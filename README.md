# java8-study
 学习异步响应式编程

**HashMap数据结构**

HashMap采用了数组+链表/红黑树数据结构，在链表长度大于8时升级为红黑树，小于六时退化为链表。避免频繁数据结构转换。

HashMap插入数据过程：

1. 判断数组是否为空，为空进行初始化。
2. 不为空，计算 k 的 hash 值，通过`(n - 1) & hash`计算应当存放在数组中的下标 index;
3. 查看 table[index] 是否存在数据，没有数据就构造一个Node节点存放在 table[index] 中；
4. 存在数据，说明发生了hashssz(存在二个节点key的hash值一样), 继续判断key是否相等，相等，用新的value替换原数据(onlyIfAbsent为false)；
5. 如果不相等，判断当前节点类型是不是树型节点，如果是树型节点，创造树型节点插入红黑树中；
6. 如果不是树型节点，创建普通Node加入链表中；判断链表长度是否大于 8， 大于的话链表转换为红黑树；
7. 插入完成之后判断当前节点数是否大于阈值，如果大于开始扩容为原数组的二倍。

**红黑树与平衡二叉树比较**

虽然平衡树解决了二叉查找树退化为近似链表的缺点，能够把查找时间控制在 O(logn)，不过却不是最佳的，因为平衡树要求每个节点的左子树和右子树的高度差至多等于1，这个要求实在是太严了，导致每次进行插入/删除节点的时候，几乎都会破坏平衡树的第二个规则，进而我们都需要通过左旋和右旋来进行调整，使之再次成为一颗符合要求的平衡树。

实际应用中，若搜索的次数远远大于插入和删除，那么选择AVL，如果搜索，插入删除次数几乎差不多，应该选择RB。



## Java延迟队列

**Timer**

JDK自带的定时器

存在schedule和scheduleAtFixedRate两套不同调度算法的方法， 它们的共同点是若判断理论执行时间小于实际执行时间时，都会马上执行任务，区别在于计算下一次执行时间的方式不同：

- schedule： 任务开始的时间 + period（时间片段），强调“固定间隔”地执行任务
- scheduleAtFixedRate： 参数设定开始的时间 + period（时间片段），强调“固定频率”地执行任务

可以看出前者采用实际值，后者采用理论值。不过实际上若参数设定的开始时间比当前时间大的话，两者执行的效果是一样的。

**缺点**

Timer被设计成支持多个定时任务，通过源码发现它有一个任务队列用来存放这些定时任务，并且启动了一个线程来处理。

通过这种单线程的方式实现，在存在多个定时任务的时候便会存在问题： 若任务B执行时间过长，将导致任务A延迟了启动时间！

还存在另外一个问题，应该是属于设计的问题： 若任务线程在执行队列中某个任务时，该任务抛出异常，将导致线程因跳出循环体而终止，即Timer停止了工作！



## NIO和BIO区别

**堆外内存优点**

1. 减少垃圾回收工作，因为垃圾回收会暂停其他工作。
2. 加快了复制速度，因为堆内在flush到远程时，会先复制到直接内存（非堆内存），然后在发送；而堆外内存相当于省略掉了这个工作。 福之祸所依，自然也有不好的一面。

**堆外内存缺点**

1. 堆外内存难以控制，如果内存泄漏，那么很难排查 
2. 堆外内存相对来说，不适合存储很复杂的对象。一般简单的对象或者扁平化的比较适合。

**由于堆外内存并不直接控制于JVM，因此只能等到full GC的时候才能垃圾回收！（direct buffer归属的的JAVA对象是在堆上且能够被GC回收的，一旦它被回收，JVM将释放direct buffer的堆外空间。前提是没有关闭**DisableExplicitGC**）**



**堆外内存回收方法**

堆外内存回收的几张方法：

1. Full GC，一般发生在年老代垃圾回收以及调用System.gc的时候，但这样不一顶能满足我们的需求。
2. 调用ByteBuffer的cleaner的clean()，内部还是调用System.gc(),所以一定不要**-XX:+DisableExplicitGC**



1. 减少GC时间
2. 进程间可以共享,减少虚拟机间的复制

## Netty

**Netty业务设计**

Netty的ChannelHandler是业务代码和Netty框架交汇的地方，ChannelHandler里的业务逻辑，正常来说是由NioEventLoop（NIO）线程串行执行，以Netty服务端举例，在服务端接收到新消息后，第一步要做的往往是用解码的handler解码消息的字节序列，字节序列解码后就变为了消息对象，第二步将消息对象丢给后续的业务handler处理，**此时如果某个业务handler的流程非常耗时，比如需要查询数据库，那么为了避免I/O线程（也就是Netty的NIO线程）被长时间占用**，需要使用额外的非I/O线程池来执行这些耗时的业务逻辑，这也是基本操作。

