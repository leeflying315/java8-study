package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lifei
 * @Description: 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。实现 LRUCache 类：
 * @Date 2021/5/13
 */
public class LeetCode146 {
    HashMap<Integer, Integer> set;
    List<Integer> list;

    Integer MAX_SIZE;

    // 实现堆
    public LeetCode146(int capacity) {
        MAX_SIZE = capacity;
        set = new HashMap<>(capacity);
//        queue = new int[capacity];
        list = new ArrayList<>();
    }

    public int get(int key) {
        if (set.containsKey(key)) {
            int result = set.get(key);

            list.remove((Integer) key);
            list.add(key);
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (set.containsKey(key)) {
            list.remove((Integer) key);
        } else {
            if (list.size() >= MAX_SIZE) {
                Integer tempKey = list.get(0);
                list.remove( tempKey);
                set.remove(tempKey);
            }
        }
        set.put(key, value);
        list.add(key);
        System.out.println(list);
    }

    public static void main(String[] args) {
        LeetCode146 leetCode146 = new LeetCode146(2);
        leetCode146.put(1,1);
        leetCode146.put(2,2);
        System.out.println(leetCode146.get(1));
        leetCode146.put(3,3);
    }
}
