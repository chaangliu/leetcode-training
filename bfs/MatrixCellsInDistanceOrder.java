package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.
 * <p>
 * Additionally, we are given a cell in that matrix with coordinates (r0, c0).
 * <p>
 * Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: R = 1, C = 2, r0 = 0, c0 = 0
 * Output: [[0,0],[0,1]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1]
 * Example 2:
 * <p>
 * Input: R = 2, C = 2, r0 = 0, c0 = 1
 * Output: [[0,1],[0,0],[1,1],[1,0]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
 * The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
 * Example 3:
 * <p>
 * Input: R = 2, C = 3, r0 = 1, c0 = 2
 * Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
 * There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * <p>
 * 20190422
 */
public class MatrixCellsInDistanceOrder {
    /**
     * approach1 bfs
     * 这题我也想到了BFS但是用Set记录visited的时候总是有些case过不了百思不得其解，后来看别人用的是坐标（虽然很浪费空间），想想有可能是由于不同string的string.hash相同导致的？
     * 6         for (int i = 0; i < value.length; i++) {
     * 7             hash = 31 * h + val[i];
     * 8         }
     * <p>
     * 【20190424】评论区别人的回复我，并不是hashCode导致的，原因是1，11和11，1这种会导致相同，加上分隔符即可
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        Queue<int[]> q = new LinkedList<>();
        boolean visited[][] = new boolean[R][C];
        int[][] res = new int[R * C][2];
        q.offer(new int[]{r0, c0});
        int index = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                int x = tmp[0], y = tmp[1];
                if (x < 0 || x >= R || y < 0 || y >= C) continue;
                if (visited[x][y]) continue;
                res[index++] = tmp;
                visited[x][y] = true;
                q.offer(new int[]{x - 1, y});//无论是否越界全丢进去下一层处理；优点是写的代码少，缺点是占用空间多
                q.offer(new int[]{x + 1, y});
                q.offer(new int[]{x, y - 1});
                q.offer(new int[]{x, y + 1});
            }
        }
        return res;
    }


    /**
     * 使用String + set记录visited
     */
    public int[][] allCellsDistOrder__SET(int R, int C, int r0, int c0) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int[][] res = new int[R * C][2];
        q.offer(new int[]{r0, c0});
        int index = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                int x = tmp[0], y = tmp[1];
                if (x < 0 || x >= R || y < 0 || y >= C) continue;
                if (visited.contains(x + "," + y)) continue;
                res[index++] = tmp;
                visited.add(x + "," + y);
                q.offer(new int[]{x - 1, y});
                q.offer(new int[]{x + 1, y});
                q.offer(new int[]{x, y - 1});
                q.offer(new int[]{x, y + 1});
            }
        }
        return res;
    }

    /**
     * approach2 sort
     * 先生成所有排列然后排序
     */
    public int[][] allCellsDistOrder__SORT(int R, int C, final int r0, final int c0) {
        int[][] origin = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                origin[i * C + j] = new int[]{i, j};
            }
        }

        Arrays.sort(origin, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Math.abs(a[0] - r0) + Math.abs(a[1] - c0)
                        - Math.abs(b[0] - r0) - Math.abs(b[1] - c0);
            }
        });
        return origin;
    }

    /**
     * list.sort 2020
     */
    public int[][] allCellsDistOrder__(int R, int C, int r0, int c0) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                list.add(new int[]{i, j});
            }
        }
        Collections.sort(list, (a, b) -> Math.abs(a[0] - r0) + Math.abs(a[1] - c0) - Math.abs(b[0] - r0) - Math.abs(b[1] - c0));
        return list.toArray(new int[list.size()][]);
    }
}
