package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description:
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * @Date 2021/4/16
 */
public class LeetCode34 {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0)
            return result;
        int length = nums.length;
        if (length == 1) {
            if (nums[0] == target)
                return new int[]{0, 0};
            else
                return result;
        }
        if (length == 2) {
            if (nums[0] == target) {
                if (nums[1] == target) {
                    return new int[]{0, 1};
                } else {
                    return new int[]{0, 0};
                }
            } else {
                if (nums[1] == target) {
                    return new int[]{1, 1};
                } else {
                    return result;
                }
            }
        }
        int n = length / 2;
        int leftPos = 0;
        int rightPos = length;
        while (n > leftPos && n < rightPos) {
            if (nums[n] < target) {
                System.out.println("1\t" + n);

                leftPos = n;
                int temp = n;
                n = (leftPos + rightPos) / 2;
                if (n == temp) {
                    return result;
                }
                System.out.println("1\t" + n);
            } else if (nums[n] > target) {
                System.out.println("2\t" + n);

                rightPos = n;
                int temp = n;
                n = (leftPos + rightPos) / 2;
                if (n == temp) {
                    return result;
                }
                System.out.println("2\t" + n);
            } else {
                int right = n;
                while (right < length && nums[right] == target) {
                    right++;
                }
                int left = n;
                while (left >= 0 && nums[left] == target) {
                    left--;
                }
                return new int[]{left == n ? n : left + 1, right == n ? n : right - 1};
            }

        }
        if (nums[0] == target)
            return new int[]{0, 0};
        return result;
    }

    public static void main(String[] args) {
        LeetCode34 leetCode34 = new LeetCode34();
        int[] res = leetCode34.searchRange(new int[]{0, 1, 2, 3, 4, 4, 4}, 2);
        for (int n : res) {
            System.out.println(n);
        }
    }
}
