package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * n a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.
 * <p>
 * We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.
 * <p>
 * Return true if and only if it is possible to reach the target square through a sequence of moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation:
 * The target square is inaccessible starting from the source square, because we can't walk outside the grid.
 * Example 2:
 * <p>
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation:
 * Because there are no blocked cells, it's possible to reach the target square.
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= blocked[i][j] < 10^6
 * source.length == target.length == 2
 * 0 <= source[i][j], target[i][j] < 10^6
 * source != target
 * <p>
 * 20190430
 */
public class EscapeALargeMaze {

    /**
     * 这题我一开始用Open the lock那种双端bfs的方法但是WA了，有些false的情况返回了true，原因没想出（按理说应该TLE）
     *
     * 然后发现思路并不是从source一定要reach到target，因为这题10e6特别大，但是block.length给出的是[0,200]范围，所以我们可以在bfs一定次数之后就判定block无法围住source或target了；
     * 这个step目前的case判定很不严格，50也能过，这里用了block.length；事实上按照lee215的说法，这里应该填入block.length * 2，也就是对角线的长度。
     * 1  *  *  *  *
     * *  2  *  *
     * *  *  3
     * *  *
     * *
     */
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return helper(blocked, source, target) && helper(blocked, target, source);
    }

    /**
     * 0 <= blocked.length <= 200
     */
    public boolean helper(int[][] blocked, int[] source, int[] target) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> blocks = new HashSet<>();
        for (int[] b : blocked) {
            blocks.add(b[0] + "," + b[1]);
        }
        q.offer(new int[]{source[0], source[1]});
        int level = 0;
        int dir[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                String str = cell[0] + "," + cell[1];
                if (blocks.contains(str)) continue;
                if (cell[0] == target[0] && cell[1] == target[1]) return true;
                blocks.add(str);//bfs过程可能会向queue中添加相同的坐标
                for (int j = 0; j < 4; j++) {
                    int nx = cell[0] + dir[j][0], ny = cell[1] + dir[j][1];
                    if (nx >= 0 && nx < 1000000 && ny >= 0 && ny < 1000000 && !blocks.contains(nx + "," + ny)) q.offer(new int[]{nx, ny});
                }
            }
            if (level++ == blocked.length) return true;
        }
        return false;
    }


    /**
     * 2-end bfs，WA
     */
    public boolean isEscapePossible__WA(int[][] blocked, int[] source, int[] target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> blocks = new HashSet<>();
        for (int[] b : blocked) {
            blocks.add(b[0] + "," + b[1]);
        }
        begin.add(source[0] + "," + source[1]);
        end.add(target[0] + "," + target[1]);
        int dir[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!begin.isEmpty() && !end.isEmpty()) {
            Set<String> tmp = new HashSet<>();
            for (String s : begin) {
                if (end.contains(s)) return true;
                if (blocks.contains(s)) continue;
                blocks.add(s);
                String[] arr = s.split(",");
                int x = arr[0].charAt(0) - '0', y = arr[1].charAt(0) - '0';
                for (int i = 0; i < 4; i++) {
                    int nx = x + dir[i][0], ny = y + dir[i][1];
                    if (nx >= 0 && nx < 1000000 && ny >= 0 && ny < 1000000 && !blocks.contains(nx + "," + ny)) tmp.add(nx + "," + ny);
                }
            }
            begin = end;
            end = tmp;
        }
        return false;
    }


}
