package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * @Date 2021/4/28
 */
public class LeetCode633 {
    public boolean judgeSquareSum(int c) {
        for (int i = 0; i * i <= c; i++) {
            for (int n = c - i; n * n <= c && n >= 0; n--) {
                if (n * n + i * i == c)
                    return true;
            }
        }
        return false;
    }
}
