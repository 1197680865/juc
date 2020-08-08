package top.itser.learn;

/**
 * 演示指令重排序
 * <p>多线程环境下，可能发生指令重排序，
 * {@link VolatileNoResortSeq#init()} 语句1 和语句2切换顺序</p>
 * <p>对变量声明 volatile 后，可禁止指令重排</p>
 */
public class VolatileNoResortSeq {
    private int a =0;
    private boolean f = false;

    private void init(){
        a =1; //语句1
        f = true; //语句2
    }

    private void call(){
        if (f)
        {
            a = a +5; //语句3
            System.out.println(a);
        }
    }
}
