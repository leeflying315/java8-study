package com.lifei.study;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lifei
 * @Description:
 * @Date: 2020/9/27
 */
public class mapStudy {
    public static ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.get("a");
    }
}
