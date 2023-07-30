package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author lifei
 * @Description: 253. 会议室 II
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * @Date 2021/12/16
 */
public class LeetCode253 {

    public int minMeetingRooms(int[][] intervals) {
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            for (int n = i + 1; n < intervals.length; n++) {
                if (intervals[i][0] > intervals[n][0]) {
                    int temp = intervals[i][0];
                    intervals[i][0] = intervals[n][0];
                    intervals[n][0] = temp;
                    temp = intervals[i][1];
                    intervals[i][1] = intervals[n][1];
                    intervals[n][1] = temp;
                } else if (intervals[i][0] == intervals[n][0] && intervals[i][1] > intervals[n][1]) {
                    int temp = intervals[n][1];
                    intervals[n][1] = intervals[i][1];
                    intervals[i][1] = temp;
                }
            }
        }
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[i][0] + "\t" + intervals[i][1]);
        }

        int minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            // 最短的终止时间未超过本次的开始时间
            if (minEnd > intervals[i][0]) {
                minEnd = Math.min(minEnd, intervals[i][1]);
                // 更新要记住更新新加的字段
                stack.add(intervals[i][1]);
            } else {
                Integer maxEnd = Integer.MIN_VALUE;
                int kPosition = 0;
                // 选最小满足要求的start节点 因为已经排序了 好像随便选一个也可以
                for (int k = 0; k < stack.size(); k++) {
                    // 比较要记得要拿当前值和条件比较
                    if (maxEnd < stack.get(k) && stack.get(k) <= intervals[i][0]) {
                        maxEnd = stack.get(k);
                        kPosition = k;
                    }
                }
                stack.remove(kPosition);

                stack.add(intervals[i][1]);
                // 需要记录每个房间目前使用的最短时间
                // 需要重新获取当前最短结束周期
                minEnd = Integer.MAX_VALUE;
                for (int k = 0; k < stack.size(); k++) {
                    if (minEnd > stack.get(k)) {
                        minEnd = stack.get(k);
                    }
                }
                System.out.println(minEnd + "\t" + kPosition + "\t" + intervals[i][1]);
            }
            System.out.println(stack);
        }
        return stack.size();
    }

    public static void main(String[] args) {
        LeetCode253 leetCode253 = new LeetCode253();
        int[][] input = new int[][]{
                {1, 8}, {6, 20}, {9, 16}, {13, 17}
        };
        System.out.println(leetCode253.minMeetingRooms(input));
    }
}
