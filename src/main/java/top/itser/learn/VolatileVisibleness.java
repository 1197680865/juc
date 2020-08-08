package top.itser.learn;


import java.util.concurrent.TimeUnit;

/**
 * 验证 volatile 的可见性
 * @author Zhangchen
 */
public class VolatileVisibleness {
    public static void main(String[] args) {
        final MyData myData = new MyData();
        // Hanhan 线程 Sleep 3秒后，执行方法 addTo60
        new Thread(new Runnable() {
            public void run() {
                System.out.println( Thread.currentThread().getName() + ":start.");
                try {
                    TimeUnit.SECONDS.sleep(3);
                    myData.addTo60();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":over. MyData is " + myData.num);
            }
        },"Hanhan-Thread").start();

        //主线程 当myData.num
        while (myData.num ==0)
        {

        }
        System.out.println(Thread.currentThread().getName() + ":over. get value ："+ myData.num);


        /**
         *   <p>jdk1.5之前，使用这个例子是可以体现出volatile对共享变量的可见性，1.5对volatile的语义进行了增加，新增加了一个happens-before规则：
         *   前面的操作happens -before后面的操作，所以这里体现不出内存的不可见性。</p>
         *
         *   <p>但这里可以看出，加与不加volatile时，执行顺序是不一致的：
         *   1.加volatile
         *      Hanhan-Thread:start.
         *      Hanhan-Thread:over. MyData is 60
         *      main:over. get value ：60
         *   2. 不加 volatile
         *      Hanhan-Thread:start.
         *      main:over. get value ：60
         *      Hanhan-Thread:over. MyData is 60
         *   </p>
         */
    }
}


class MyData{
//      int num =0;
    volatile int num =0;

    public void addTo60(){
        this.num = 60;
    }
}
