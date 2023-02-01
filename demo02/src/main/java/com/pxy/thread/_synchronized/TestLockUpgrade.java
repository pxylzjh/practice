package com.pxy.thread._synchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 测试synchronized锁升级
 * @date 2022/12/3 22:52
 */
public class TestLockUpgrade {


    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();
        System.out.println("无锁状态");
        // 由于偏向锁在应用程序启动几秒钟之后才会激活所以此时创建的 obj 对象是non-biasable 不会升级成偏向锁，直接升级成 thin lock 轻量级锁
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        // 确保 偏向锁 生效
        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // 没有其他线程竞争锁
        synchronized (lock) {
            System.out.println("偏向锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        // 其它线程竞争锁
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("轻量级锁");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                // 暂停2s确保 t2 线程阻塞并自旋
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        // 暂停 1s 确保 t1 能打印出 轻量级锁的信息 避免 t2 过早竞争 导致 升级为 重量级锁
        TimeUnit.SECONDS.sleep(1);

        // 线程自旋获取锁，自旋一定次数后升级为重量级锁
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("重量级锁");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        t2.start();

    }
}
