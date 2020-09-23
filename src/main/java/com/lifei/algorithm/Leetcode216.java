package com.lifei.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: lifei
 * @Description: 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * @Date: 2020/9/18
 */
public class Leetcode216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combineList(res, new ArrayDeque<>(), k, n, 1, 0);
        return res;
    }

    public void combineList(List<List<Integer>> res, Deque<Integer> temp, int k, int target, int index, int forEachVar) {
        if (target < 0 || forEachVar > k)
            return;
        if (target == 0 && forEachVar == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < 10; i++) {
            temp.add(i);
            combineList(res, temp, k, target - i, i + 1, forEachVar + 1);
            temp.removeLast();
        }
    }

    public static void main(String[] args) {
        Leetcode216 leetcode216 = new Leetcode216();
        System.out.println(leetcode216.combinationSum3(3,7));
    }
}
