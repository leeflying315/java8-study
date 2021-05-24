package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

/**
 * @Author lifei
 * @Description: 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * @Date 2021/4/27
 */
public class LeetCode938 {
    Integer sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        print(root, low, high);
        return sum;
    }

    public void print(TreeNode root, int low, int high) {
        if (root == null)
            return;
        if (root.val > low) {
            print(root.left, low, high);
        }
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val < high) {
            print(root.right, low, high);
        }
    }

    public static void main(String[] args) {

    }
}
