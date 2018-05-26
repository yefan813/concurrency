package com.yefan.concurrency.example.singleton;

import com.yefan.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式，类加载是创建实例
 *
 * 如果没有实际调用，浪费
 *
 */
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instance =  new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
