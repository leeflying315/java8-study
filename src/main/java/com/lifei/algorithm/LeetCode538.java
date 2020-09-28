package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

/**
 * @Author: lifei
 * @Description: 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * @Date: 2020/9/21
 */
public class LeetCode538 {
    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;
        TreeNode target = new TreeNode(root.val + getSumValue(root, root.val));
        deepCopy(root, root,target);
        return target;
    }

    public Integer getSumValue(TreeNode root, int compareVal) {
        if (root != null) {
            if (root.val > compareVal)
                return root.val + getSumValue(root.left, compareVal) + getSumValue(root.right, compareVal);
            return getSumValue(root.left, compareVal) + getSumValue(root.right, compareVal);
        } else
            return 0;
    }

    public void deepCopy(TreeNode original,TreeNode root, TreeNode target) {
        if (original == null)
            return;
        if (original.left != null)
            target.left = new TreeNode(original.left.val + getSumValue(root, original.left.val));
        if (original.right != null)
            target.right = new TreeNode(original.right.val + getSumValue(root, original.right.val));
        deepCopy(original.left, root,target.left);
        deepCopy(original.right, root, target.right);
    }

    // 优化后：
    // 二叉搜索树都是左侧小于右侧，因此先用后续遍历到右侧
    int add = 0;

    public TreeNode convertBSTAnswer(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        root.val += add;
        add = root.val;
        convertBST(root.left);
        return root;
    }
}
