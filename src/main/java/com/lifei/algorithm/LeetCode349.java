package com.lifei.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: lifei
 * @Description: 两个数组的交集
 * @Date: 2020/11/2
 */
public class LeetCode349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length==0 || nums2.length ==0)
            return null;
        Set<Integer> set = new HashSet<>(nums1.length);
        Set<Integer> result = new HashSet<>();
        for(int n:nums1){
            set.add(n);
        }
        for(int n:nums2){
            if(set.contains(n)){
                result.add(n);
            }
        }
        Integer[] response = result.toArray(new Integer[result.size()]);
        int[] arr2 = Arrays.stream(response).mapToInt(Integer::valueOf).toArray();

        return arr2;
    }
}
