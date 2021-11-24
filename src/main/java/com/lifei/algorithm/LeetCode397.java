package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 397. 整数替换
 * 给定一个正整数n ，你可以做如下操作：
 * 如果n是偶数，则用n / 2替换n 。
 * 如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 * @Date 2021/11/19
 */
public class LeetCode397 {


    public int integerReplacement(int n) {
        if (n <= 1) {
            return 0;
        }
        int count =
                getCount(n, 0);
        return count;
    }

    private int getCount(int n, int count) {
        if (n == 1) {
            return count;
        }
        if (n % 2 == 0) {
            return getCount(n / 2, count + 1);
        } else {
            // 注意剪枝
            return Math.min(getCount(n / 2, count + 2), getCount(n / 2 + 1, count + 2));
        }
    }

    public static void main(String[] args) {
        LeetCode397 leetCode397 = new LeetCode397();
        System.out.println(leetCode397.integerReplacement(65536));
    }
}
