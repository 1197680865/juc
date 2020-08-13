package top.itser.learn.intro_volatile;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单例模式-懒汉式  演示线程不安全问题
 *  线程安全问题的解决方案，见：{@link VolatileSingletonDCL}
 * @author Zhangchen
 */
public class SingletonLazyOnSafe {
    private static SingletonLazyOnSafe instance;
    private SingletonLazyOnSafe(){}

    public static  SingletonLazyOnSafe getInstance()
    {
        if (null == instance)
        {
            //模拟延迟 此处会导致在多线程下同时getInstance，出现线程不安全问题
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonLazyOnSafe();
        }
        return instance;
    }

    public static void main(String[] args) {
        Callable<SingletonLazyOnSafe> callable = new Callable<SingletonLazyOnSafe>() {
            @Override
            public SingletonLazyOnSafe call() throws Exception {
                return SingletonLazyOnSafe.getInstance();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<SingletonLazyOnSafe> s1 = executorService.submit(callable);
        Future<SingletonLazyOnSafe> s2 = executorService.submit(callable);
        System.out.println( s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
//        false
//        java.util.concurrent.FutureTask@16f6e28
//        java.util.concurrent.FutureTask@15fbaa4
    }
}
