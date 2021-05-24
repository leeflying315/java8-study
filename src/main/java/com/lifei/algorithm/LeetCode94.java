package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lifei
 * @Description: 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * @Date 2021/4/25
 */
public class LeetCode94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        print(root, list);
        return list;
    }

    public void print(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        print(root.left, list);
        list.add(root.val);
        print(root.right, list);
    }
}
