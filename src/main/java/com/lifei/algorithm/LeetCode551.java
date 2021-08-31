package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 学生出勤记录 I
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 * @Date 2021/8/17
 */
public class LeetCode551 {
    public boolean checkRecord(String s) {
        int count = 0;
        boolean continueFlag = true;
        int lCount = 0;
        for (char m : s.toCharArray()) {
            if (m == 'L') {
                if (continueFlag) {
                    lCount++;
                    if (lCount >= 3)
                        return false;
                } else {
                    lCount = 1;
                    continueFlag = true;
                }
            } else {
                continueFlag = false;
                lCount = 0;
            }

            if (m == 'A') {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // 答案 思路很简洁
    public boolean checkRecord2(String s) {
        return s.indexOf("A") == s.lastIndexOf("A") && !s.contains("LLL");
    }
    public static void main(String[] args) {
        System.out.printf("");
    }
}
