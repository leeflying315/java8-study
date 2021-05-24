package com.lifei.algorithm;

import java.util.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 *
 * @Author: lifei
 * @Description:
 * @Date: 2020/9/11
 */
public class LeetCode39 {

    public static class LeetCodePersonal39 {

        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            Deque<Integer> deque = new ArrayDeque<>();
            dfs(candidates, target, res, 0, deque);
            return res;
        }

        private static void dfs(int[] candidates, int target, List<List<Integer>> res, int beginIndex, Deque<Integer> deque) {
            if (target < 0)
                return;
            if (target == 0) {
                res.add(new ArrayList<>(deque));
                return;
            }
            for (int i = beginIndex; i < candidates.length; i++) {
                deque.addLast(candidates[i]);
                dfs(candidates, target - candidates[i], res, i, deque);
                deque.removeLast();
            }
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 避免结果集重复
        Arrays.sort(candidates);
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 双端队列
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            // 预制当前候选数据
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置，删除预制节点
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
