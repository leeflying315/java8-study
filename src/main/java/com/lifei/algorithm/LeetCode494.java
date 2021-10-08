package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * @Date 2021/9/27
 */
public class LeetCode494 {
    int count = 0;
    int nums[];
    int target;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        addCount(0,0);
        return count;
    }

    private void addCount(int pos, int curValue) {
        if (pos == nums.length) {
            if (curValue == target) {
                count++;
            }
            return;
        }
        addCount(pos + 1, curValue + nums[pos]);
        addCount(pos + 1, curValue - nums[pos]);
    }
}
