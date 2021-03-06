package com.yefan.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.yefan.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class ImmutableExample3 {

    private final  static ImmutableList<Integer> list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2);

    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();


    public static void main(String[] args) {
//        list.add(3);
//        set.add(3);
        map2.put(1,3);

    }
}
