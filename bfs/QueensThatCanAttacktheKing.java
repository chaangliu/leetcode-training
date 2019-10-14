package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 * <p>
 * Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.
 * Example 1:
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 * Example 2:
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 * Example 3:
 * Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * Constraints:
 * 1 <= queens.length <= 63
 * queens[0].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * At most one piece is allowed in a cell.
 * 20191013
 */
public class QueensThatCanAttacktheKing {
    /**
     * 题意：给定一个KING的位置和一些QUEENS的位置，求哪些QUEENS可以吃到KING（QUEEN可以走直线和对角线）。
     * 这题我一拿到就觉得用BFS做，怎么让一个方向的QUEEN只有一个呢？我用了8个方向标识，每次找到一个QUEEN就判断她位置是否合法，就像N-Queens那题一样。但是代码写得极长。。我在后面贴上。
     * Solution 1.
     * 在讨论区看到的，很新颖，我没想到。。可以直接从8个方向上去找QUEEN，而不是找到QUEEN再判断是否在8个方向上。
     * 用两个loop，枚举九个方向(0，0就continue)，然后让铁头娃往每个方向一直寻找直到边缘。找到第一个QUEEN就break。
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] seen = new boolean[8][8];
        for (int[] queen : queens) seen[queen[0]][queen[1]] = true;
        int[] dirs = {-1, 0, 1};
        for (int dx : dirs) {
            for (int dy : dirs) {
                if (dx == 0 && dy == 0) continue;
                int x = king[0], y = king[1];
                while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8) {
                    x += dx;
                    y += dy;
                    if (seen[x][y]) {
                        result.add(Arrays.asList(x, y));
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 我的Solution：BFS
     */
    class Solution {
        /**
         * Strategy: BFS 4方向，找到满足的Q之后把所在的8个方向中的位置标记为找到了，后续来自这个方向的Q都过滤掉
         **/
        public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
            Queue<int[]> q = new LinkedList<>();
            boolean[] dir = new boolean[8];//visited directions, starts from top-left
            HashSet<Integer> queensSet = new HashSet<>();
            for (int[] queen : queens) {
                queensSet.add(1000 * queen[0] + queen[1]);
            }
            HashSet<Integer> visited = new HashSet<>();
            List<List<Integer>> res = new ArrayList<>();
            q.offer(king);
            //visited.add(1000 * king[0] + king[1]);
            while (!q.isEmpty()) {
                int[] pair = q.poll();
                int x = pair[0], y = pair[1];
                if (x < 0 || x > 8 || y < 0 || y > 8) continue;
                if (!visited.contains(1000 * x + y)) {
                    visited.add(1000 * x + y);
                    if (queensSet.contains(1000 * x + y) && canAttack(x, y, king, dir)) {
                        List<Integer> item = new ArrayList<>();
                        item.add(x);
                        item.add(y);
                        res.add(item);
                    }
                    q.offer(new int[]{x + 1, y});
                    q.offer(new int[]{x - 1, y});
                    q.offer(new int[]{x, y + 1});
                    q.offer(new int[]{x, y - 1});
                }
            }
            return res;
        }

        private boolean canAttack(int x, int y, int[] king, boolean[] dir) {
            int x1 = king[0], y1 = king[1];
            if (x == x1 && y == y1) return false;
            if (x == x1) {
                if (y > y1 && !dir[1]) {
                    dir[1] = true;
                    return true;
                }
                if (y < y1 && !dir[5]) {
                    dir[5] = true;
                    return true;
                }
                return false;
            } else if (y == y1) {
                if (x > x1 && !dir[3]) {
                    dir[3] = true;
                    return true;
                }
                if (x < x1 && !dir[7]) {
                    dir[7] = true;
                    return true;
                }
                return false;
            } else if (Math.abs(x - x1) == Math.abs(y - y1)) {
                if (x > x1 && y > y1 && !dir[2]) {
                    dir[2] = true;
                    return true;
                }
                if (x > x1 && y < y1 && !dir[6]) {
                    dir[6] = true;
                    return true;
                }
                if (x < x1 && y > y1 && !dir[0]) {
                    dir[0] = true;
                    return true;
                }
                if (x < x1 && y < y1 && !dir[4]) {
                    dir[4] = true;
                    return true;
                }
                return false;
            }
            return false;
        }
    }
}
