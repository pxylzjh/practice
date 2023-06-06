package com.example.distributelock.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author puxy
 * @version 1.0
 * @description zk的事件监听
 * @date 2023/5/24 03:01
 */
public class ZKTest02 {

    /**
     * zk的监听事件有4种(其实是5种，有一种是config配置信息的监听，其余4种是对zk节点的监听)
     * 新增节点事件监听  stat -w /path
     * 删除节点事件监听  stat -w /path
     * 节点数据变化监听  get -w /path
     * 子节点变化监听    ls -w /path
     *
     * 所有的监听只会触发一次，如果想要再次触发需要再次设置监听
     */

    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        CountDownLatch latch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    Event.EventType type = event.getType();
                    Event.KeeperState state = event.getState();
                    if (Event.KeeperState.SyncConnected.equals(event.getState()) && Event.EventType.None.equals(event.getType())) {
                        System.out.println("获取到链接-监听器触发" + event);
                        // 当获取到链接后计数器减1
                        latch.countDown();
                    } else if (Event.KeeperState.Closed.equals(event.getState())) {
                        System.out.println("关闭链接------");
                    } else if (Event.EventType.NodeDataChanged.equals(event.getType())) {
                        // 对节点数据变更事件的监听
                        System.out.println("节点数据变化----------------");
                    }
                }
            });
            // 获取到链接后才开始执行下面的代码
            latch.await();

            // 1. 获取stat
            Stat stat = zooKeeper.exists("/pxy/test_node_1", false);

            // 2. 获取当前节点中的数据，开启节点数据变更事件监听
            byte[] data = zooKeeper.getData("/pxy", true, stat);// 还是只能获取到一级路径下的数据，多级会报错
            System.out.println("当前节点数据：" + new String(data));


            // 监听子节点变化事件
            List<String> children = zooKeeper.getChildren("/pxy", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 我们一般采用这种方式进行监听
                    if (Event.EventType.NodeChildrenChanged.equals(event.getType())) {
                        System.out.println("子节点发生变化--------------");
                    }
                }
            });

            // 这个方法用来让main方法保持不退出，然后再zk客户端修改节点数据，看是否触发
            System.in.read();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }


    }


}
