package com.yefan.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 不可中断锁  适合竞争不激烈
 * Lock 可中断锁，竞争激烈也能维持常态
 * atomic：竞争激烈也能维持常态，比 lock 性能好，但是只能同步一个值
 *
 *
 * 解锁前必须吧共享变量刷新到主内存
 *
 * 加锁时 需要清空工作内存中的共享变量的值，使共享变量从主内存读取最新的值
 */
@Slf4j
public class SynchronizedExample1 {
    public void test1(){
        synchronized(this){
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}" , i);
            }
        }
    }

    //修饰方法
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}" , i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            example1.test1();
        });
        service.execute(()->{
            example2.test2();
        });
        service.shutdown();
    }
}
