package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * @Date 2021/9/16
 */
public class LeetCode300 {
    // [0,1,0,3,2,3] 这种情况需要考虑过滤掉最短路径
    public int lengthOfLIS(int[] nums) {
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int temp = 1;
            int cursor = nums[i];
            for (int n = i + 1; n < nums.length; n++) {
                if (cursor < nums[n]) {
                    temp++;
                    cursor = nums[n];
                }
            }
            max = Math.max(max, temp);
        }
        return max;
    }

    //    [0,5,1,0,3,2,3]
//    [4,1,3,3,2,2,1]
    public static int lengthOfLISAnswer(int[] nums) {
        int result[] = new int[nums.length];
        result[nums.length - 1] = 1;
        int max = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = 1;
            for (int n = i + 1; n < nums.length; n++) {
                if (nums[i] < nums[n]) {
                    result[i] = Math.max(result[i], 1 + result[n]);
                }
            }
            max = Math.max(max, result[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 5, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLISAnswer(nums));
    }
}
