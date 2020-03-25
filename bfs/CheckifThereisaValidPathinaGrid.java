package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
 * 1 which means a street connecting the left cell and the right cell.
 * 2 which means a street connecting the upper cell and the lower cell.
 * 3 which means a street connecting the left cell and the lower cell.
 * 4 which means a street connecting the right cell and the lower cell.
 * 5 which means a street connecting the left cell and the upper cell.
 * 6 which means a street connecting the right cell and the upper cell.
 * You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
 * Notice that you are not allowed to change any street.
 * Return true if there is a valid path in the grid or false otherwise.
 * Example 1:
 * Input: grid = [[2,4,3],[6,5,2]]
 * Output: true
 * Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
 * Example 2:
 * Input: grid = [[1,2,1],[1,2,1]]
 * Output: false
 * Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
 * Example 3:
 * Input: grid = [[1,1,2]]
 * Output: false
 * Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
 * Example 4:
 * Input: grid = [[1,1,1,1,1,1,3]]
 * Output: true
 * Example 5:
 * Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
 * Output: true
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 * 20200325
 */
public class CheckifThereisaValidPathinaGrid {
    /**
     * 题意：给你一些管道，问你能否从左上角走到右下角，有点像小时候玩的铁轨玩具。
     * 解法：BFS，DFS都行，我DFS写了一万行没整出来；这里有个技巧，可以用两组数组表示一种铁轨的两个方向，然后BFS，到下一层的时候，看看能不能再回到之前那个各格子，如果能，说明两个格子联通了。
     * 注意，这里左右走，变化的应该是y，比如[0,0]向右走，应该变成[0,1]而不是[1,0]，因为x对应m，y对应n；这个跟平时想得好像有点区别。
     */
    int[][][] dirs = {
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
    };

    //the idea is you need to check port direction match, you can go to next cell and check whether you can come back.
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] d = q.poll();
            int x = d[0], y = d[1], num = grid[x][y];
            for (int[] dir : dirs[num - 1]) {
                int newX = x + dir[0], newY = y + dir[1];
                if (newX >= m || newX < 0 || newY >= n || newY < 0 || visited[newX][newY]) continue;
                int nextNum = grid[newX][newY];
                for (int[] nextDir : dirs[nextNum - 1]) {
                    if (newX + nextDir[0] == x && newY + nextDir[1] == y) {
                        q.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return visited[m - 1][n - 1];
    }
}
