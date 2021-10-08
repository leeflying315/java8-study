package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * @Date 2021/9/28
 */
public class LeetCode560 {
    // 暴力
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int n = 0; n < nums.length; n++) {
            int sum = 0;
            for (int m = n ; m < nums.length; m++) {
                sum = sum + nums[m];

                if (sum == k) {
                    count++;
                    break;
                }
                if (sum > k) {
                    break;
                }
            }
        }
        return count;
    }
}
