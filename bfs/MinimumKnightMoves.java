package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * <p>
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 * <p>
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * |x| + |y| <= 300
 * <p>
 * 20190922
 */
public class MinimumKnightMoves {
    public static final int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    /**
     * 这题一看就是BFS，但是写出来之后一直超时。。
     * 我用"x#y"作为visited的key，看到有个人用了1000 * x + y这种形式，试了一下竟然就AC了。很神奇。
     * 另外有人说只要处理第一象限，有点难以理解。
     */
    public int minKnightMoves(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int res = 1;
        HashSet<Integer> visited = new HashSet<>();
        if (x == 0 && y == 0) return 0;
        visited.add(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                int x1 = pair[0], y1 = pair[1];
                for (int[] d : dirs) {
                    int x2 = x1 + d[0], y2 = y1 + d[1];
                    if (x == x2 && y == y2) return res;
                    int key = 1000 * x2 + y2;
                    if (!visited.contains(key) && Math.abs(x2) + Math.abs(y2) <= 300) {
                        queue.offer(new int[]{x2, y2});
                        visited.add(key);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
