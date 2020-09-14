package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        return getRightNums(res, target, 0, list, candidates);
    }

    private static List<List<Integer>> getRightNums(List<List<Integer>> list, int target, int index, List<Integer> temp, int[] candidates) {
        if (target == 0) {
            list.add(temp);
            return list;
        }
        if (index >= target) {
            list.remove(list.size() - 1);
            return list;
        }
        target = target - candidates[index];
        if (target == 0) {
            list.add(temp);
        } else if (target > 0) {
            temp.add(candidates[index]);
            getRightNums(list, target, index, temp, candidates);
            getRightNums(list, target, index + 1, temp, candidates);
        } else {
//            list.remove(list.size() - 1);
            return list;
        }
        return list;
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
