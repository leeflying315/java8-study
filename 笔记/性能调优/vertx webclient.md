# Vertx同步webclient处理

## 1. 业务场景

使用vertx的kafka套件消费消息后，需要对消息进行HTTP转发推送。topic已经对key做了hash，实现了顺序生产和消费。但是推送需要按照严格顺序。

## 2. 问题现象

在做HTTP推送的时候，使用vertx的webclient套件。是异步框架，在消息未响应的时候就开始发送下一条消息。接收方通过nginx负载后相应速度不一致，导致接收方入库乱序。

## 解决方案

### 1. 使用EventBus消息队列

使用EventBus消息队列，把kafka消费的消息单独发到一个topic中。使用单独的一个worker verticle来承载推送消息任务。

测试发现已经解决client端仍在woker verticle阻塞。

### 2. 使用CompletableFuture实现

考虑webclient异步特性，使用vertx.executeBlocking 独立线程的方式执行webclient发送HTTP请求。在webclient响应后填充future值。主线程使用future.get同步等待。

此时发现webclient被future.get阻塞。直到future.get超时后才会执行。

~~~java
        log.info("receive msg is {}", context);
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
        vertx.executeBlocking(t -> {
            log.info("send http request ");
            client.put(configManager.getCloud().getPort(), configManager.getCloud().getServer(),
                    Constants.SYNC_DEVICE_UP_DATA_URL).sendBuffer(Buffer.buffer(context), ar -> {
                if (ar.succeeded()) {
                    HttpResponse<Buffer> response = ar.result();
                    log.info("Got HTTP response with status " + response.statusCode());
                    completableFuture.complete(response.bodyAsString());
                } else {
                    log.error("", ar.cause());
                    completableFuture.complete(ar.cause().getMessage());
                }
            });
        }, false);
        try {
            log.info("start to waiting result");
            String result = completableFuture.get(5000, TimeUnit.MILLISECONDS);
            log.info("get result is {}", result);
        } catch (Exception e) {
            log.error("", e);
        }


日志如下：
2021-12-10 08:49:55.178 [INFO ] [vert.x-worker-thread-10] [SyncSendVerticle.java:40] - receive msg is {data}
2021-12-10 08:49:55.179 [INFO ] [vert.x-worker-thread-10] [SyncSendVerticle.java:57] - start to waiting result
2021-12-10 08:49:55.180 [INFO ] [vert.x-worker-thread-11] [SyncSendVerticle.java:43] - send http request 
2021-12-10 08:50:00.190 [ERROR] [vert.x-worker-thread-10] [SyncSendVerticle.java:61] - 
java.util.concurrent.TimeoutException: null
	at java.util.concurrent.CompletableFuture.timedGet(CompletableFuture.java:1771) ~[?:1.8.0_202]
	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1915) ~[?:1.8.0_202]
	at cn.cuiot.dmp.verticle.SyncSendVerticle.syncMsgHandler(SyncSendVerticle.java:58) ~[classes/:?]
	at io.vertx.core.impl.AbstractContext.dispatch(AbstractContext.java:100) ~[vertx-core-4.2.0.jar:4.2.0]
	at io.vertx.core.impl.WorkerContext.lambda$emit$0(WorkerContext.java:59) ~[vertx-core-4.2.0.jar:4.2.0]
	at io.vertx.core.impl.WorkerContext.lambda$execute$2(WorkerContext.java:104) ~[vertx-core-4.2.0.jar:4.2.0]
	at io.vertx.core.impl.TaskQueue.run(TaskQueue.java:76) ~[vertx-core-4.2.0.jar:4.2.0]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [?:1.8.0_202]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [?:1.8.0_202]
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) [netty-common-4.1.69.Final.jar:4.1.69.Final]
	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_202]
2021-12-10 08:50:00.246 [INFO ] [vert.x-worker-thread-12] [SyncSendVerticle.java:48] - Got HTTP response with status 200
~~~

日志中可以清楚的观察到，主线程work-thread-10线程正常打印，进入到future.get后迅速被阻塞。然后work-thread-11线程开始执行，此时无法发送http请求，查看推送端也未收到消息。到future.get超时释放异常后，http request才得以发送。

**由此可见，http发送请求是一个在主线程上执行（加载他的vertcile上），响应回调会新生成回调线程执行**

### 3. 手动提交offset

offset 修改为手动提交时，仍会批量消费一批消息后手动提交

~~~java
consumer.commit(ar -> {
  if (ar.succeeded()) {
    System.out.println("Last read message offset committed");
  }
});
~~~

手动提交无效，应该vertx kafka做了代码完成自动提交业务

## 4. consumer pause 暂定

~~~java
TopicPartition topicPartition = new TopicPartition()
  .setTopic("test")
  .setPartition(0);

//注册一个handler处理进来的消息
consumer.handler(record -> {
  System.out.println("key=" + record.key() + ",value=" + record.value() +
    ",partition=" + record.partition() + ",offset=" + record.offset());

  // 如果我们读到partition0的第5个offset
  if ((record.partition() == 0) && (record.offset() == 5)) {

    // 则暂停读取
    consumer.pause(topicPartition, ar -> {

      if (ar.succeeded()) {

        System.out.println("Paused");

        // 5秒后再恢复,继续读取
        vertx.setTimer(5000, timeId -> {

          // resumi read operations
          consumer.resume(topicPartition);
        });
      }
    });
  }
});
~~~

性能太差，PASS

