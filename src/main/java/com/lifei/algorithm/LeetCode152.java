package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * @Date 2021/7/16
 */
public class LeetCode152 {
    public int maxProduct(int[] nums) {
        if (nums.length < 2)
            return nums[0];
        return getValue(nums, 0, nums[0]);
    }

    private int getValue(int[] nums, int pos, int max) {
        if (pos >= nums.length) {
            return max;
        } else {
            max = Math.max(max, getMaxValue(pos + 1, nums));
            return getValue(nums, pos + 1, max);
        }
    }

    private Integer getMaxValue(int end, int[] nums) {
        if (end >= nums.length) {
            return Integer.MIN_VALUE;
        }
        int max = nums[end];
        int curValue = nums[end];

        int pos = end - 1;
        while (pos >= 0) {
            curValue = curValue * nums[pos];

            if (nums[pos] == 0) {
                return max;
            }
            if (max < curValue) {
                max = curValue;
            }
            pos--;

        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode152 leetCode152 = new LeetCode152();
        int[] test = new int[]{-2,0,-1};

        System.out.println(leetCode152.maxProduct(test));
    }
}
