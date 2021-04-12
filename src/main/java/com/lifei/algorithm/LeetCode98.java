package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lifei
 * @Description: 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * @Date 2021/4/8
 */
public class LeetCode98 {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addValue(root, list);
        System.out.println(list);

        if (list.size() <= 1)
            return true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1))
                return false;
        }
        return true;
    }

    public void addValue(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        if (root.left != null) {
            addValue(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            addValue(root.right, list);
        }
    }

    public boolean compareLess(TreeNode left, int curVal) {
        if (left == null)
            return true;
        if (left.val >= curVal) {
            return false;
        }
        return compareLess(left.left, curVal) && compareLess(left.right, curVal);
    }

    public boolean compareBig(TreeNode right, int curVal) {
        if (right == null)
            return true;
        if (right.val <= curVal) {
            return false;
        }
        return compareLess(right.right, curVal) && compareLess(right.left, curVal);
    }

    public boolean compare(TreeNode left, TreeNode right, int curVal) {
        if (left != null && left.val >= curVal) {
            return false;
        }
        if (right != null && right.val <= curVal) {
            return false;
        }
        if (left == null && right == null)
            return true;
        return false;
    }

    public static void main(String[] args) {
        LeetCode98 leetCode98 = new LeetCode98();
    }
}
