package com.yefan.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.yefan.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NoThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    //修饰引用变量不能，重新指向新的对象，但是可以对对象里面的值重新赋值
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        a =1
//        b = "aa"
//        map = Maps.newHashMap();
        map.put(1,4);
        System.out.println(map.get(1));
    }

    private void test(final int a){
//        a =1
    }
}
