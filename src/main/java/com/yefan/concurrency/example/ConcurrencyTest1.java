package com.yefan.concurrency.example;

import com.yefan.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NoThreadSafe("线程不安全的写法")
public class ConcurrencyTest1 {

    //请求总数
    private static int clientTotal = 5000;

    //并发数
    private static int threadTotal = 200;

    private static long COUNT = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        exec.shutdown();

        log.info("总数量为:{}", COUNT);
    }

    private static void add(){
        COUNT ++ ;
    }
}