看下NIO线程常见的阻塞情况，一共两大类：

- 无意识：在ChannelHandler中编写了可能导致NIO线程阻塞的代码，但是用户没有意识到，包括但不限于查询各种数据存储器的操作、第三方服务的远程调用、中间件服务的调用、等待锁等
- 有意识：用户知道有耗时逻辑需要额外处理，但是在处理过程中翻车了，比如主动切换耗时逻辑到业务线程池或者业务的消息队列做处理时发生阻塞，最典型的有对方是阻塞队列，锁竞争激烈导致耗时，或者投递异步任务给消息队列时异机房的网络耗时，或者任务队列满了导致等待，等等

Netty中两大线程池：

- NioEventLoopGroup
- DefaultEventExecutorGroup

1. NioEventLoopGroup线程池内部是NioEventLoop，它可以理解为是I/O线程，它内部聚合了Java的Thread，以及JDK的I/O多路复用器，实现了事件循环机制，侧重于处理网络I/O相关的各种操作
2. DefaultEventExecutorGroup线程池内部是DefaultEventExecutor，它可以理解为是非I/O线程，内部聚合了Java的Thread，但没有I/O多路复用器，侧重于处理耗时业务逻辑（非I/O操作）
3. I/O线程池里的线程封装实例Thread会绑定I/O多路复用器，以及配套的NIO的属性，非I/O线程池的线程只封装Thread，类似于JDK原生的线程，很干净。
4. 非I/O线程池的线程执行器的执行原理和I/O线程池的线程执行器没什么区别，都可以统称为Netty线程，而且它们都消除了锁竞争。即每个Channel只会绑定一个不变的Netty线程，而一个Netty线程可以绑定多个Channel，期间每个Channel上的各种handler的逻辑执行都是串行无锁的。

它们和JDK线程池的区别

两者比起来，Netty线程池里的线程全部消除了对锁的竞争，而JDK的线程池没有这种设计，JDK线程池的线程会处理同一个阻塞队列。比如LinkedBlockingQueue，可能产生锁竞争。

### Netty 解码器Decoder、组合编解码器Codec

**HttpServerCodec **

- `HttpServerCodec`是netty针对http编解码的处理类，但是这些只能处理像http `get`的请求,也就是数据带在`url问号后面`的http请求

**HttpObjectAggregator**

这个netty的处理器把`HttpMessage`和`HttpContent`聚合成为一个`FullHttpRquest`或者`FullHttpRsponse`。



## Spring

### Spring Bean 加载过程

首先加载配置类的后置处理器, 将其解析后放入到beanDefinitionMap中. 然后加载配置类, 也将其解析后放入beanDefinitionMap中. 最后解析配置类. 我们这里直接简化掉前两步, 将两个类放入beanDefinitionMap中. 主要模拟第三步解析配置类. 在解析的过程中, 获取bean的时候会出现循环依赖的问题循环依赖。

在@Autowired的时候, 增加了一个出口. 判断即将要创建的类是否已经存在, 如果存在了, 那么就直接返回, 不在创建。

虽然使用了一级缓存解决了循环依赖的问题, 但要是在多线程下, 这个依赖可能就会出现问题.

比如: 有两个线程, 同时创建instanceA 和instanceB, instanceA和instanceB都引用了instanceC. 他们同步进行, 都去创建instanceC. 首先A去创建, A在实例化instanceC以后就将其放入到一级缓存了, 这时候, B去一级缓存里拿. 此时拿到的instanceC是不完整的. 后面的属性赋值, 初始化都还没有执行呢. 所以, 我们增加耳机缓存来解决这个问题. 

```
一级缓存: 解决循环依赖的问题
二级缓存: 在创建实例bean和放入到一级缓存之间还有一段间隙. 如果在这之间从一级缓存拿实例, 肯定是返回null的. 为了避免这个问题, 增加了二级缓存.
```

```
面试题: 在创建bean的时候, 在哪里创建的动态代理, 这个应该怎么回答呢?
很多人会说在初始化之后, 或者在实例化之后.
其实更严谨的说, 有两种情况: 第一种是在初始化之后调用 . 第二种是出现了循环依赖, 会在实例化之后调用
```

三级缓存的作用:

**一级缓存:** 用来存放成熟的bean. 这个bean如果是切入点, 则是一个动态代理的bean,如果不是切入点, 则是一个普通的类

**二级缓存:** 用来存放循环依赖过程中创建的动态代理bean. 

