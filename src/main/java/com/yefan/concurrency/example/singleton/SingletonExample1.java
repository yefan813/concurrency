package com.yefan.concurrency.example.singleton;

import com.yefan.concurrency.annoations.NoThreadSafe;

/**
 * 懒汉模式，第一次使用时创建
 *
 * 多线程下会出问题， 17-19行多线层下回初始化多次
 * 如果构造函数很耗时，会浪费内存 、cpu
 */
@NoThreadSafe
public class SingletonExample1 {

    private SingletonExample1(){

    }

    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance(){
        if(null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
