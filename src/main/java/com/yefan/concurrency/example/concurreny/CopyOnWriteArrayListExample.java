package com.yefan.concurrency.example.concurreny;

import com.yefan.concurrency.annoations.NoThreadSafe;
import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {

    // 线程不安全
    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
        log.info("count:{}" , list.size());

    }

    private static void add(){
        list.add(1);
    }
}
