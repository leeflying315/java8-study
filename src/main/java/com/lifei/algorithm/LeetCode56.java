package com.lifei.algorithm;

import java.util.Stack;

/**
 * @Author lifei
 * @Description: 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * @Date 2021/4/20
 */
public class LeetCode56 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2)
            return intervals;
        for (int i = 0; i < intervals.length; i++) {
            for (int m = i + 1; m < intervals.length; m++) {
                if (intervals[i][0] > intervals[m][0]) {
                    int temp = intervals[i][0];
                    intervals[i][0] = intervals[m][0];
                    intervals[m][0] = temp;
                    // 交换右边
                    int temp2 = intervals[i][1];
                    intervals[i][1] = intervals[m][1];
                    intervals[m][1] = temp2;
                }
            }
        }
        for(int i =0;i<intervals.length;i++){
            System.out.println(intervals[i][0] + "\t" + intervals[i][1]);
        }

        Stack<int[]> stack = new Stack<>();
        int right = intervals[0][1];
        stack.add(intervals[0]);
        for (int[] interval : intervals) {
            // 1. 有重叠间隔 2. 忽略了第二个区间比第一个区间最右侧要小的问题
            if (right >= interval[0]) {
                int[] pos = stack.pop();
                right = Math.max(interval[1],right);
                pos[1] = right;
                stack.push(pos);
            } else {
                System.out.println(interval[0] + "\t" + interval[1]);
                right = interval[1];
                stack.push(interval);
            }
        }
        int[][] res = new int[stack.size()][2];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i] = stack.pop();
            i++;
        }
        for(int m =0;m<res.length;m++){
            System.out.println(res[m][0] + "\t" + res[m][1]);
        }
        return res;

    }

    public static void main(String[] args) {
        LeetCode56 leetCode56 = new LeetCode56();
        int[][] input = new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        leetCode56.merge(input);
    }
}
