package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 28. 实现 strStr()
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * @Date 2021/4/20
 */
public class LeetCode28 {
    public int strStr(String haystack, String needle) {
        if (needle == null) {
            return 0;
        }
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int m = i + 1;
                int n = 1;
                for (m = i + 1, n = 1; m < haystack.length() && n < needle.length() && haystack.charAt(m) == needle.charAt(n); m++, n++) {
                }
                // 此处注意变量控制
                if (n == needle.length())
                    return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode28 leetCode28 = new LeetCode28();


        System.out.println(leetCode28.strStr("hello","ll"));
    }
}
