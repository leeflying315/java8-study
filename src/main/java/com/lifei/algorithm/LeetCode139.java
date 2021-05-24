package com.lifei.algorithm;

import java.util.*;

/**
 * @Author lifei
 * @Description: 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * @Date 2021/4/27
 */
public class LeetCode139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // S可以拆分，dp[i] = dp[j] && check(dp[j....i-1];
        // 当满足拆开的两边同时都在字典中出现，则可以被拆分
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        // 边界默认能拆分。
        dp[0] = true;
        // 遍历判断每一个字母
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // dp[j] 代表从s.subString(0-j)能否被拆分
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean getMatch(String s, Queue<String> queue, Integer index, List<String> wordDict) {
        if (s == null || s.length() == 0)
            return true;
        for (int i = index; i < wordDict.size(); i++) {
            if (s.contains(wordDict.get(index))) {
                queue.add(wordDict.get(index));
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
