package com.lifei.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author lifei
 * @Description: 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * 把数组取出放到新数组中
 * @Date 2021/6/8
 */
public class LeetCode128 {

    // 999999999 会超过内存限制
    public int longestConsecutiveBack(int[] nums) {
        if (nums.length == 0)
            return 0;
        // 存在负数情况
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
            if (min > num) {
                min = num;
            }
        }
        int[] sorts = new int[max - min + 1];

        for (int num : nums) {
            sorts[num - min] = 1;
        }
        boolean flag = false;
        int maxLength = 1;
        int tempLength = 0;
        for (int sort : sorts) {
            if (sort == 1) {
                tempLength++;
                if (flag) {
                    if (maxLength < tempLength) {
                        maxLength = tempLength;
                    }
                } else {
                    // 重新开始
                    tempLength = 1;

                }
                flag = true;
            } else {
                tempLength = 0;
                flag = false;
            }
        }
        return maxLength;
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int tempLength = 1;
                int value = num + 1;
                while (set.contains(value)) {
                    tempLength++;
                    value++;
                }
                if (tempLength > max) {
                    max = tempLength;
                }

            }

        }
        return max;

    }

    public static void main(String[] args) {
        LeetCode128 leetCode128 = new LeetCode128();
        int[] test = new int[]{0, 1, 2, 4, 8, 5, 6, 7, 9, 3, 55, 88, 77, 99, 999999999};
        System.out.println(leetCode128.longestConsecutive(test));
    }
}