**三级缓存:** 用来存放动态代理的钩子方法. 用来在需要构建动态代理类的时候使用.

**Spring IOC**

**控制反转，依赖注入**

当对象创建的太多的时候，就会出现一个对象更改，就得更改所有依赖它的对象，耦合性大。 **自主性体现的同时也出现了对象耦合严重的情况** 。我们在用的时候直接拿到这个对象去用，而将创建对象的能力交给第三方，这样我们就不需要关心对象是怎么创建的了。即将自己的控制权交出去。 **这就是控制反转**

 **对象怎么才能直接被我们拿来用呢** 。对象创建的时候，我们把这个对象注入到这个对象中，然后就可以使用了。 

**这就是依赖注入**

**完成这些工作的就是IOC容器，它帮助我们创建对象，然后在对象被使用的时候，将对象注入到这个对象中。而由于IOC创建对象是通过反射来创建的，所以其速度不如直接new对象**



**Spring autowired 和resource注解区别**

​	@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。@Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。

**SpringBoot加载配置文件**

可放置目录(优先级从高到低)

```bash
1. ./config/ (当前项目路径config目录下); 
2. ./ (当前项目路径下); 
3. classpath:/config/ (类路径config目录下); 
4. classpath:/ (类路径config下).
```

SpringBoot会从这四个位置全部加载配置文件并互补配置；

**@ConfigurationProperties与@Value两种注解对比**

| 比较项                    | @ConfigurationProperties | @Value |
| :------------------------ | :----------------------- | :----- |
| 全量注入                  | 支持                     | 不支持 |
| 松散绑定(Relaxed Binding) | 支持                     | 不支持 |
| SpEL                      | 不支持                   | 支持   |
| JSR303                    | 支持                     | 不支持 |

松散绑定：驼峰命名(userName)、横干拼接(user-name)、下划线（user_name）之间可以互相识别绑定称为做松散绑定 

JSR303：通过@Email，@Nullable，@Digits 等等注解进行邮箱、判空、数字格式等等数据的校验

@ConfigurationProperties通常用于将配置全量注入某个类中； @Value通常用于注入某一些特定配置值中；

**单例模式和静态类区别**

单例模式是面向对象的设计。本质上还是创建对象，调用方法。单例存在的根本就是为了得到对象。

静态类是单纯使用方法体，对象没有存在的价值。所以直接使用类名调用，不创建对象。静态类存在是为了快捷方便的使用里面的方法。

**Spring中拦截器和过滤器的区别**

1. 拦截器不依赖与servlet容器是SpringMVC自带的，过滤器依赖于Servlet容器。
2. 拦截器是基于java的反射机制的，而过滤器是基于函数回调。
3. 拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。
4. 拦截器可以访问controller上下文、值栈里的对象，而过滤器不能访问。(拦截器的preHandle方法在进入controller前执行，而拦截器的postHandle方法在执行完controller业务流程后，在视图解析器解析ModelAndView之前执行，可以操控Controller的ModelAndView内容。而afterCompletion是在视图解析器解析渲染ModelAndView完成之后执行的)( 过滤器是在服务器启动时就会创建的，只会创建一个实例，常驻内存，也就是说服务器一启动就会执行Filter的init(FilterConfig config)方法.当Filter被移除或服务器正常关闭时，会执行destroy方法)
5. 拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。(关于这句话的解读是：我们知道拦截器是SprinMVC自带的，而SpringMVC存在Controller层的，而controller层可以访问到service层，service层是不能访问service层的，而过滤器是客户端和服务端之间请求与响应的过滤)
6. 过滤器和拦截器触发时机、时间、地方不一样。(过滤器是在请求进入容器后，但请求进入servlet之前进行预处理的。请求结束返回也是在servlet处理完后，返回给前端之前,如果看不懂可以看7完后再来理解)
7. 过滤器包裹住servlet，servlet包裹住拦截器。



## HttpClient

**ConnectionRequestTimeout**

httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池

**ConnectTimeout**

连接建立时间，三次握手完成时间

首先需要添加apache httpclient依赖

```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.12</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpcore</artifactId>
    <version>4.4.13</version>
</dependency>
```

然后新建httpclient类：

在dopost中可以根据业务自定义逻辑

