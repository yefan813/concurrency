package com.yefan.concurrency.example.commonUnSafe;

import com.yefan.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NoThreadSafe
public class DateFormatExample2 {

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

    }

    private static void add(){
        //局部变量不存在线程安全问题
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            simpleDateFormat.parse("20180401");
        } catch (ParseException e) {
            log.error("exception");
        }
    }
}
