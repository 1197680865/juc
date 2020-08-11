package top.itser.learn.intro_collection;
import java.util.*;

/**
 * 集合不安全-List
 * @author Zhangchen
 */
public class ListNoSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i=0;i<5;i++)
        {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
        /**
         * <p>异常： ConcurrentModificationException 。java.util.ConcurrentModificationException</p>
         *
         * <p>解决方案：</p>
         * 1.使用Vector
         *   Vector<String> vector = new Vector<>();
         *   vector的add方法是synchronized
         *   但是vetor的效率不高
         *
         * 2.使用集合工具类生成安全的集合
         *     List<String> list = Collections.synchronizedList(new ArrayList<>());
         *
         * 3.写时复制List  CopyOnWriteArrayList
         *       list = new CopyOnWriteArrayList<>();
         *       特点：读写分离，在写时先拷贝，扩充后在覆盖原数组
         *          适用于读多写少的场景
         *
         */
    }
}
