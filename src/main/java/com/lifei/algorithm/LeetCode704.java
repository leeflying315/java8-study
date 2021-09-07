package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 二分查找
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
 * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * @Date 2021/9/6
 */
public class LeetCode704 {
    public int search(int[] nums, int target) {

        return search(nums, target, (nums.length) / 2, 0, nums.length - 1);
    }

    public int search(int[] nums, int target, int pos, int left, int right) {
        if (nums.length == 2) {
            if (nums[0] == target) {
                return 0;
            }
            if (nums[1] == target) {
                return 1;
            }
            return -1;
        }
        if (pos > right || pos < left) {
            return -1;
        }
        // 只输入两个数的情况下会出错
        if (pos == left || pos == right) {
            return nums[pos] == target ? pos : -1;
        }
        if (nums[pos] == target) {
            return pos;
        } else if (nums[pos] < target) {
            return search(nums, target, (pos + right + 1) / 2, pos, right);
        } else {
            return search(nums, target, (pos + left) / 2, left, pos);

        }
    }

    public static void main(String[] args) {
        System.out.println(1 / 2);
    }
}
