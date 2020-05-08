package dp.statecompress;

import java.util.ArrayList;
import java.util.List;

/**
 * 总共有 n 个人和 40 种不同的帽子，帽子编号从 1 到 40 。
 * 给你一个整数列表的列表 hats ，其中 hats[i] 是第 i 个人所有喜欢帽子的列表。
 * 请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。
 * 由于答案可能很大，请返回它对 10^9 + 7 取余后的结果。
 * 示例 1：
 * 输入：hats = [[3,4],[4,5],[5]]
 * 输出：1
 * 解释：给定条件下只有一种方法选择帽子。
 * 第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。
 * 示例 2：
 * <p>
 * 输入：hats = [[3,5,1],[3,5]]
 * 输出：4
 * 解释：总共有 4 种安排帽子的方法：
 * (3,5)，(5,3)，(1,3) 和 (1,5)
 * 示例 3：
 * <p>
 * 输入：hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
 * 输出：24
 * 解释：每个人都可以从编号为 1 到 4 的帽子中选。
 * (1,2,3,4) 4 个帽子的排列方案数为 24 。
 * 示例 4：
 * <p>
 * 输入：hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
 * 输出：111
 * 提示：
 * n == hats.length
 * 1 <= n <= 10
 * 1 <= hats[i].length <= 40
 * 1 <= hats[i][j] <= 40
 * hats[i] 包含一个数字互不相同的整数列表。
 * 20200507
 */
public class NumberofWaystoWearDifferentHatstoEachOther {
    /**
     * 题意：给n个人分配帽子，一共有40个帽子，每个人有自己的喜好，问分配帽子的方案一共有多少。
     * 解法：状态压缩DP。状态压缩的意思就是，在状态特别多的时候，你不好记录之前的状态，比如你总不能拼接一个很长的字符串吧（可能真的可以？），那样操作起来很麻烦。
     * 所以用到bit，这里以帽子的维度来考虑的话，帽子数量太多不好压缩，所以考虑压缩人是否有帽子这个状态，把帽子分给人。这里也是借鉴了discuss里老哥的top down解法；
     * 但是我一开始没看懂为什么要跳过第i个帽子，直到看了wnjxyk的视频才恍然大悟；讲解得真好。【
     * q:
     * 大佬我能问一下嘛，为什么第四题很显然是状态压缩啊。
     * wnjxyk:
     * 从题目的角度，人和帽子都是我们需要表示在状态里面的信息，但是人和帽子的关系是复杂交错的，我们顺序枚举人或者帽子，另一个东西都不是顺序的。没顺序我们就没办法用单一变量去表示它了，只能用状态压缩去表示。
     * 从数据的角度，非常小的那一维很可能是让我们状态压缩用的。
     */
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        List<Integer>[] h2p = new List[41]; // h2p[i] indicates the list of people who can wear i_th hat
        for (int i = 1; i <= 40; i++) h2p[i] = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int hat : hats.get(i))
                h2p[hat].add(i);
        Integer[][] dp = new Integer[41][1024];
        return dfs(((1 << hats.size()) - 1), 0, dp, h2p, 1);
    }

    /**
     * 返回[1, i - 1]顶帽子都已经被assign的情况下（其中有些帽子可以没人戴），每个人是否被分配到帽子的情况用mask表示的情况下，最多有多少种分配方案
     **/
    private int dfs(int all, int mask, Integer[][] memo, List<Integer>[] h2p, int i) {
        if (mask == all) return 1;
        if (i > 40) return 0;
        if (memo[i][mask] != null) return memo[i][mask];
        int res = dfs(all, mask, memo, h2p, i + 1); // 第i顶帽子不分配给任何人
        for (int p : h2p[i]) {
            if (((mask >> p) & 1) == 1) continue; // 第p个人已经有帽子了，跳过
            res += dfs(all, mask | (1 << p), memo, h2p, i + 1);
            res %= (int) (1e9) + 7;
        }
        memo[i][mask] = res;
        return res;
    }

    int MOD = (int) (1e9) + 7;

    /**
     * 状压bottom up写法，参考https://www.bilibili.com/video/BV1hZ4y1s79w
     */
    public int numberWays_(List<List<Integer>> hats) {
        int n = hats.size();
        int all = 1 << n;
        int[][] dp = new int[41][1 << 10]; // dp[i][j]代表前有i顶帽子的、分配情况是j的情况下总共的方案数。1<<10意思是10个人，每个人两种状态。
        dp[0][0] = 1; // 0顶帽子分给0个人，一种分法
        for (int h = 1; h <= 40; h++) {
            for (int mask = 0; mask < all; mask++) {
                if (dp[h - 1][mask] == 0) continue; // 0的加到当前状态也无影响，所以跳过
                for (int p = 0; p < n; p++) {
                    if (!hats.get(p).contains(h)) continue; // 他不喜欢这顶帽子
                    if (((mask >> p) & 1) == 1) continue; // 这个人已经戴上了这顶帽子
                    int newMask = mask | (1 << p); // 把帽子分给这个人
                    dp[h][newMask] = (dp[h][newMask] + dp[h - 1][mask]) % MOD;
                }
            }
            for (int mask = 0; mask < all; mask++) {
                dp[h][mask] = (dp[h][mask] + dp[h - 1][mask]) % MOD;
            }
        }
        return dp[40][all - 1];
    }
}
