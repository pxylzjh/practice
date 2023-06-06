package com.example.distributelock.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author puxy
 * @version 1.0
 * @description zk的基本操作
 * @date 2023/5/24 03:01
 */
public class ZKTest01 {


    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        CountDownLatch latch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    Event.EventType type = event.getType();
                    Event.KeeperState state = event.getState();
                    if (Event.KeeperState.SyncConnected.equals(event.getState())) {
                        System.out.println("获取到链接-监听器触发" + event);
                        // 当获取到链接后计数器减1
                        latch.countDown();
                    } else if (Event.KeeperState.Closed.equals(event.getState())) {
                        System.out.println("关闭链接------");
                    }
                }
            });
            // 获取到链接后才开始执行下面的代码
            latch.await();

            // 1.新增节点： 永久节点  临时节点 永久序列化节点  临时序列化节点
//            String s = zooKeeper.create("/pxy/test_node_1", // 节点路径 不支持创建多级节点，只能先创建好/pxy 否则报错
//                    "我是永久节点".getBytes(), // 节数据
//                    ZooDefs.Ids.OPEN_ACL_UNSAFE,// 访问权限
//                    CreateMode.PERSISTENT);// 节点类型 PERSISTENT(永久节点)

            // 2.查询节点：
            // 2.1 判断节点是否存在
            Stat stat = zooKeeper.exists("/pxy/test_node_1", false);
            if (stat != null) {
                System.out.println("存在");
            } else {
                System.out.println("不存在");
            }
            // 2.2 获取当前节点中的数据
            byte[] data = zooKeeper.getData("/pxy", false, stat);// 还是只能获取到一级路径下的数据，多级会报错
            System.out.println("当前节点数据：" + new String(data));
            // 2.3 获取当前节点的子节点
            List<String> children = zooKeeper.getChildren("/pxy", false);
            System.out.println("当前节点的子节点：" + children);

            // 3. 更新 : 更新的时候需要指定版本号，如果版本号和 当前节点的版本号不一致，更新失败，或者设置为 -1 表示不校验版本号
            Stat stat1 = zooKeeper.setData("/pxy", "456".getBytes(), stat.getVersion());

            // 4. 删除 : 删除的时候 需要保证当前节点下没有子节点，否则无法删除
            zooKeeper.delete("/pxy/test_node_1",-1);

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
