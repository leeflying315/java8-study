package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * @Date 2021/4/13
 */
public class LeetCode11 {
    int max = 0;

    public int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        getMax(height, 0, height.length - 1);
        return max;
    }

    public void getMax(int[] height, int left, int right) {
        max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
        if (height[left] > height[right]) {
            int currentVal = height[right];
            while (height[left] > height[right] && left < right && height[right] <= currentVal) {
                right--;
            }
        } else {
            int currentVal = height[left];
            while (height[left] <= height[right] && left < right && height[left] <= currentVal) {
                left++;
            }
        }
        if (left >= right) {
            return;
        }
        getMax(height, left, right);
    }


    public static void main(String[] args) {
        int[] height = new int[]{
                1,8,6,2,5,4,8,3,7
        };
        LeetCode11 leetCode11 = new LeetCode11();
        System.out.println(leetCode11.maxArea(height));
    }
}
