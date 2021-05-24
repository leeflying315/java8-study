package com.lifei.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author lifei
 * @Description: 79. 单词搜索
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。
 * 如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * @Date 2021/4/22
 */
public class LeetCode79 {
    public boolean exist(char[][] board, String word) {
        for (int m = 0; m < board.length; m++) {
            for (int n = 0; n < board[m].length; n++) {
                // 入口匹配，进行匹配
                if (board[m][n] == word.charAt(0)) {
                    Integer stepValue = m * 10 + n;
                    Set<Integer> set = new HashSet<Integer>() {{
                        add(stepValue);
                    }};
                    if (getMatch(board, word, 1, m, n, set))
                        return true;
                }
            }

        }
        return false;
    }

    // index 下一位
    public boolean getMatch(char[][] board, String word, Integer index, Integer m, Integer n, Set<Integer> set) {
        if (index == word.length()) {
            return true;
        }
        System.out.println("start " + m + "\t" + n + "\t" + board[m][n] + "\t" + word.charAt(index) + "\t" + set);

        if (m > 0 && board[m - 1][n] == word.charAt(index) && !set.contains((m - 1) * 10 + n)) {
            set.add((m - 1) * 10 + n);
            System.out.println("down " + m + "\t" + n + "\t" + board[m - 1][n] + "\t" + set);
            if (getMatch(board, word, index + 1, m - 1, n, set)) {
                return true;
            }
        }

        if (m < board.length - 1 && board[m + 1][n] == word.charAt(index) && !set.contains((m + 1) * 10 + n)) {
            set.add((m + 1) * 10 + n);
            System.out.println("up " + m + "\t" + n + "\t" + board[m + 1][n] + "\t" + set);

            if (getMatch(board, word, index + 1, m + 1, n, set)) {
                return true;
            }
        }

        if (n > 0 && board[m][n - 1] == word.charAt(index) && !set.contains((m) * 10 + n - 1)) {
            set.add((m) * 10 + n - 1);
            System.out.println("left " + m + "\t" + n + "\t" + board[m][n - 1] + "\t" + set);

            if (getMatch(board, word, index + 1, m, n - 1, set)) {
                return true;
            }
        }
        if (n < board[m].length - 1 && board[m][n + 1] == word.charAt(index) && !set.contains((m) * 10 + n + 1)) {
            set.add((m ) * 10 + n + 1);
            System.out.println("right " + m + "\t" + n + "\t" + board[m][n + 1]+ "\t" + set);

            if (getMatch(board, word, index + 1, m, n + 1, set)) {
                return true;
            }
        }
        // 当前没有任何前进方向，当前回退
        set.remove(m * 10 + n);
        return false;
    }

    public static void main(String[] args) {
        LeetCode79 leetCode79 = new LeetCode79();
        char[][] input = new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(leetCode79.exist(input, word));
    }
}
