package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 1221. 分割平衡字符串
 * 在一个 平衡字符串中，'L' 和 'R' 字符的数量是相同的。
 * 给你一个平衡字符串s，请你将它分割成尽可能多的平衡字符串。
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 * @Date 2021/9/7
 */
public class LeetCode1221 {
    public int balancedStringSplit(String s) {
        int count = 0;
        int leftTemp = s.charAt(0) == 'L' ? 1 : 0;
        int rightTemp = leftTemp == 1 ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                leftTemp++;
            } else {
                rightTemp++;
            }
            if (leftTemp == rightTemp) {
                leftTemp = 0;
                rightTemp = 0;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode1221 leetCode1221 = new LeetCode1221();
        System.out.println(leetCode1221.balancedStringSplit("RLLLLRRRLR"));
    }
}
