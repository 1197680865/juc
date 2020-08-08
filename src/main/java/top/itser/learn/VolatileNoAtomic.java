package top.itser.learn;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  1.验证 volatile 的不具备原子性
 *  2.解决原子性问题：
 *      使用Atomic保证原子性（推荐）
 *      或使用synchronize（太重量级）
 */
public class VolatileNoAtomic {
    public static void main(String[] args) {
        MyVData vData = new MyVData();
        for (int i=0;i<10 ;i++)
        {
            new Thread(()->{
                for (int j=0;j<2000;j++)
                {
                    vData.addPlusPlus();
                    vData.addAtomic();
                }
            },"Thread " + i).start();
        }
        //等待10个线程全部完成后，在主线程打印最终累计结果
        while (Thread.activeCount() > 2)
        {
            //Thread.yield()翻译成中文就是让步的意思，根据语义理解就是线程让出当前时间片给其他线程执行
            Thread.yield();
        }
        System.out.println(vData.num);//结果会小于20000
        System.out.println(vData.atomicNum); // atomic 结果==20000
    }

}
class MyVData {
    public volatile int num = 0;

    //this.num ++; 非原子操作
    public void addPlusPlus() {
        //三步： 拷贝（从主内存拷贝） -> 计算 -> 写入（到主内存）
        this.num ++;
    }

    AtomicInteger atomicNum = new AtomicInteger();
    public void addAtomic(){
        atomicNum.getAndIncrement();
    }
}