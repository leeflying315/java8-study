package com.lifei.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author lifei
 * @Description: 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * @Date 2021/9/26
 */
public class LeetCode416 {
    int[] input;
    int count = 1;

    public boolean canPartition(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        if (sum % 2 != 0) {
            return false;
        }
        System.out.println("sum is " + sum);
        int target = sum / 2;
        input = nums;
        return test(target, 0);
    }

    // 回溯法
    private boolean test(int target, int pos) {

        System.out.println("times:" + count + "\ttarget :" + target + "\t pos:" + pos);
        count++;
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        if (pos >= input.length) {
            return false;
        }

        boolean result2 = test(target, pos + 1);
        boolean result = test(target - input[pos], pos + 1);
        return result || result2;
    }

    public static void main(String[] args) {
        LeetCode416 leetCode416 = new LeetCode416();

        int[] nums = new int[]{
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 99, 97
        };
        System.out.println(leetCode416.canPartition(nums));
    }
}
