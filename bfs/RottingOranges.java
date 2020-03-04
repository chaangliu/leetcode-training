package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * <p>
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * <p>
 * 20190217weekly contest
 */
public class RottingOranges {
    /**
     * 题意：和腐烂橘子相邻的句子会腐烂。问最早都腐烂的是什么时候。
     * 此题在群内WanYing等朋友的指导下才AC（感觉这题标easy是因为queue bfs这种题大家都已经做烂了，默认是基本功了）
     * 我一开始想着每次for循环遇到的2都加入到queue，然后再维持一个全局最小的时间；这样的问题是，
     * 没法做到2-end/multi-end bfs，比如左下角和右下角同时开始rot，这样速度是大大缩短的；而且就算用我那种方案实施起来，还要把grid的状态还原回去
     * 对于2-end bfs，我一开始理解的bfs是queue里一开始只能有一个元素；但是仔细想一下，每次while代表一次bfs的扩张，那么一开始queue中有k个元素的话，就相当于从k个起点一开开始bfs了；类似的思路OpenTheLock那题有2-end bfs
     */
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        int row = grid.length;
        int col = grid[0].length;
        int res = -1;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        while (!queue.isEmpty()) {
            res++;
            for (int i = queue.size(); i > 0; i--) {
                int[] cord = queue.poll();
                int x = cord[0], y = cord[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j], ny = y + dy[j];
                    if (nx < 0 || nx >= row || ny < 0 || ny >= col || grid[nx][ny] != 1) continue;
                    grid[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        for (int[] r : grid)
            for (int v : r)
                if (v == 1)
                    return -1;
        return res == -1 ? 0 : res;
    }
}
