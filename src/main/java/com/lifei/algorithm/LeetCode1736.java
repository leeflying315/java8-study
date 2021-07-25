package com.lifei.algorithm;

/**
 * @Author lifei
 * @Date 2021/7/24
 * @Description: 1736. 替换隐藏数字得到的最晚时间
 */
public class LeetCode1736 {
    public String maximumTime(String time) {

//        StringBuilder sb = new StringBuilder();
        char[] s = new char[4];
        if (time.charAt(0) == '?') {
            if (time.charAt(1) == '?' || time.charAt(1) == '0'
                    || time.charAt(1) == '1' || time.charAt(1) == '2' || time.charAt(1) == '3') {
                s[0] = '2';
            } else {
                s[0] = '1';
            }
        } else {
            s[0] = time.charAt(0);
        }
        if (time.charAt(1) == '?') {
            if (s[0] == '2') {
                s[1] = '3';
            } else {
                s[1] = '9';
            }
        } else {
            s[1] = time.charAt(1);
        }

        if (time.charAt(3) == '?') {
            s[2] = '5';
        } else {
            s[2] = time.charAt(3);
        }
        s[3] = time.charAt(4) == '?' ? '9' : time.charAt(4);
        StringBuilder sb = new StringBuilder();
        sb.append(s[0]).append(s[1]).append(":").append(s[2]).append(s[3]);
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode1736 leetCode1736 = new LeetCode1736();
        System.out.println(leetCode1736.maximumTime("2?:?5"));
    }
}
