package com.lifei.algorithm;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组candidates和一个目标数target，
 * 找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * @Author: lifei
 * @Description:
 * @Date: 2020/9/10
 */
public class LeetCode40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int length = candidates.length;
        if (length == 0)
            return list;


        // 排序 剪枝
        Arrays.sort(candidates);

        Deque<Integer> deque = new ArrayDeque<>();
        dfs(candidates, target, list, 0, deque);
        return list;
    }

    private static void dfs(int[] candidates, int target, List<List<Integer>> list, int beginIndex, Deque<Integer> deque) {
        if (target < 0)
            return;

        if (target == 0) {
            list.add(new ArrayList<>(deque));
            return;
        }

        for (int i = beginIndex; i < candidates.length; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1])
                continue;
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > beginIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // 先添加上
            deque.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], list, i + 1, deque);
            // 在本层for循环 重置I的状态
            deque.removeLast();
        }
    }
}
