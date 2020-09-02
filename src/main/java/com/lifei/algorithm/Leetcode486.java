package com.lifei.algorithm;

//  预测赢家
// 给定一个表示分数的非负整数数组。
// 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
// 然后玩家 1 拿，…… 。
// 每次一个玩家只能拿取一个分数，分
// 数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
public class Leetcode486 {
    public boolean PredictTheWinner(int[] nums) {
        return predictFuture(nums, 0, nums.length - 1) >= 0;
    }

    private int predictFuture(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];
        else {
            int leftOne = nums[left] - predictFuture(nums, left + 1, right); // 选择左侧
            int rightOne = nums[right] - predictFuture(nums, left, right - 1);
            return Math.max(leftOne, rightOne);
        }
    }

    public static void main(String[] args) {
        Leetcode486 leetcode486 = new Leetcode486();

    }
}
