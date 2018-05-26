package com.yefan.concurrency.example.singleton;


import com.yefan.concurrency.annoations.Recommend;
import com.yefan.concurrency.annoations.ThreadSafe;

@ThreadSafe
@Recommend
public class SingletomExample7 {
    private SingletomExample7(){

    }
    private static SingletomExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletomExample7 singleton;

        //jvm 保证此方法只执行一次
        Singleton() {
            singleton = new SingletomExample7();
        }

        public SingletomExample7 getInstance(){
            return singleton;
        }

    }
}
