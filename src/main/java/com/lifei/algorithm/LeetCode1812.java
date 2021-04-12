package com.lifei.algorithm;


public class LeetCode1812 {
    public boolean squareIsWhite(String coordinates) {
        int index = coordinates.charAt(0) - 65;
        int two = Integer.parseInt(Character.toString(coordinates.charAt(1)));

        return (two + index) % 2 == 0;

    }

    public static void main(String[] args) {
        LeetCode1812 leetCode1812 = new LeetCode1812();

        System.out.println(leetCode1812.squareIsWhite("c7"));
    }
}
