package com.lifei.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author lifei
 * @Description: 200. 岛屿数量
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * @Date 2021/9/6
 */
public class LeetCode200 {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int m = 0; m < grid.length; m++) {
            for (int n = 0; n < grid[0].length; n++) {
                // 注意边界数组长度
                if (grid[m][n] == '1' ) {
                    // 发现新岛屿
                    count++;
                    dfs(grid,m,n);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int m, int n) {
        // 越界或者已经走过的路不算
        if (m >= grid.length || m < 0 || n >= grid[0].length || n < 0 ) {
            return;
        }
        // 如果当前已经是海洋，则退出
        if (grid[m][n] != '1') {
            return;
        }
        // 直接标记效率会更高
        grid[m][n] = '0';
        dfs(grid, m + 1, n);
        dfs(grid, m - 1, n);
        dfs(grid, m, n + 1);
        dfs(grid, m, n - 1);
    }
}
