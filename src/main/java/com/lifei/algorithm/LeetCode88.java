package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author lifei
 * @Description: 88. 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * @Date 2021/10/12
 */
public class LeetCode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int count1 = 0;
        int count2 = 0;
        while (count1 != m && count2 != n) {
            if (nums1[count1] > nums2[count2]) {
                int temp = nums2[count2];
                nums2[count2] = nums1[count1];
                nums1[count1] = temp;
                int tempPos = count2;
                while (tempPos + 1 < n) {
                    if (nums2[tempPos] > nums2[tempPos + 1]) {
                        temp = nums2[tempPos + 1];
                        nums2[tempPos + 1] = nums2[tempPos];
                        nums2[tempPos] = temp;
                    }
                    tempPos++;
                }
            }
            count1++;
        }

        while (count2 != n) {
            nums1[count1 + 1] = nums2[count2];
            count2++;
            count1++;
        }

    }
    //[1,2,3,0,0,0]
    //3
    //[2,5,6]
    //3

    public static void main(String[] args) {
        Set<String> set = new HashSet<>(new ArrayList<>());
    }
}
