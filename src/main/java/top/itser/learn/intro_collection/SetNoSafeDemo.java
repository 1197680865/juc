package top.itser.learn.intro_collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Set不安全及解决方案
 * @author Zhangchen
 */
public class SetNoSafeDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();//HashSet 线程不安全
        //Set<String> set = Collections.synchronizedSet(new HashSet<>()); //解决方案1
        //Set<String> set = new CopyOnWriteArraySet<>();//解决方案2
        for (int i=0;i<10;i++)
        {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }).start();
        }
    }
}
