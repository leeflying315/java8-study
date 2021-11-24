package com.lifei.study.futurestudy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author: lifei
 * @Description:
 * @Date: 2020/11/5
 */
public class FutureDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        CompletableFuture<String> test1 = new CompletableFuture<>();
        test1.complete("hello");
        CompletableFuture<String> test2 = new CompletableFuture<>();
        test2.complete("world");

        CompletableFuture<String> test3 = new CompletableFuture<>();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test3.completeExceptionally(new NullPointerException());

        priceFutures.add(test1);
        priceFutures.add(test2);
        priceFutures.add(test3);

        // 等待所有异步操作结束 异常会抛出
        List<String> prices = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("CompletableFuture异步方式查询价格耗时" + invocationTime + " ms," + "价格列表:" + prices);
    }
}
