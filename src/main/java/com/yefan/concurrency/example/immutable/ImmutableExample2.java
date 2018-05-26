package com.yefan.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.yefan.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableMap 不能再修改对象，包括里面的值
 */
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,4);
        System.out.println(map.get(1));
    }
}
