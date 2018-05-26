package com.yefan.concurrency.example.singleton;

import com.yefan.concurrency.annoations.NoRecommend;
import com.yefan.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式，第一次使用时创建
 *
 * 多线程会阻塞线程，影响性能
 */
@ThreadSafe
@NoRecommend
public class SingletonExample3 {

    private SingletonExample3(){

    }

    private static SingletonExample3 instance = null;

    public synchronized static SingletonExample3 getInstance(){
        if(null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
