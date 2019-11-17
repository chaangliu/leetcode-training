package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.
 * In one shift operation:
 * Element at grid[i][j] becomes at grid[i][j + 1].
 * Element at grid[i][m - 1] becomes at grid[i + 1][0].
 * Element at grid[n - 1][m - 1] becomes at grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 * Example 1:
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * Example 2:
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * Example 3:
 * <p>
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 * Constraints:
 * 1 <= grid.length <= 50
 * 1 <= grid[i].length <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 * 20191117
 */
public class Shift2DGrid {
    /**
     * 题意：把二维数组中的每个数右移k位。
     * 我很无耻地执行了k%n次~可以单次完成的。
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        k = k % (row * col);

        int[][] r = calc(new int[row][col], grid, row, col, k);
        List<List<Integer>> res = new ArrayList<>();
        for (int[] arr : r) {
            List<Integer> item = new ArrayList<>();
            for (int num : arr) item.add(num);
            res.add(item);
        }
        return res;
    }

    private int[][] calc(int[][] r, int[][] grid, int row, int col, int k) {
        for (int x = 0; x < k; x++) {
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++) {
                    if (j < col - 1) {
                        r[i][j + 1] = grid[i][j];
                    } else if (i != row - 1) {
                        r[i + 1][0] = grid[i][j];
                    } else {
                        r[0][0] = grid[i][j];
                    }
                }
            grid = r;
            r = new int[row][col];
        }
        return grid;
    }
}
