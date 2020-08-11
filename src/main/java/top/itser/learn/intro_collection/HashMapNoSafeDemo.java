package top.itser.learn.intro_collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap不安全及解决方案:ConcurrentHashMap
 * @author Zhangchen
 */
public class HashMapNoSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();//HashMap 线程不安全
        //Map<String,String> map = new ConcurrentHashMap<>();//解决方案
        for (int i=0;i<10;i++)
        {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }
    }
}
