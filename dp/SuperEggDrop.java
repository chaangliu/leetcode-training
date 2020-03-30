package dp;

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * <p>
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * <p>
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
 * <p>
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * <p>
 * Your goal is to know with certainty what the value of F is.
 * <p>
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 * Example 1:
 * Input: K = 1, N = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 * If it didn't break, then we know with certainty F = 2.
 * Hence, we needed 2 moves in the worst case to know what F is with certainty.
 * Example 2:
 * <p>
 * Input: K = 2, N = 6
 * Output: 3
 * Example 3:
 * <p>
 * Input: K = 3, N = 14
 * Output: 4
 * Note:
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 * 20200330
 */
public class SuperEggDrop {
    /**
     * 题意：K个鸡蛋，N层楼，从某一层楼开始再往上开始扔，蛋一定会碎。让你找到最高的不会碎的那层楼。
     * 直观解法：top down dp + 二分优化。
     * 还有一种bottom up的方法非常简洁，我先不看了。
     **/
    public int superEggDrop(int K, int N) {
        return dfs(K, N, new int[K + 1][N + 1]);
    }

    /**
     * k个鸡蛋，n层最差情况需要仍多少次
     * while循环那儿可以用for来枚举楼层数，但是会超时；
     * 优化：left 和 当前楼层数正相关，right和 当前楼层数负相关，所以可以用二分来缩小范围
     **/
    private int dfs(int k, int n, int[][] memo) {
        if (k == 1) {
            return n; // 只有一个鸡蛋，需要从低到高一层层试
        }
        if (n <= 1) {
            return n; // 只有0~1层楼
        }
        if (memo[k][n] > 0) return memo[k][n];
        int lo = 1, hi = n, res = Integer.MAX_VALUE;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int left = dfs(k, mid - 1, memo); // 如果碎了，临界楼层在前面
            int right = dfs(k - 1, n - mid, memo); // 如果没碎，临界楼层在后面，蛋-1
            res = Math.min(res, Math.max(left, right) + 1); //鸡蛋从mid层开始扔，最差情况需要的次数是max(left,right)+1就一定能找到临界楼层
            if (left == right) break;
            if (left < right) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        memo[k][n] = res;
        return res;
    }
}
