package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

/**
 * @Author: lifei
 * @Description: 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * @Date: 2020/9/24
 */
public class LeetCode501 {
    public int[] findMode(TreeNode root) {
        if (root == null)
            return null;
        return new int[getCount(0, root, root.val)];
    }

    public int getCount(int count, TreeNode currentNode, int currentValue) {
        if (currentNode == null)
            return count;
        if (currentValue == currentNode.val)
            return Math.max(getCount(count + 1, currentNode.left, currentValue), getCount(count + 1, currentNode.right, currentValue));
        else {
            int leftMax = 0;
            if (currentNode.left != null) {
                leftMax = getCount(0, currentNode.left, currentNode.left.val);
            }
            int rightMax = 0;
            if (currentNode.right != null) {
                rightMax = getCount(0, currentNode.right, currentNode.right.val);
            }
//            int max = ((max=(count>leftMax)?count:leftMax)>rightMax?max:rightMax);

        }
        return 0;
    }


}
