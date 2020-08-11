package top.itser.learn.intro_transfer_value;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 传值与传引用
 * @author Zhangchen
 */
public class TransferValueDemo {
    public void changeValue1(int age)
    {
        age =15;
    }
    public void changeValue2(Student student){
        student.setName("hahaha");
    }
    public void changeValue3(String s){
        s = "qqq";
    }

    public static void main(String[] args) {
        TransferValueDemo transferValue = new TransferValueDemo();
        int age = 10;
        transferValue.changeValue1(age);
        System.out.println("--- age:" + age);

        Student student = new Student("z3");
        transferValue.changeValue2(student);
        System.out.println("--- student.name:" + student.getName());

        String str = "xxx";
        //不是new String ，“xxx”位于常量池中
        // 八种基本数据类型都存储在栈内存中。八种基本数据类型:byte、short、int、long、float、double、boolean、char。
        //栈 stack：存储基本数据类型、方对象的引用变量。数据一执行完毕，变量会立即释放，节约内存空间。
        //堆 heap：new创建的对象和数组，所有的实体都有内存地址值。堆内存中的实体不再被指向时，JVM启动垃圾回收机制，自动清除
        transferValue.changeValue3(str);
        System.out.println("--- str:" + str);
//        --- age:10
//        --- student.name:hahaha
//        --- str:xxx
    }
}

@Data
@AllArgsConstructor
class Student{
    private String name;
}