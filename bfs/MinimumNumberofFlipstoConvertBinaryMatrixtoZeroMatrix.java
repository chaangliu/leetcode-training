package bfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.
 * <p>
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 * <p>
 * Binary matrix is a matrix with all cells equal to 0 or 1 only.
 * <p>
 * Zero matrix is a matrix with all cells equal to 0.
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
 * Example 2:
 * <p>
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We don't need to change it.
 * Example 3:
 * <p>
 * Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
 * Output: 6
 * Example 4:
 * <p>
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix can't be a zero matrix
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[0].length
 * 1 <= m <= 3
 * 1 <= n <= 3
 * mat[i][j] is 0 or 1.
 * 20191208
 */
public class MinimumNumberofFlipstoConvertBinaryMatrixtoZeroMatrix {
    /**
     * 题意：给你一个m * n的二维数组，1 <= m，n <= 3，每次选一个数字把它和它相邻的数字flip一下，问最少flip几次能flip到全0。
     * contest第四题，拿到这题还有40分钟，我看了数据范围就想到DFS + MEMO，但是很遗憾最后被一个死循环卡住了。
     * 看了有人有了类似解法，用了一个set记录visited，防止死循环。值得学习。
     * 解法1，DFS + MEMO
     */
    public int minFlips(int[][] mat) {
        int min = Integer.MAX_VALUE;
        int m = mat.length, n = mat[0].length;
        min = Math.min(min, dfs(mat, new HashSet<>(), new HashMap<>(), m, n));
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int dfs(int[][] mat, Set<String> visited, Map<String, Integer> memo, int m, int n) {
        StringBuilder key = new StringBuilder();
        boolean allZero = true;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                key.append(mat[i][j]);
                if (mat[i][j] != 0) allZero = false;
            }
        if (allZero) return 0;
        if (memo.containsKey(key.toString())) return memo.get(key.toString());
        if (visited.contains(key.toString())) return Integer.MAX_VALUE;//这个set的过滤很关键，意思是防止循环，0001 => 1111 => 0001 => 1111 ..
        visited.add(key.toString());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 1 ^ mat[i][j];
                if (i - 1 >= 0)
                    mat[i - 1][j] = 1 ^ mat[i - 1][j];//这个backtrack的一对操作要放在for循环里面，我一开始放外面了
                if (i + 1 < m) mat[i + 1][j] = 1 ^ mat[i + 1][j];
                if (j - 1 >= 0) mat[i][j - 1] = 1 ^ mat[i][j - 1];
                if (j + 1 < n) mat[i][j + 1] = 1 ^ mat[i][j + 1];
                int res = dfs(mat, visited, memo, m, n);
                if (res != Integer.MAX_VALUE) min = Math.min(min, 1 + res);
                mat[i][j] = 1 ^ mat[i][j];
                if (i - 1 >= 0) mat[i - 1][j] = 1 ^ mat[i - 1][j];
                if (i + 1 < m) mat[i + 1][j] = 1 ^ mat[i + 1][j];
                if (j - 1 >= 0) mat[i][j - 1] = 1 ^ mat[i][j - 1];
                if (j + 1 < n) mat[i][j + 1] = 1 ^ mat[i][j + 1];
            }
        }
        visited.remove(key.toString());
        memo.put(key.toString(), min);
        return min;
    }

    /**
     * 解法2. BFS
     * 看到「最短」应该条件反射想到BFS。BFS的解法跟DFS除了执行顺序上略有差别之外，没什么大差别，都是遍历格子,毕竟数据量小的情况下就是按时你暴力搜索。
     * 这儿大家都用了bit来记录matrix状态，因为matrix很小，天然地就可以记录matrix状态，不用map成string了。
     **/
    int[] dir = new int[]{0, 0, 1, 0, -1, 0};//上下左右四个方向可以这么写

    public int minFlips_(int[][] mat) {
        int start = 0, m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++)//map the mat => int
            for (int j = 0; j < n; j++) {
                start |= mat[i][j] << i * n + j; // convert the matrix to an int.
            }
        Queue<Integer> q = new LinkedList<>(Arrays.asList(start));
        HashSet<Integer> visited = new HashSet<>();
        for (int res = 0; !q.isEmpty(); res++) {
            for (int size = q.size(); size > 0; size--) {//bfs技巧，把size放到for循环里声明
                int cur = q.poll();
                if (cur == 0) return res;
                for (int r = 0; r < m; r++) {//traverse, flip [r][c] and its neighborhood
                    for (int c = 0; c < n; c++) {
                        int next = cur;
                        for (int k = 0; k < 5; k++) {
                            int nr = r + dir[k], nc = c + dir[k + 1];
                            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;//已犯错误，这里nc >= n写成了nc >= c..找了一小时，脑子什么时候能在线呢T_T..
                            next ^= 1 << n * nr + nc;//flip

                        }
                        if (visited.add(next)) q.offer(next);//加入q之前就加入visited，这有可以减小q的体积；当然也可以在q.poll()的时候判断，不过那样就需要更多空间
                    }
                }
            }
        }
        return -1;
    }
}
