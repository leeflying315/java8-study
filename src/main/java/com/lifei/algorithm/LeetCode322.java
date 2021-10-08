package com.lifei.algorithm;

import java.util.Arrays;

/**
 * @Author lifei
 * @Description:3 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * @Date 2021/9/22
 */
public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        Arrays.sort(coins);
        // 存在 [2,7] 10的情况
        // 5个2是可以，但是10 被7 减去就会失败
        //[186,419,83,408]
        // 6249
        // 预期结果20
        // 中间跳跃的情况无法处理
        // 此处需要回溯
        for (int m = coins.length - 1; m >= 0; m--) {
            int count = 0;
            int temp = amount;
            for (int i = m; i >= 0; i--) {
                if (temp < coins[i]) {
                    continue;
                }
                count += temp / coins[i];
                temp = temp % coins[i];
                if (temp == 0) {
                    return count;
                }
            }
        }
        return -1;
    }

    public int coinChangeAnswer(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        int test[] = new int[]{186,419,83,408};
        System.out.println(leetCode322.coinChange(test,6249));
    }
}
