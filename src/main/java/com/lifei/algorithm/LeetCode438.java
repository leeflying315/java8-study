package com.lifei.algorithm;

import java.util.*;

/**
 * @Author lifei
 * @Description:
 * @Date 2021/9/30
 */
public class LeetCode438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        char[] tempP = p.toCharArray();
        Arrays.sort(tempP);
        String indexP = String.valueOf(tempP);
        for(int i =0;i <= s.length()-p.length();i++){
            char[] temp = s.substring(i,i+p.length()).toCharArray();
            Arrays.sort(temp);
            if(indexP.equals(String.valueOf(temp))){
                result.add(i);
            }
        }
        return result;
    }
}
