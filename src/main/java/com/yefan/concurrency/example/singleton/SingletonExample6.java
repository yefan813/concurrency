package com.yefan.concurrency.example.singleton;

import com.yefan.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式，类加载是创建实例
 *
 * 如果没有实际调用，浪费
 *
 */
@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6(){

    }
    private static SingletonExample6 instance =  null;

    static {
        instance = new SingletonExample6();
    }


    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());

    }
}
