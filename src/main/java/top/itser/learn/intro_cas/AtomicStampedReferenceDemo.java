package top.itser.learn.intro_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * @author Zhangchen
 */
public class AtomicStampedReferenceDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(10);
    public static void main(String[] args) {
        System.out.println("----  ABA问题复现  ----");
        new Thread(()->{
            atomicReference.compareAndSet(10,99);
            atomicReference.compareAndSet(99,10);
            System.out.println(Thread.currentThread().getName() + " value:" + atomicReference.get());
        },"t1").start();
        new Thread(()->{
            //do something
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()  + ":" +
                        atomicReference.compareAndSet(10,11));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();



        System.out.println("----  ABA问题解决方案  ----");
        //参数2 为版本号或者是时间戳，此处使用版本号形式
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(10,1);

        new Thread(()->{
            //获取版本号
            int stamp = stampedReference.getStamp();
            try {
                //等待t4线程也获取到版本号
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(10,99,stamp, stamp+1);
           int stamp1 = stampedReference.getStamp();
            stampedReference.compareAndSet(99,10,stamp1,stamp1 +1 );
            System.out.println(Thread.currentThread().getName() + " value:" + stampedReference.getReference()
                    +" ,version:" +stampedReference.getStamp());
            //t3 value:10 ,version:3
        },"t3").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
                boolean flag = stampedReference.compareAndSet(10, 11, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName()  + ":" + flag);
                //t4:false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
