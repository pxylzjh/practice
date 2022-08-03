package leecode.test;

import java.util.HashMap;

/**
 * @author puxy
 * @version 1.0
 * @description 自定义LRU对象
 * @date 2021/7/10 15:49
 */
public class LRUCache<K,V>{

    //容量
    private int capacity=16;

    private int size = 0;

    //存放元素的集合
    private HashMap<K,Node<K,V>> map = new HashMap<>(capacity);

    private Node<K,V> lruNode = new Node<K, V>();
    private Node<K,V> mruNode = new Node<K, V>();


    public LRUCache(){

    }
    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    /**
     * 添加元素
     * 用过了就移动到前面
     * 如果容量满了,则删除后面的元素
     * @param key
     * @param value
     */
    public void put(K key,V value){
        if(map.containsKey(key)){
            Node<K,V> temp = map.get(key);
            temp.value = value;
            move2mru(temp);
            return;
        }

        //容量满了清除后面的
        if(size >= capacity){
            evict4lru();
        }
        Node<K,V> newNode = new Node<>(mruNode,null,key,value);
        if(size == 0){
            lruNode.next = newNode;
            newNode.pre = lruNode;
        }
        mruNode.next = newNode;
        mruNode = newNode;
        map.put(key,newNode);
        size++;
    }

    /**
     * 获取元素
     * 返回元素值,并将元素移动到前面
     * @return
     */
    public Object get(K key){

        Node<K, V> node = map.get(key);
        if (node == null){
            return -1;
        }
        //移动到前面
        move2mru(node);
        return node.value;
    }

    /**
     * 移动到前面
     * @param newMru
     */
    private void move2mru(Node<K,V> newMru){
        Node<K,V> pre = newMru.pre;
        Node<K,V> next = newMru.next;
        pre.next = next;
        next.pre = pre;
        newMru.pre = mruNode;
        mruNode.next = newMru;
        mruNode = newMru;
    }

    /**
     * 删除末尾的元素
     */
    private void evict4lru(){
        map.remove(lruNode.next.key);
        lruNode.next = lruNode.next.next;
        size--;
    }
}

class Node<K,V>{
    K key;
    V value;
    Node<K,V> pre;
    Node<K,V> next;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }

    public Node(){
    }

    public Node(Node<K,V> pre, Node<K,V> next, K key, V value){
        this.pre = pre;
        this.next = next;
        this.key = key;
        this.value = value;
    }
}
