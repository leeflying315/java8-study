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

    // 递归方式求解，存在大量重复计算，如 玩家一选择左边
    private int predictFuture(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];
        else {
            int leftOne = nums[left] - predictFuture(nums, left + 1, right); // 选择左侧
            int rightOne = nums[right] - predictFuture(nums, left, right - 1);
            return Math.max(leftOne, rightOne);
        }
    }

    // 使用二维数组，动态规划计算
    public boolean predictTheWinner(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][length - 1] >= 0;
    }

    /*
     * dp[i][j] 的值只和dp[i+1][j] 与 dp[i][j−1] 有关，
     * 即在计算 dp 的第 i 行的值时，只需要使用到dp 的第 i行和第 i+1行的值，
     * 因此可以使用一维数组代替二维数组，对空间进行优化。
     *
     * */
    public boolean predictTheWinner2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    public static void main(String[] args) {
        Leetcode486 leetcode486 = new Leetcode486();

    }
}
