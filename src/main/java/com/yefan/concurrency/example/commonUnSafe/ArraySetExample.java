package com.yefan.concurrency.example.commonUnSafe;

import com.yefan.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NoThreadSafe
public class ArraySetExample {

    // 线程不安全
    private static Set<Integer> set = new HashSet<>();

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
        log.info("count:{}" , set.size());

    }

    private static void add(int i){
        set.add(i);
    }
}
