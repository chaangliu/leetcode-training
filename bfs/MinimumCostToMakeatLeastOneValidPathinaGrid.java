package bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * Example 3:
 *
 *
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 * Example 4:
 *
 * Input: grid = [[2,2,2],[2,2,2]]
 * Output: 3
 * Example 5:
 *
 * Input: grid = [[4]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 20200303
 */
public class MinimumCostToMakeatLeastOneValidPathinaGrid {
    /**
     * 题意：每个格子的1，2，3，4代表4个方向，每次换方向就要cost+1，问从左上走到右下的最小cost。
     * 解法：把顺势方向的node看成权值0，改变方向的看成权值1，做BFS，但是需要把下一次符合条件的node再次加入队列头部，所以用Deque.
     */
    public int minCost(int[][] grid) {
        int m, n;
        if (grid == null || (m = grid.length) < 1 || (n = grid[0].length) < 1) {
            return -1;
        }
        int[] dx = {0, 0, 0, 1, -1};
        int[] dy = {0, 1, -1, 0, 0};
        Deque<Node> deq = new ArrayDeque<>();
        deq.addFirst(new Node(0, 0, 0));
        boolean[][] visit = new boolean[m][n];
        while (!deq.isEmpty()) {
            Node pair = deq.pollFirst();
            int x = pair.x, y = pair.y, cost = pair.cost;
            if (x == m - 1 && y == n - 1) return cost;
            visit[x][y] = true;
            for (int i = 1 ; i <= 4; i ++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visit[nx][ny]) continue;
                if (grid[x][y] == i) { //  grid[x][y] == i: 老格子和新格子方向一致，权值为0
                    deq.addFirst(new Node(nx,ny,cost));
                } else {
                    deq.addLast(new Node(nx,ny,cost+1));
                }
            }
        }
        return -1;
    }
    class Node {
        int x, y, cost;
        Node (int x,int y, int cost) {
            this.x = x; this.y = y ; this.cost = cost;
        }
    }
}
