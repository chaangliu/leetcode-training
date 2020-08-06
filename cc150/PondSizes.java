package cc150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 * 示例：
 * 输入：
 * [
 * [0,2,1,0],
 * [0,1,0,1],
 * [1,1,0,1],
 * [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 */
public class PondSizes {
    /**
     * 题意：求所有水塘的面积，升序排列。
     * 解法：DFS或者BFS；
     * DFS我自己写了个flood fill。
     * BFS来自讨论区。
     */
    class Solution {
        public int[] pondSizes(int[][] land) {
            List<Integer> res = new ArrayList<>();
            int m = land.length, n = land[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (land[i][j] == 0) res.add(floodFill(land, i, j, visited));
                }
            }
            Collections.sort(res);
            int[] result = new int[res.size()];
            for (int i = 0; i < result.length; i++) result[i] = res.get(i);
            return result;
        }

        private int floodFill(int[][] A, int i, int j, boolean[][] visited) {
            int m = A.length, n = A[0].length, res = 0;
            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || A[i][j] != 0) return 0;
            visited[i][j] = true;
            A[i][j] = 1;
            res = 1;
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0) continue;
                    int ni = i + x, nj = j + y;
                    res += floodFill(A, ni, nj, visited); // 注意这里时res+=，而不是 1 + floodFill()
                }
            }
            // System.out.println("start from, res: " + i + ", " + j + " : " + res);
            return res;
        }
    }

    class Solution_BFS {
        int[][] land;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int n;
        int m;

        public int[] pondSizes(int[][] land) {
            this.land = land;
            this.n = land.length;
            this.m = land[0].length;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (land[i][j] == 0) {
                        bfs(i, j, list);
                    }
                }
            }
            return list.stream().sorted().mapToInt(Integer::valueOf).toArray();
        }

        private void bfs(int i, int j, List<Integer> list) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{i, j});
            land[i][j] = 1;
            int count = 1;
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                for (int k = 0; k < 8; k++) {
                    int ni = poll[0] + dx[k];
                    int nj = poll[1] + dy[k];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && land[ni][nj] == 0) {
                        land[ni][nj] = 1;
                        count++;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
            list.add(count);
        }
    }
}
