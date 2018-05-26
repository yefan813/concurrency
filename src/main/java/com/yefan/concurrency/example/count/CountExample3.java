package com.yefan.concurrency.example.count;

import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe("线程安全的写法")
public class CountExample3 {

    //请求总数
    private static int clientTotal = 5000;

    //并发数
    private static int threadTotal = 200;

    /**
     * volatile 只保证可见性，不能保证原子性
     *
     * volatile 用法：
     * volatile 对变量写不依赖当前值
     * 
     */
    private static volatile int COUNT = 0;

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
        COUNT++;
    }
}