```java
@Slf4j
@Service
public class HttpClientFactory {
    @Autowired
    HttpClientConfig httpClientConfig;

    private PoolingHttpClientConnectionManager poolConnManager;

    // 线程安全，所有的线程都可以使用它一起发送http请求
    private CloseableHttpClient httpClient;

    @PostConstruct
    public void init() {
        try {
            log.info("init http client start, default config is {}", httpClientConfig);
            SSLConnectionSocketFactory trustAll = buildSSLContext();
            // 配置同时支持 HTTP 和 HTTPS
            // 一个httpClient对象对于https仅会选用一个SSLConnectionSocketFactory
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
                    register("http", PlainConnectionSocketFactory.getSocketFactory()).
                    register("https", trustAll).build();
            // 初始化连接管理器
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnManager.setMaxTotal(httpClientConfig.getPollMaxTotal());// 同时最多连接数
            // 设置最大路由
            poolConnManager.setDefaultMaxPerRoute(httpClientConfig.getPollMaxPeerRouter());
            httpClient = getConnection();
            log.info("init http client finish");
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public CloseableHttpClient getConnection() {

        RequestConfig config = RequestConfig.custom().setConnectTimeout(httpClientConfig.getConnectTimeout())
                .setConnectionRequestTimeout(httpClientConfig.getConnectionRequestTimeout())
                .setSocketTimeout(httpClientConfig.getResponseTimeout())
                .build();
        return HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(config).build();
    }

    public String doGet(String url) {
        return this.doGet(url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
    }

    public String doGet(String url, Map<String, Object> params) {
        return this.doGet(url, Collections.EMPTY_MAP, params);
    }

    public String doGet(String url, Map<String, String> headers, Map<String, Object> params) {

        // *) 构建GET请求头
        String apiUrl = getUrlWithParams(url, params);
        HttpGet httpGet = new HttpGet(apiUrl);

        // *) 设置header信息
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    return EntityUtils.toString(entityRes, "UTF-8");
                }
            }
            return null;
        } catch (IOException e) {
            log.error("", e);

        }
        return null;
    }

    public HttpServerResponseDTO doPost(String apiUrl, String body, int connectionTimeOut, Integer contentTypeEnum, String pemBody) {
        return doPost(apiUrl, Collections.EMPTY_MAP, body, connectionTimeOut, contentTypeEnum);
    }

    public HttpServerResponseDTO doPost(String apiUrl, Map<String, String> headers, String body,Integer contentTypeEnum) {
        CloseableHttpClient currentHttpClient = httpClient;
        HttpPost httpPost = new HttpPost(apiUrl);
        // *) 配置请求headers
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        ContentTypeEnum contentType = ContentTypeEnum.getDataSourceEnum(contentTypeEnum);
        // *) 配置请求参数
        httpPost.setEntity(new StringEntity(body, ContentType.create(contentType.getDesc(), Consts.UTF_8)));


        httpPost.setConfig(buildRequestConfig());
        try (CloseableHttpResponse response = currentHttpClient.execute(httpPost)) {
            if (response == null || response.getStatusLine() == null) {
                return HttpServerResponseDTO.builder()
                        .statusCode(Constants.HTTP_CLIENT_ERROR)
                        .build();
            }
            HttpEntity httpEntity = response.getEntity();
            String contentTypeString = httpEntity.getContentType() == null ? null : httpEntity.getContentType().getValue();
            String connection = getHeaderValue(response, "Connection");
            String server = getHeaderValue(response, "Server");
            String date = getHeaderValue(response, "Date");
            String pragma = getHeaderValue(response, "pragma");

            return HttpServerResponseDTO.builder()
                    .statusCode(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity(), UTF_8))
                    .contentType(contentTypeString)
                    .connection(connection)
                    .server(server)
                    .date(date)
                    .pragma(pragma)
                    .build();
        } catch (IOException e) {
            log.error("", e);
            return HttpServerResponseDTO.builder().statusCode(Constants.HTTP_CLIENT_ERROR).statusMessage(e.getMessage()).build();
        }
    }

    private String getUrlWithParams(String url, Map<String, Object> params) {
        boolean first = true;
        StringBuilder sb = new StringBuilder(url);
        for (String key : params.keySet()) {
            char ch = '&';
            if (first) {
                ch = '?';
                first = false;
            }
            String value = params.get(key).toString();
            try {
                String sval = URLEncoder.encode(value, "UTF-8");
                sb.append(ch).append(key).append("=").append(sval);
            } catch (UnsupportedEncodingException e) {
                log.error("", e);
            }
        }
        return sb.toString();
    }

    public SSLConnectionSocketFactory buildSSLContext() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {

        SSLContext sslcontext = SSLContexts.custom()
                //忽略掉对服务器端证书的校验
                .loadTrustMaterial((TrustStrategy) (chain, authType) -> true)
                .build();

        return new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                null,
                NoopHostnameVerifier.INSTANCE);
    }

    private RequestConfig buildRequestConfig() {
        int connectionOut = httpClientConfig.getConnectTimeout();
        return RequestConfig.custom().setConnectTimeout(connectionOut)
                .setConnectionRequestTimeout(httpClientConfig.getConnectionRequestTimeout())
                .setSocketTimeout(connectionOut)
                .build();
    }

    private String getHeaderValue(CloseableHttpResponse response, String key) {
        return response.getFirstHeader(key) == null ?
                null : response.getFirstHeader(key).getValue();
    }
}
```

