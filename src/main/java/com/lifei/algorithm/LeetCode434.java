package com.lifei.algorithm;

/**
 * @Author lifei
 * @Date 2021/10/7
 * @Description:
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 */
public class LeetCode434 {
    public int countSegments(String s) {
        if(s == null || s.length() == 0)
            return 0;

        return s.split(" ").length;
    }


    public int countSegmentsAnswer(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == ' ' && i++ >= 0) continue;
            while (i < n && s.charAt(i) != ' ') i++;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode434 leetCode434 = new LeetCode434();
        System.out.println(leetCode434.countSegments(", , ,        a, eaefa"));
    }
}
