package com.lifei.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lifei
 * @Description: 138. 复制带随机指针的链表
 * @Date 2021/7/22
 */
public class LeetCode138 {
    Map<Node, Integer> map = new HashMap<>();
    Map<Integer, Node> copyMap = new HashMap<>();

    public Node copyRandomList(Node head) {

        if (head == null)
            return null;
        Node temp = head;

        Node copyHeader = new Node(head.val);
        Node copy = copyHeader;
        int i = 0;
        map.put(head, i);
        copyMap.put(i, copyHeader);
        // 下一个如果为空，实际上当前节点已经保存了。因为上一个节点的下一个节点也就是当前节点一定不为空。
        while (temp.next != null) {
            temp = temp.next;
            i++;
            map.put(temp, i);
            Node t = new Node(temp.val);
            copyMap.put(i, t);
            copy.next = t;
            copy = copy.next;
        }
        temp = head;
        copy = copyHeader;
        // 不能使用下一个节点为空判断，因为最后一个节点的下一个节点一定为空，但是random可能不为空
        while (temp != null) {
            Node randomNode = temp.random;
            if (randomNode != null) {
                Integer index = map.get(randomNode);
                copy.random = copyMap.get(index);
            }
            temp = temp.next;
            copy = copy.next;
        }

        return copyHeader;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
