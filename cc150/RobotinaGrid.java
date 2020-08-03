package cc150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobotinaGrid {
    /**
     * 题意：机器人走格子，1代表障碍，请找一条左上到右下的路，如果找不到，返回空list。
     * 事实证明，case要求的是走一条不能折返的路。
     * 解法：跟lc的题目不太一样，lc是求路径种类数，这个是求路径。有两种做法，dfs和dp。
     * dfs的话有两个要点；
     * 1. 向右向下走，不需要向左向上，因为如果向左向上能走通，那一定是多余的，需要折返的。
     * 2. backtrack的时候，不能把visited设置回false。因为如果你在某个地方v过了却又没有能够继续走下去，说明这个格子向右向下都是barrier，下次也不用考虑了。否则超时。
     */
    public List<List<Integer>> pathWithObstacles(int[][] A) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(A, res, new boolean[A.length][A[0].length], 0, 0);
        return res;
    }

    int[][] dirs = new int[][]{{1, 0}, {0, 1}}; // 要点1

    private boolean dfs(int[][] A, List<List<Integer>> res, boolean[][] visited, int i, int j) {
        int m = A.length, n = A[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || A[i][j] == 1) return false;
        res.add(Arrays.asList(i, j));
        visited[i][j] = true;
        if (i == m - 1 && j == n - 1) return true;
        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (dfs(A, res, visited, ni, nj)) {
                // System.out.println("found path, " + res);
                return true;
            }
        }
        res.remove(res.size() - 1);
        // visited[i][j] = false; // 要点2
        return false;
    }
}
