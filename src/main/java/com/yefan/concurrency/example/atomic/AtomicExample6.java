package com.yefan.concurrency.example.atomic;

import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe("线程安全的写法")
public class AtomicExample6 {

    /**
     * 原子操作设置 true or false
     */
    static AtomicBoolean isHappened = new AtomicBoolean(false);

    //请求总数
    private static int clientTotal = 5000;

    //并发数
    private static int threadTotal = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        exec.shutdown();

        log.info("总数量为:{}", isHappened.get());
    }

   private  static void  test(){
       if (isHappened.compareAndSet(false,true)) {
           log.info("execute ---------");
       }
    }


}
