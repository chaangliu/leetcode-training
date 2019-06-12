package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * <p>
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * <p>
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 * Example 1:
 * Input: [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * Note:
 * <p>
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 * 20190612
 */
public class ShortestBridge {

    /**
     * 这题我看到TAG是DFS/BFS,以为两者都能做，就先用DFS做了一下，思路是先flood fill把其中一个岛的颜色变成2，然后从这个岛dfs地去找颜色为1的岛。
     * 这么做的问题是，你不知道从哪个个2开始寻找才能找到shortest的bridge，所以只能从每个2出发去dfs。这样这做法在遇到N = 5左右就TLE了。
     * <p>
     * 看了DISCUSS第二名答案的做法，发现这题是DFS + BFS;
     * 也是先flood fill，但是flood fill完了之后bfs有两个操作非常值得学习：
     * 1. 不要对每个格子做bfs，而是应该在flood fill的时候把颜色2的格子全都加入queue当做bfs的第一层。
     * 2. bfs的过程中遇到0的格子要把它标记为visited（或者颜色涂成2），这样可以节省很多次循环（不然会在N = 7左右的时候TLE）！
     *
     * 另，这题也能用UnionFind.
     */
    int res = Integer.MAX_VALUE;
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    Queue<int[]> q = new LinkedList<>();

    public int shortestBridge(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] == 1) {
                    floodFill(A, i, j, 2);
                    return bfs(A);
                }
            }
        }
        return -1;
    }

    private int bfs(int[][] A) {
        //bfs to expand this island
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                for (int[] dir : dir) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i >= 0 && j >= 0 && i < A.length && j < A.length && A[i][j] != 2) {
                        if (A[i][j] == 1) {
                            return step;
                        }
                        q.offer(new int[]{i, j});
                        A[i][j] = 2;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private void floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, newColor, new boolean[image.length][image[0].length], image[sr][sc]);
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, boolean[][] visited, int originalColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || visited[sr][sc] || image[sr][sc] != originalColor) return;
        //铁头娃
        image[sr][sc] = newColor;
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;
        dfs(image, sr - 1, sc, newColor, visited, originalColor);
        dfs(image, sr + 1, sc, newColor, visited, originalColor);
        dfs(image, sr, sc - 1, newColor, visited, originalColor);
        dfs(image, sr, sc + 1, newColor, visited, originalColor);
    }
}
