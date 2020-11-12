package com.lifei.algorithm;

import com.lifei.algorithm.support.ListNode;

/**
 * @Author: lifei
 * @Description: 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * @Date: 2020/10/9
 */
public class LeetCode141 {
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode oneStep = head.next;
        if (oneStep == null)
            return false;
        ListNode twoStep = head.next.next;

        while (oneStep != null && twoStep != null && oneStep != twoStep) {
            oneStep = oneStep.next;
            if (twoStep.next != null) {
                twoStep = twoStep.next.next;
            } else {
                return false;
            }
        }
        if (oneStep == null || twoStep == null)
            return false;
        return true;
    }
}
