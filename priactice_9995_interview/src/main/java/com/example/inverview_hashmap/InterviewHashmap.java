package com.example.inverview_hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName InterviewHashmap
 * @Description TODO
 * @Author zhouguodong
 * @Date 2022/1/13 14:59
 * @Version 1.0
 **/
public class InterviewHashmap {

    /**
     * HashMap 底层是数组和链表或者红黑树
     * <p>
     * <p>
     * 数组代表
     * transient Node<K,V>[] table;
     * <p>
     * <p>
     * 链表结构
     * <p>
     * static class Node<K,V> implements Map.Entry<K,V> {
     * final int hash;
     * final K key;
     * V value;
     * Node<K,V> next;
     * <p>
     * Node(int hash, K key, V value, Node<K,V> next) {
     * this.hash = hash;
     * this.key = key;
     * this.value = value;
     * this.next = next;
     * }
     * <p>
     * public final K getKey() { return key; }
     * public final V getValue() { return value; }
     * public final String toString() { return key + "=" + value; }
     * <p>
     * public final int hashCode() {
     * return Objects.hashCode(key) ^ Objects.hashCode(value);
     * }
     * <p>
     * public final V setValue(V newValue) {
     * V oldValue = value;
     * value = newValue;
     * return oldValue;
     * }
     * <p>
     * public final boolean equals(Object o) {
     * if (o == this)
     * return true;
     * if (o instanceof Map.Entry) {
     * Map.Entry<?,?> e = (Map.Entry<?,?>)o;
     * if (Objects.equals(key, e.getKey()) &&
     * Objects.equals(value, e.getValue()))
     * return true;
     * }
     * return false;
     * }
     * }
     * <p>
     * <p>
     * <p>
     * **
     * * 红黑树结构
     * *
     * <p>
     * static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {
     * <p>
     * TreeNode<K, V> parent; // red-black tree links
     * <p>
     * TreeNode<K, V> left;
     * <p>
     * TreeNode<K, V> right;
     * <p>
     * TreeNode<K, V> prev; // needed to unlink next upon deletion
     * <p>
     * boolean red;
     * ...
     */


    public static void main(String[] args) {
        HashMap map = new HashMap(16);
        ConcurrentHashMap<String, String> mp = new ConcurrentHashMap<String, String>();
        for (int i = 30; i <= 33; i++) {
            System.out.println("1 << " + i + "=" + (1 << i));
        }
    }

    /**
     * ReentrantLock
     */

    private final ReentrantLock lock = new ReentrantLock();
    public void m () {
        lock.lock();  // block until condition holds
        boolean locked = lock.isLocked();
        try {
            // ... method body
        } finally {
            lock.unlock();
        }
    }

    Hashtable
}
