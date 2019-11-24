package array;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
 * Return the number of servers that communicate with any other server.
 * Example 1:
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 * Example 2:
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 * Example 3:
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 * 20191124
 */
public class CountServersthatCommunicate {
    /**
     * 题意：给你一个二维grid，1代表server。如果一条横线上有不止一台server，这台server就算连通；问一共有多少连通的server。
     * 解法：我用了两个hashMap预处理，时间从暴力的O(n^3)缩短到O(n^2)。
     */
    public int countServers(int[][] grid) {
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                row.put(i, row.getOrDefault(i, 0) + 1);
                col.put(j, col.getOrDefault(j, 0) + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                if (row.get(i) > 1 || col.get(j) > 1) res++;
            }
        }
        return res;
    }
}
