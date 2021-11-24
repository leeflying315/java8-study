package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lifei
 * @Description:
 * @Date 2021/10/22
 */
public class LeetCode229 {
    public List<Integer> majorityElement(int[] nums) {

        List<Integer> list = new ArrayList<>();
        if (nums.length == 1) {
            list.add(nums[0]);
            return list;
        }
        int pos = nums.length / 3;
        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int i : nums) {
            if (hs.containsKey(i)) {
                Integer count = hs.get(i);
                count++;
                hs.put(i, count);
                if (count > pos && !list.contains(i)) {
                    list.add(i);
                }
            } else {
                hs.put(i, 1);
                if (1 > pos && !list.contains(i)) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(2/3);
    }
}
