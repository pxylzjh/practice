package com.pxy.thread.unsafe;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author puxy
 * @version 1.0
 * @description CAS解决 ABA 问题：采用带版本号的 原子引用 AtomicStampedReference
 * @date 2023/2/23 18:15
 */
public class SolveABA {

    public static void main(String[] args) {

        // TestAtomicReference(); // 普通原子引用，无法解决 ABA

        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

        // t1 进行 CAS 操作，将 100 修改为 101，版本号+1
        new Thread(() -> {
            System.out.println("t1 CAS前的值：" + atomicStampedReference.getReference() + " 版本号：" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(
                    100, // CAS 前的值
                    101, // CAS 后的值
                    atomicStampedReference.getStamp(), // CAS 前的 版本号
                    atomicStampedReference.getStamp() + 1);// CAS 后的 版本号
            System.out.println("t1第一次修改后的值：" + atomicStampedReference.getReference() + " 版本号：" + atomicStampedReference.getStamp());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicStampedReference.compareAndSet(
                    101, // CAS 前的值
                    100, // CAS 后的值
                    atomicStampedReference.getStamp(), // CAS 前的 版本号
                    atomicStampedReference.getStamp() + 1);// CAS 后的 版本号
            System.out.println("t2第二次修改后的值：" + atomicStampedReference.getReference() + " 版本号：" + atomicStampedReference.getStamp());
        }, "t1").start();


        // t2 进行 CAS 操作，将 101 修改为 1001，版本号+1， 如果 发生 ABA 问题 则 CAS 操作失败，返回false
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t2 CAS前的值：" + atomicStampedReference.getReference() + " 版本号：" + stamp);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean res = atomicStampedReference.compareAndSet(
                    100, // CAS 前的值
                    1001, // CAS 后的值
                    stamp, // CAS 前的 版本号
                    stamp + 1);// CAS 后的 版本号
            System.out.println("修改成功否："+ res +" t2 CAS后的值：" + atomicStampedReference.getReference() + " 版本号：" + stamp);
        }, "t2").start();

    }


    // 普通 原子引用 无法解决ABA问题
    private static void TestAtomicReference() {
        User z3 = new User("z3", 25);
        User li4 = new User("li4", 25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference);
        System.out.println(atomicReference.compareAndSet(z3, li4) +
                "       " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(li4, z3) +
                "       " + atomicReference.get().toString());
    }
}

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
