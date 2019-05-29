package dfs.dfswithcache;

import java.util.HashMap;
import java.util.Map;

/**
 * A chess knight can move as indicated in the chess diagram below:
 * <p>
 * .
 * <p>
 * <p>
 * <p>
 * This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 * <p>
 * Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
 * <p>
 * How many distinct numbers can you dial in this manner?
 * <p>
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: 10
 * Example 2:
 * <p>
 * Input: 2
 * Output: 20
 * Example 3:
 * <p>
 * Input: 3
 * Output: 46
 * Note:
 * <p>
 * 1 <= N <= 5000
 * <p>
 * 20190529
 */
public class KnightDialer {

    /**
     * 这题TAG是dp，但我感觉dfs with memo更直观。DP跟DFS确实有密切关联啊。
     * 1. dfs(TLE)
     */
    public static final int max = (int) Math.pow(10, 9) + 7;

    public int knightDialer__NAIVE(int n) {
        long s = 0;
        //do n hops from every i, j index (the very requirement of the problem)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                s = (s + paths(i, j, n)) % max;
            }
        }
        return (int) s;
    }

    private long paths(int i, int j, int n) {
        if (i < 0 || i > 3 || j < 0 || j > 2 || i == 3 && j != 1) return 0;
        if (n == 1) return 1;//关键；这里不能放到上面返回0的条件里。
        return paths(i - 2, j - 1, n - 1) + paths(i - 2, j + 1, n - 1)
                + paths(i - 1, j - 2, n - 1) + paths(i - 1, j + 2, n - 1)
                + paths(i + 2, j - 1, n - 1) + paths(i + 2, j + 1, n - 1)
                + paths(i + 1, j - 2, n - 1) + paths(i + 1, j + 2, n - 1);
    }

    /**
     * 2. dfs with cache
     */
    public int knightDialer__CACHE(int n) {
        long s = 0;
        long dp[][][] = new long[4][3][n + 1];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                s = (s + dfs(dp, i, j, n)) % max;
            }
        }
        return (int) s;
    }

    private long dfs(long[][][] dp, int i, int j, int n) {
        if (i < 0 || i > 3 || j < 0 || j > 2 || i == 3 && j != 1) return 0;
        if (n == 1) return 1;
        if (dp[i][j][n] != 0) return dp[i][j][n];//pruning
        //这种写法是第二次遇到，第一次也是一道dfs with cache的题
        dp[i][j][n] = dfs(dp, i - 2, j - 1, n - 1) % max + dfs(dp, i - 2, j + 1, n - 1) % max
                + dfs(dp, i - 1, j - 2, n - 1) % max + dfs(dp, i - 1, j + 2, n - 1) % max
                + dfs(dp, i + 2, j - 1, n - 1) % max + dfs(dp, i + 2, j + 1, n - 1) % max
                + dfs(dp, i + 1, j - 2, n - 1) % max + dfs(dp, i + 1, j + 2, n - 1) % max;
        return dp[i][j][n];
    }


    /**
     * 3. dfs with cache ，Map写法，节省空间
     */
    private static final int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    Map<String, Long> pathCount = new HashMap<>();

    public int knightDialer(int N) {
        long res = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                res = (res + helper(i, j, N)) % max;
            }
        }
        return (int) res;
    }

    public long helper(int i, int j, int n) {

        if (i < 0 || i >= 4 || j < 0 || j >= 3 || (i == 3 && j != 1))
            return 0;
        if (n == 1)
            return 1;
        String key = i + "_" + j + "_" + n;
        if (pathCount.containsKey(key)) return pathCount.get(key);
        long ans = 0;
        for (int k = 0; k < dirs.length; k++) {
            ans += helper(i + dirs[k][0], j + dirs[k][1], n - 1) % max;
        }
        pathCount.put(key, ans);
        return ans;
    }

    /**
     * 4. 二维top down dp，不包含n
     */
    public static final int MOD = 1000000007;

    public int knightDialer__DP(int N) {
        int[][] graph = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};
        int cnt = 0;
        Integer[][] memo = new Integer[N + 1][10];
        for (int i = 0; i <= 9; i++)
            cnt = (cnt + helper(N - 1, i, graph, memo)) % MOD;
        return cnt;
    }

    private int helper(int N, int cur, int[][] graph, Integer[][] memo) {
        if (N == 0) return 1;
        if (memo[N][cur] != null) return memo[N][cur];
        int cnt = 0;
        for (int nei : graph[cur]) cnt = (cnt + helper(N - 1, nei, graph, memo)) % MOD;
        memo[N][cur] = cnt;
        return cnt;
    }
}
