package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.List;

// 679. 24 点游戏
// 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
public class Leetcode679 {
    static final int SUM = 24;
    static final double EPSLION = 1e-6;
    static final int ADD = 0, MULTIFY = 1, SUBTRCT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            list.add((double) num);
        }
        return resolve(list);
    }

    public boolean resolve(List<Double> list) {
        if (list.size() == 0)
            return false;
        // 浮点运算小于10的-6次方就认为是相等的
        if (list.size() == 1)
            return Math.abs(list.get(0) - SUM) < EPSLION;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<Double>();
                    // 把此时不属于当前选中的两个数字的其余数字加入list
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIFY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRCT) {
                            list2.add(list.get(i) - list.get(j));
                        } else {
                            if (Math.abs(list.get(j)) < EPSLION)
                                continue;
                            else
                                list2.add(list.get(i) / list.get(j));
                        }
                        if (resolve(list2))
                            return true;
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
