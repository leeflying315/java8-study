package com.lifei.algorithm;

/**
 * @Author lifei
 * @Description: 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * @Date 2021/9/7
 */
public class LeetCode221 {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    // DP算法
    // 以右下角f(i,j)为正方形成立，那么以右下角f(i,j-1)、f(i-1,j-1)、f(i-1,j)为正方形的边长至少为f(i,j)为正方形的边长-1.
    // 例如边长为4的正方形，内部至少存在三个边长为3的正方形。
    // 即 min(f[i][j−1],f[i−1][j],f[i−1][j−1])≥f[i][j]−1
    // 反之，如果这三个正方形边长至少为3，如果位置(i),(j)==1。 那么f(i)(j)至少为4。最短木桶原则。
    // min(f[i][j−1],f[i−1][j],f[i−1][j−1])≥f[i][j]−1
    // 与上一个不等式联动 可以推导
    // f[i][j]=min(f[i][j−1],f[i−1][j],f[i−1][j−1])+1
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
