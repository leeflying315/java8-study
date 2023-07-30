package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 816. 截断句子
 * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * @Date 2021/12/6
 */
public class LeetCode1816 {
    public String truncateSentence(String s, int k) {
        if (s == null || k <= 0)
            return null;
        String[] sSplit = s.split(" ");
        if (k > sSplit.length) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(sSplit[i]);
            if (i < k - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode1816 leetCode1816 = new LeetCode1816();
        System.out.println(leetCode1816.truncateSentence("What is the solution to this problem", 7));

        System.out.println(10/6);
    }
}
