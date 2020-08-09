package top.itser.learn.intro_volatile;


import java.util.concurrent.TimeUnit;

/**
 * 验证 volatile 的可见性
 * <p>
 * 注 1：Java中System.out.println()会影响内存可见性
 * 注2 ： 有可能不加volatile，也会出现主线程读到新值的情况 https://blog.csdn.net/whxwkb/article/details/82190066
 * 不加volatile ：
 * 现象1：主线程不加：Thread.sleep(10);线程1将flag改为true,主线程读取不到，主线程flag=false，主线程一直循环，(这种情况如果电脑性能好的话，可能出现不了，多找几台电脑试试)
 * 现象2：主线程加：Thread.sleep(10);那么主线程可以读取到线程1改变的flag值，说明线程1将flag值刷新到了公共内存中 此时两者都为true
 * <p>
 * 加volatile:
 * 此时主线程加不加 Thread.sleep(10); 两个线程都会结束：flag 都为true，说明 volatile关键字是让变量变为线程之间可见
 *
 * @author Zhangchen
 */
public class VolatileVisibleness {
    public static void main(String[] args) {
        MyData myData = new MyData();
        // Hanhan 线程 Sleep 3秒后，执行方法 addTo60
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":start.");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":over.MyData is " + myData.num);
        }, "Hanhan-Thread").start();

        while (myData.num == 0) {
        }
        System.out.println(Thread.currentThread().getName() + ":over.MyData is " + myData.num);
    }
}

class MyData {
    public volatile int num = 0;
    public void addTo60() {
        this.num = 60;
    }
}

