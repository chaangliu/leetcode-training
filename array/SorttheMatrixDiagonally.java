package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 * Example 1:
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * 20200126
 */
public class SorttheMatrixDiagonally {
    /**
     * 题意：给你一个matrix，让你把它按对角线排序。
     * 这题就straight-forward地做，复杂度不会超。
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = m - 1; i >= 0; i--) {
            int x = i, y = 0;
            List<Integer> item = new ArrayList<>();
            while (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1) {
                item.add(mat[x][y]);
                x++;
                y++;
            }
            Collections.sort(item);
            x = i;
            y = 0;
            int cnt = 0;
            while (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1) {
                mat[x][y] = item.get(cnt++);
                x++;
                y++;
            }
        }


        for (int i = 1; i < n; i++) {
            int x = 0, y = i;
            List<Integer> item = new ArrayList<>();
            while (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1) {
                item.add(mat[x][y]);
                x++;
                y++;
            }
            Collections.sort(item);
            int cnt = 0;
            x = 0;
            y = i;
            while (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1) {
                mat[x][y] = item.get(cnt++);
                x++;
                y++;
            }
        }
        return mat;
    }

    /**
     * lee的解法，正常顺序遍历就行，省去了边界、省去了排序。
     * A[i][j] on the same diagonal have same value of i - j
     * For each diagonal,
     * put its elements together, sort, and set them back.
     */
    public int[][] diagonalSort_(int[][] A) {
        int m = A.length, n = A[0].length;
        HashMap<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.putIfAbsent(i - j, new PriorityQueue<>());
                d.get(i - j).add(A[i][j]);
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                A[i][j] = d.get(i - j).poll();
        return A;
    }
}
