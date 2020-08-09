package top.itser.learn.intro_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS：比较并交换
 * 如果期望值一致，进行update赋值，如果期望值不一致，证明数据被修改过，返回fasle，取消赋值
 * 存在ABA问题，若业务场景不允许存在ABA，则要借助 AtomicStampedReference 解决,见{@link AtomicStampedReferenceDemo}
 * @author Zhangchen
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //main  do some thing ...
        System.out.println(atomicInteger.compareAndSet(5,20) + " ,current value : " +atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,15) + " ,current value : " +atomicInteger.get());
        /*true ,current value : 20
          false ,current value : 20*/
    }
}
