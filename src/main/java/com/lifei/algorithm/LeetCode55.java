package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * @Date 2021/4/19
 */
public class LeetCode55 {
    // 贪心算法，求解每个位置能跳的最远距离。
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        int rightmost = 0;

        for (int i = 0; i < nums.length; ++i) {
            // 点睛之笔，限制了i不能越级贪心，如果i之前的i...i-1 都不能跳到i，则返回false
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= nums.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    // 穷举回溯，会超时
    public boolean jump(int[] nums, int pos) {
        if (pos >= nums.length - 1 || pos + nums[pos] >= nums.length - 1) {
            return true;
        }
        if (nums[pos] == 0) {
            return false;
        }
        for (int i = nums[pos]; i >= 1; i--) {
            if (jump(nums, pos + i)) {
                // 及时剪枝
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode55 leetCode55 = new LeetCode55();
        Long startTime = System.currentTimeMillis();
        System.out.println(leetCode55.canJump(new int[]{
                1, 0, 1, 0
        }));
        System.out.println("time cost:" + (System.currentTimeMillis() - startTime));
    }
}
