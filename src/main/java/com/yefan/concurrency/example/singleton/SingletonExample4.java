package com.yefan.concurrency.example.singleton;


import com.yefan.concurrency.annoations.NoThreadSafe;

/**
 * 懒汉模式，双重同步锁单例模式
 *  指令重排序肯能后在造成线程不安全
 *  正常
 *  1， memory = allocate() 分配内存
 *  2，instance 初始化对象
 *  3，instance= memory 实例指向内存地址
 *
 *  指令重拍
 *  1， memory = allocate() 分配内存
 *  2，instance= memory 实例指向内存地址
 *  3，instance 初始化对象 此时多线程在29行时可能另一个线程已经执行内存，但是没实例化 就会返回
 */
@NoThreadSafe
public class SingletonExample4 {

    private SingletonExample4(){

    }

    private static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance(){
        if(null == instance){
            synchronized (SingletonExample4.class){
                if(null == instance){
                    instance = new SingletonExample4();
                }
            }

        }
        return instance;
    }
}