调用方式：

```java
@Autowired
HttpClientFactory httpClientFactory;

HttpServerResponseDTO httpServerResponseDTO = httpClientFactory.doPost(url, headersMap, body, httpConfigEntity.getContentType(), httpConfigEntity.getTls());

```

## 线程

**Sleep和Wait两者的区别**

（1）属于不同的两个类，sleep()方法是线程类（Thread）的静态方法，wait()方法是Object类里的方法。

（2）sleep()方法不会释放锁，wait()方法释放对象锁。

（3）sleep()方法可以在任何地方使用，wait()方法则只能在同步方法或同步块中使用。

（4）sleep()必须捕获异常，wait()方法、notify()方法和notiftAll()方法不需要捕获异常。

（5）sleep()使线程进入阻塞状态（线程睡眠），wait()方法使线程进入等待队列（线程挂起），也就是阻塞类别不同。

  (6) 它们都可以被interrupted方法中断。

wait(1000)与sleep(1000)的区别

**Thread.Sleep(1000) **

意思是在未来的1000毫秒内本线程不参与CPU竞争，1000毫秒过去之后，这时候也许另外一个线程正在使用CPU，那么这时候操作系统是不会重新分配CPU的，直到那个线程挂起或结束。

即使这个时候恰巧轮到操作系统进行CPU 分配，那么当前线程也不一定就是总优先级最高的那个，CPU还是可能被其他线程抢占去。

另外值得一提的是Thread.Sleep(0)的作用，就是触发操作系统立刻重新进行一次CPU竞争，竞争的结果也许是当前线程仍然获得CPU控制权，也许会换成别的线程获得CPU控制权。

**wait(1000)**

表示将锁释放1000毫秒，到时间后如果锁没有被其他线程占用，则再次得到锁，然后wait方法结束，执行后面的代码，如果锁被其他线程占用，则等待其他线程释放锁。

注意，设置了超时时间的wait方法一旦过了超时时间，并不需要其他线程执行notify也能自动解除阻塞，但是如果没设置超时时间的wait方法必须等待其他线程执行notify。

### 线程安全Violate

​	如果一个变量在多个CPU中都存在缓存（一般在多线程编程时才会出现），那么就可能存在缓存不一致的问题 。

缓存不一致的通用解决办法：

- 总线加#Lock锁。
- 通过缓存一致性协议。

Java 内存模型规定所有的变量都是存在主存当中（类似于前面说的物理内存），每个线程都有自己的工作内存（类似于前面的高速缓存）。线程对变量的所有操作都必须在工作内存中进行，而不能直接对主存进行操作。并且每个线程不能访问其他线程的工作内存。

并发程序正确地执行， **必须要保证原子性、可见性以及有序性** 。

volatile 关键字禁止指令重排序有两层意思：

- 当程序执行到 volatile 变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行；
- 在进行指令优化时，不能将在对 volatile 变量访问的语句放在其后面执行，也不能把 volatile 变量后面的语句放到其前面执行。

观察加入 volatile 关键字和没有加入 volatile 关键字时所生成的汇编代码发现，加入 volatile 关键字时，会多出一个 lock 前缀指令。

- 它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
- 它会强制将对缓存的修改操作立即写入主存；
- 如果是写操作，它会导致其他 CPU 中对应的缓存行无效。

<<<<<<< Updated upstream
## 数据库

## Mybatis

java.util.Date实际上是能够表示MySQL的三种字段类型

1. date
2. datetime
3. timestamp

=======
# 数据库

## Mybatis

## MyBatis的实现逻辑

1. 在 MyBatis 的初始化过程中，会生成一个 Configuration 全局配置对象，里面包含了所有初始化过程中生成对象
2. 根据 Configuration 创建一个 SqlSessionFactory 对象，用于创建 SqlSession “会话”
3. 通过 SqlSession 可以获取到 Mapper 接口对应的动态代理对象，去执行数据库的相关操作
4. 动态代理对象执行数据库的操作，由 SqlSession 执行相应的方法，在他的内部调用 Executor 执行器去执行数据库的相关操作
5. 在 Executor 执行器中，会进行相应的处理，将数据库执行结果返回
>>>>>>> Stashed changes
