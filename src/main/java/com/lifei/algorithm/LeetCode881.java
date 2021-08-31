package com.lifei.algorithm;

import java.util.*;

/**
 * @Author lifei
 * @Description: 881. 救生艇
 * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * @Date 2021/8/26
 */
public class LeetCode881 {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int ship = 0;
        List<Integer> sorts = new ArrayList<>();
        while (left != right) {
            // 最小和最大加起来大于limit， 最大需要单独一个船
            if (people[right] + people[left] > limit && !sorts.contains(left)&& !sorts.contains(right) ) {
                ship++;
                right--;
            } else {
                System.out.println("left " + left);
                System.out.println("right " + right);

                int temp = left;
                while (people[right] + people[temp] <= limit && !sorts.contains(temp) && temp < right) {
                    temp++;
                }
                // 移除挪走的元素
                sorts.add(temp - 1);
                right--;
                ship++;
            }
        }
        // 如果始终是一个人一条船，那么最后一个人需要+1
        // 如果有两人上船了，
        if (sorts.isEmpty()) {
            ship++;
        }
        return ship;
    }

    // 如果最大值和最小值相加小于Limit， 最大值和第二小值相加同样小于limit，
    // 那么第二大值和第小值一定大于 limit, 第二大值和第二小值相加同样小于limit
    public int numAnswerRescueBoats(int[] people, int limit) {
        int res = 0;
        int right = people.length - 1;
        int left = 0;
        Arrays.sort(people);
        while (left <= right) {
            if (left == right) {
                res++;      // 只剩下最后一个,直接一个走,结束
                break;
            }
            if (people[left] + people[right] > limit) {
                res++;
                right--;        // 先载最重的, 而且最小的也无法一起载,那么就最重的单独走
            }
            else {
                res++;
                right--;        // 最重的与最轻的一起走
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int a[] = new int[]{1, 2, 4, 5};
        LeetCode881 leetCode881 = new LeetCode881();
        System.out.println(leetCode881.numRescueBoats(a, 6));
    }
}
