package com.lifei.algorithm;

import com.lifei.algorithm.support.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lifei
 * @Description: 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * @Date: 2020/10/10
 */
public class LeetCode142 {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (true) {
            if (head == null)
                return null;
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
                head = head.next;
            }
        }

    }
}
