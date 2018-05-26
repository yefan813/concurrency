package com.yefan.concurrency.example.commonUnSafe;

import com.yefan.concurrency.annoations.NoThreadSafe;
import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateFormatExample3 {

    //请求总数
    private static int clientTotal = 5000;

    //并发数
    private static int threadTotal = 200;


    //thread safe
    private static DateTimeFormatter dateTimeformatter = DateTimeFormat.forPattern("yyyyMMdd");

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
        DateTime.parse("20180911",dateTimeformatter).toDate();
    }
}
