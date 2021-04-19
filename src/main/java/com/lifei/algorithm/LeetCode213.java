package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * @Date 2021/4/15
 */
public class LeetCode213 {
    public int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax = 0;
        int curMax = 0;
        // 从0开始 不读最大值
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = curMax;
            curMax = Math.max(curMax, preMax + nums[i]);
            preMax = temp;
        }
        int preMax2 = 0;
        int curMax2 = 0;
        // 1开始，可以读取最大值
        for (int i = 1; i < nums.length; i++) {
            int temp = curMax2;
            curMax2 = Math.max(curMax2, preMax2 + nums[i]);
            preMax2 = temp;
        }
        return Math.max(curMax, curMax2);
    }

    public int get(int[] nums, int pos, int val) {
        if (pos >= nums.length) {
            return val;
        }
        return Math.max(get(nums, pos + 2, val + nums[pos]), get(nums, pos + 3, val + nums[pos]));
    }

    public static void main(String[] args) {
        int[] height = new int[]{
                1,2,3,1
        };
        LeetCode213 leetCode213 = new LeetCode213();
        System.out.println(leetCode213.rob(height));
    }
}
