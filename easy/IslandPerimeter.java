package easy;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class IslandPerimeter {
    /**
     * 题意： 给你一个0，1组成的二维数组，1是陆地0是水面，求岸的长度。
     * 解法：直接遍历或者dfs。
     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    res += calc(row, col, grid);
                }
            }
        return res;
    }

    private int calc(int row, int col, int[][] matrix) {
        int count = 0;
        if (row == 0 || row > 0 && matrix[row - 1][col] == 0) {
            count++;
        }
        if (col == 0 || col > 0 && matrix[row][col - 1] == 0) {
            count++;
        }
        if (row == matrix.length - 1 || row < matrix.length - 1 && matrix[row + 1][col] == 0) {
            count++;
        }
        if (col == matrix[0].length - 1 || col < matrix[0].length - 1 && matrix[row][col + 1] == 0) {
            count++;
        }
        return count;
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter_(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }

    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

    public int islandPerimeter__(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    dfs(A, i, j, new boolean[A.length][A[0].length]);
                }
            }
        }
        return res;
    }

    int res = 0;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private void dfs(int[][] A, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || visited[i][j]) return;
        if (A[i][j] == 1) {
            addEdge(A, i, j);
            visited[i][j] = true;
            for (int[] d : dirs) {
                int ni = i + d[0], nj = j + d[1];
                dfs(A, i, j, visited);
            }
        }
    }

    private void addEdge(int[][] A, int i, int j) {
        if (i == 0 || A[i - 1][j] == 0) res++;
        if (i == A.length - 1 || A[i + 1][j] == 0) res++;
        if (j == 0 || A[i][j - 1] == 0) res++;
        if (j == A[0].length - 1 || A[i][j + 1] == 0) res++;
    }
}
