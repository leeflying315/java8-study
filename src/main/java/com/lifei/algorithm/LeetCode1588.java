package com.lifei.algorithm;

/**
 * @Author lifei
 * @Date 2021/8/29
 * @Description: 1588. 所有奇数长度子数组的和
 * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
 * <p>
 * 子数组 定义为原数组中的一个连续子序列。
 * <p>
 * 请你返回 arr中 所有奇数长度子数组的和 。
 */
public class LeetCode1588 {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int m = 1; m <= arr.length; m += 2) {
            int left = 0;
            // 从0开始第一个。
            int right = m - 1;
            for (; right < arr.length; right++, left++) {
                for (int temp = left; temp <= right; temp++) {
                    sum = sum + arr[temp];
                }
            }
        }
        return sum;
    }
}
