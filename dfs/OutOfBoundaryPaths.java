package dfs;

/**
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 * Example 1:
 * <p>
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * <p>
 * Example 2:
 * <p>
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 */
public class OutOfBoundaryPaths {

    //WRONG ANSWER
//    int res = 0;
//
//    public int findPaths___INTUITIVE(int m, int n, int N, int i, int j) {
//        dfs(new boolean[m][n], m, n, N, i, j);
//        return res;
//    }
//
//    private void dfs(boolean visited[][], int m, int n, int N, int i, int j) {
//        if (N < 0) return;
//        if (i < 0 || i >= m || j < 0 || j >= n) {
//            res++;
//            return;
//        }
//        if (visited[i][j]) return;
//        visited[i][j] = true;
//        dfs(visited, m, n, N - 1, i + 1, j);
//        dfs(visited, m, n, N - 1, i - 1, j);
//        dfs(visited, m, n, N - 1, i, j + 1);
//        dfs(visited, m, n, N - 1, i, j - 1);
//        visited[i][j] = false;
//    }

    /**
     * approach1. brute force（思路对但是TLE）
     * 这题的brute force我看它没有用visited[][]很奇怪，我以往认为这种四个方向的dfs必须要搭配visited不然会产生死循环；事实上并不是这样，不加visited只会产生很多的重复路径而已，进而很可能产生stackOverFlow；
     * 而这题用二维坐标表示Visited不行，因为允许走重复的线路。怎么办？我之前想到用set存储完整路径的string，但发现实施困难（而且可能会遇到不同string的hash相同的情况？）
     */

    public int findPath__TTLE(int m, int n, int N, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        if (N == 0)
            return 0;
        return findPath__TTLE(m, n, N - 1, i - 1, j)
                + findPath__TTLE(m, n, N - 1, i + 1, j)
                + findPath__TTLE(m, n, N - 1, i, j - 1)
                + findPath__TTLE(m, n, N - 1, i, j + 1);
    }
}
