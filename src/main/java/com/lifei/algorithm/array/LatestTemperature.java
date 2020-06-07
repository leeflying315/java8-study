package com.lifei.algorithm.array;

/* 739. 每日温度
 * https://leetcode-cn.com/problems/daily-temperatures/
 * */

import java.util.Arrays;
import java.util.Stack;

public class LatestTemperature {
    public int[] dailyTemperatures(int[] T) {
        int[] a = new int[T.length];
        // 大的在最底层，由大到小出队列
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.empty() && T[stack.peek()] <= T[i])
                stack.pop();
            a[i] = stack.empty()?0:stack.peek() - i;
            // 加入当前值是因为当前值可能比i-1要大，此时stack中peek是最小的
            stack.push(i);
        }
        return a;
    }
}
