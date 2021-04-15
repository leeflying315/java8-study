package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * @Date 2021/4/15
 */
public class LeetCode31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int totalLength = nums.length;
        int left = 0;
        int change = 0;
        for (int m = 0; m < totalLength; m++) {
            int min = Integer.MAX_VALUE;
            for (int j = m + 1; j < totalLength; j++) {
                if (nums[m] < nums[j]) {
                    left = m;
                    if (min > nums[j]) {
                        min = nums[j];
                        change = j;
                    }
                }
            }
        }
//        System.out.println("left :" + left);
//        System.out.println("change: " + change);
        // 整体顺序 不存在下一个更大的排列
        if (left == 0 && change == 0) {
            for (int i = 0; i < totalLength / 2; i++) {
                int temp = nums[i];
                nums[i] = nums[totalLength - i - 1];
                nums[totalLength - i - 1] = temp;
            }
        }
        // 在当前I节点后顺序排
        else {
            int temp = nums[change];
            nums[change] = nums[left];
            nums[left] = temp;

            for (int i = left + 1; i < totalLength; i++) {
                temp = nums[i];
                int p = i;
                for (int n = i + 1; n < totalLength; n++) {
                    if (nums[n] < temp) {
                        temp = nums[n];
                        p = n;
                    }
                }
                if (p != i) {
                    temp = nums[i];
                    nums[i] = nums[p];
                    nums[p] = temp;
                }
            }

        }


    }

    // 两遍扫描即可
    // 1. 从右往左找到第一个非降序排列的数字
    // 2. 从右到左找到第一个大于非降序排列数字的数字
    // 3. 交换两个数字
    // 4. 反转数字
    public void nextPermutationAnsewer(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
    public static void main(String[] args) {
        int[] height = new int[]{
                1, 3, 2
        };
        LeetCode31 leetCode31 = new LeetCode31();
        leetCode31.nextPermutation(height);
        for (int n : height) {
            System.out.println(n);
        }
    }
}
