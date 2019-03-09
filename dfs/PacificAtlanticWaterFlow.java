package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * Example:
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   *   * Atlantic
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {

    /**
     * approach1.
     * 我的做法：backtracking dfs
     * 但是我没有复用太平洋和大西洋两种情况的递归，导致代码有点冗余
     */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (toPacific(i, j, matrix, new boolean[matrix.length][matrix[0].length]) && toAtlantic(i, j, matrix, new boolean[matrix.length][matrix[0].length]))
                    res.add(new int[]{i, j});
            }
        return res;
    }

    private boolean toPacific(int i, int j, int[][] matrix, boolean[][] visited) {
        if (j <= 0 || i <= 0) return true;//这两句的顺序不能变
        //if (j > matrix[0].length - 1 || i > matrix.length - 1) return false;//已犯错误，这里判断的应该是超出边界
        if (!visited[i][j]) {
            visited[i][j] = true;
            if (matrix[i - 1][j] <= matrix[i][j] && toPacific(i - 1, j, matrix, visited)) return true;
            if (matrix[i][j - 1] <= matrix[i][j] && toPacific(i, j - 1, matrix, visited)) return true;
            if (i + 1 <= matrix.length - 1 && matrix[i + 1][j] <= matrix[i][j] && toPacific(i + 1, j, matrix, visited)) return true;
            if (j + 1 <= matrix[0].length - 1 && matrix[i][j + 1] <= matrix[i][j] && toPacific(i, j + 1, matrix, visited)) return true;
            visited[i][j] = false;
        }
        return false;
    }

    private boolean toAtlantic(int i, int j, int[][] matrix, boolean[][] visited) {
        if (j >= matrix[0].length - 1 || i >= matrix.length - 1) return true;
        if (!visited[i][j]) {
            visited[i][j] = true;
            if (i - 1 >= 0 && matrix[i - 1][j] <= matrix[i][j] && toAtlantic(i - 1, j, matrix, visited)) return true;
            if (j - 1 >= 0 && matrix[i][j - 1] <= matrix[i][j] && toAtlantic(i, j - 1, matrix, visited)) return true;
            if (matrix[i + 1][j] <= matrix[i][j] && toAtlantic(i + 1, j, matrix, visited)) return true;
            if (matrix[i][j + 1] <= matrix[i][j] && toAtlantic(i, j + 1, matrix, visited)) return true;
            visited[i][j] = false;
        }
        return false;
    }

    /**
     * 网上的floodfill dfs：
     * 这个思路跟我那个不太一样，我那个是从每个格子开始backtracking；这个是floodfill（或者说，从下游走向上游），从入海口开始往高处走，遇到开始下降就返回；不恢复状态，效率比我的高
     */
    public List<int[]> pacificAtlantic____DFS(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
        }
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, i);
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i, j});
        return res;
    }

    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
        int n = matrix.length, m = matrix[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for (int[] d : dir) {
            dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
        }
    }


    public static void main(String args[]) {
        int[][] nums = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}};
//        node.next.next = new ListNode(3);
//        ListNode res = new Test().reverseList(node);
        List res = new PacificAtlanticWaterFlow().pacificAtlantic____DFS(nums);
        System.out.print("");
    }
}
