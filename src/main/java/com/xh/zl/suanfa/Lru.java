package com.xh.zl.suanfa;

import sun.awt.AWTCharset;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zl
 * @Description 基于链表实现LRU算法（Least Recently Used） 最近最久未使用
 * 主要的功能有添加、删除、变更、查询、采用双向链表进行实现，根据FIFO的原则，新增的元素添加到链表的head,变更数据将节点数据覆盖并将该节点移动到head节点
 * 始终保持链表头部保留最近访问的数据
 * @Date: 2019/12/4 15:05
 */
public class Lru {
    private int capatity;//容器的大小
    public int currentSize;//当前容器大小，元素可数不可超过容器大小
    private Node head;//头节点
    private Node tail;//尾部文件
    private Map<Object,Node> caches;//用来缓存数据
    public Lru(int size){
        this.capatity = size;
        caches = new HashMap<>(size);
    }

    /**
     * 添加元素:如何不存在的元素，直接进行添加，保证不超过容量大小，如何存在则直接覆盖旧值
     * @param key
     * @param value
     */
    public void add(Object key ,Object value){
        Node node = caches.get(key);
        if(null == node){//如果元素不存在
            node = new Node(key,value);
            if(currentSize >= capatity){//超过容量
                //删除最后一个元素
                removeLast();
                caches.remove(tail.key);
            }
        }
        node.value = value;//
        //添加新元素
        addTohead(node);
        caches.put(key,node);
    }
    public void update(Object key ,Object value){
        Node node = caches.get(key);
        if(null == node){ //如果不存在
            add(key,value);
            return;
        }
        node.value = value;
        moveToHead(node);
    }
    public void remove(Object key){
        Node node = caches.get(key);
        if(null == node){
            return;
        }
        if( node.pre != null){
            node.pre.next = node.next;
        }
        if(node.next != null){
            node.next.pre = node.pre;
        }
        if(node == head){
            head = head.next;
            head.pre = null;
        }
        if(node == tail){//删除尾结点
            tail = tail.pre;
            tail.next.pre = null;
            return;
        }
        caches.remove(key);
        currentSize--;
    }
    public Object get(Object key){
        Node node = caches.get(key);
        if(node == null){
            return null;
        }
        moveToHead(node);
        return node.value;
    }
    private void moveToHead(Node node){
        if(node == head){
            return;
        }
        remove(node.key);
        addTohead(node);
    }
    private void removeLast(){
        if(tail == null){
            return;
        }
        if(head == tail){//只有一个节点
            head = null;
            tail = null;
            return;
        }
        tail = tail.pre;
        tail.next.pre = null;
        tail.next = null;
    }
    private void addTohead(Node node){
        if(head == null || tail == null){//链表中无元素
            head = node;
            tail = node;
            return;
        }
        if(head == tail){
            tail = node;
        }
        node.next = head;
        head.pre = node;
        head = node;
        head.pre = null;
        currentSize++;
    }
    /**
     * 链表使用节点Node
     */
   private static class Node{
        Object key;
        Object value;
        Node pre;
        Node next;
        public Node(Object key,Object value){
            this.key = key;
            this.value = value;
        }
    }
    public String getContent(){
        StringBuffer sb = new StringBuffer();
        if(head == null){
            return "";
        }
        Node item = head;
        while (item != null){
            sb.append(" ( "+item.key+", "+ item.value + ")");
            item = item.next;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Lru cols = new Lru(10);
        cols.add("n1","v1");
        cols.add("n2","v2");
        cols.add("n3","v3");
        cols.add("n4","v4");
        cols.add("n5","v5");
        System.out.println(cols.getContent());
        cols.get("n3");
        System.out.println(cols.getContent());
        cols.update("n2","s2");
        System.out.println(cols.getContent());
    }
}


