package com.lifei.algorithm;

import java.util.*;

/**
 * @Author lifei
 * @Description: 给你一个包含 n 个整数的数组nums，
 * 判断nums中是否存在三个元素 a，b，c ，使得
 * a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * @Date 2021/4/7
 */
public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[i];

            for (int second = i + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > i + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }

                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> res = new ArrayList<Integer>();
                    res.add(nums[i]);
                    res.add(nums[second]);
                    res.add(nums[third]);
                    list.add(res);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        LeetCode15 leetCode15 = new LeetCode15();
        System.out.println(leetCode15.threeSum(nums));
    }
}
