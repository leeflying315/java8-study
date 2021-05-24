package com.lifei.algorithm;

import com.lifei.algorithm.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lifei
 * @Description: 897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * @Date 2021/4/25
 */
public class LeetCode897 {
//    public TreeNode increasingBST(TreeNode root) {
//
//        TreeNode t = new TreeNode();
//
//        printBST(root, t);
//        return t;
//    }

    public void printBST(TreeNode node, TreeNode copy) {
        if (node == null)
            return;

        if (node.left != null) {
            printBST(node.left, copy);
        }
        copy.val = node.val;
        copy.right = new TreeNode();
        copy = copy.right;

        if (node.right != null) {
            printBST(node.right, copy.right);
        }
    }

    // 利用list顺序遍历特性再处理一遍
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);

        TreeNode dummyNode = new TreeNode(-1);
        TreeNode currNode = dummyNode;
        for (int value : res) {
            currNode.right = new TreeNode(value);
            currNode = currNode.right;
        }
        return dummyNode.right;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    TreeNode resNode;
    // 中序遍历过程中改变指向
    public TreeNode increasingBST2(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }


    public static void main(String[] args) {

    }
}
