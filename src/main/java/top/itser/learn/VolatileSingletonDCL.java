package top.itser.learn;

/**
 * volatile 的使用场景： 单例模式-DCL双检锁模式
 */
public class VolatileSingletonDCL {
    //指令重排机制会让程序很小的几率出现构造方法被执行多次
    //必须加入volatile，因为instance在创建非原子操作，需要禁止其重排序，保证有序性
    private static volatile VolatileSingletonDCL instance =null;
    private VolatileSingletonDCL(){
        System.out.println("构造函数，初始化对象");
    }

    //单例模式-双检锁模式
    public static VolatileSingletonDCL getInstance(){
        if (null == instance)
        {
            synchronized (VolatileSingletonDCL.class)
            {
                if (null == instance)
                {
                    instance = new VolatileSingletonDCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++)
        {
            new Thread(()->{
                VolatileSingletonDCL.getInstance();
            }).start();
        }
    }
}
