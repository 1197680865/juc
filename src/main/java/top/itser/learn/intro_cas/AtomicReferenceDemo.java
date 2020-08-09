package top.itser.learn.intro_cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用：将对象作为原子，进行类似 AtomicInteger 的CAS操作
 * @author Zhangchen
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        Student z3 = new Student("z3",23);
        Student li4 = new Student("li4",34);
        AtomicReference<Student> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4) + " ,current student : " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4) + " ,current student : " + atomicReference.get().toString());
        //true ,current student : Student(name=li4, age=34)
        //false ,current student : Student(name=li4, age=34)
    }
}
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Student{
    String name;
    int age;
}