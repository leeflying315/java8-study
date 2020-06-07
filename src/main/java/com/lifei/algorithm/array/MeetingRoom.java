package com.lifei.algorithm.array;

import com.lifei.support.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 253. 会议室 II
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，
 * 同时要考虑充分利用会议室资源，
 * 请你计算至少需要多少间会议室，才能满足这些会议安排。
 * 提示： 堆、贪心算法、排序
 * */
public class MeetingRoom {
    public int minMeetingRooms(int[][] intervals) {
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }
        // Min heap
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });
        // Sort the intervals by start time
        // Add the first meeting
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }
}
