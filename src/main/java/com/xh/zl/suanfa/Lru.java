package com.xh.zl.suanfa;

/**
 * @Author:zl
 * @Description 基于链表实现LRU算法（Least Recently Used） 最近最久未使用
 * @Date: 2019/12/4 15:05
 */
public class Lru {
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

}


