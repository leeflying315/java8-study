package com.lifei.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lifei
 * @Description: 211. 添加与搜索单词 - 数据结构设计
 * @Date 2021/10/19
 */
public class LeetCode211 {
    Map<Integer, List<String>> map;

    public LeetCode211() {
        map = new HashMap<>();
    }

    public void addWord(String word) {
        if (map.containsKey(word.length())) {
            List<String> list = map.get(word.length());
            list.add(word);
        } else {
            map.put(word.length(), new ArrayList<String>() {{
                add(word);
            }});
        }
    }

    public boolean search(String word) {
        if (!map.containsKey(word.length())) {
            return false;
        }
        List<String> list = map.get(word.length());
        for (String s : list) {
            boolean result = true;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == '.') {
                    continue;
                }
                if (word.charAt(i) != s.charAt(i)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        LeetCode211 leetCode211 = new LeetCode211();
        leetCode211.addWord("add");

        System.out.println(leetCode211.search(".at"));
        leetCode211.addWord("bat");
        System.out.println(leetCode211.search(".at"));

    }
}
