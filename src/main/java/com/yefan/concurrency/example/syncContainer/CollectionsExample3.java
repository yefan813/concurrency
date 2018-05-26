package com.yefan.concurrency.example.syncContainer;

import com.yefan.concurrency.annoations.NoThreadSafe;
import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CollectionsExample3 {

    // 线程不安全
    private static Map<Integer,Integer> map = Collections.synchronizedMap(new HashMap<>());

    //请求总数
    private static int clientTotal = 5000;

    //并发数
    private static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count:{}" , map.size());

    }

    private static void add(int i){
        map.put(i,i);
    }
}
