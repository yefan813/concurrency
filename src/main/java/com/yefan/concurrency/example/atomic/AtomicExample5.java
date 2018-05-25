package com.yefan.concurrency.example.atomic;

import com.yefan.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe("线程安全的写法")
public class AtomicExample5 {
    /**
     * 原子性的修改某个类实例的属性、注：属性必须是 volatile , 且不能是 static  修饰的
     */
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile  int count = 100;

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5,100,120)) {
            log.info("update success 1,{}", example5.getCount());
        }


        if (updater.compareAndSet(example5,100,120)) {
            log.info("update success 2,{}", example5.getCount());
        }else{
            log.info("update failed.{ }", example5.getCount());
        }


    }

}
