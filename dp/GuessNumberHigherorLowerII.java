package dp;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * <p>
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * <p>
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * <p>
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * <p>
 * 示例:
 * <p>
 * n = 10, 我选择了8.
 * <p>
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * <p>
 * 游戏结束。8 就是我选的数字。
 * <p>
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 * 20190914
 */
public class GuessNumberHigherorLowerII {
    /**
     * 这题不能用二分的方式来猜（举例子：如果是{1,2,3,4,5},那么二分的思路肯定是先猜3，那么看是大是小，如果大了，就猜2，如果小了，猜4，接下来就一定可以猜中答案。那么如果答案是5，二分就要付出3+4=7的金额。如果是1，那么就要付出5。如果答案是3，那么就是0……。这里面付的钱有0，有5，有7。那到底最少要准备多少钱呢？我说只要6块钱。为何呢，先猜4，如果小了，那么就一定是5，那就是4元，如果大了，就猜2，接下来就一定猜到答案，那么就是6元，所以说，最多只要准备6元。https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/dong-tai-gui-hua-zhi-xing-yong-shi-736mszai-suo-yo/）。
     * 具体，这个是一个minimax算法。
     * 策略是什么呢，每次的策略可能都不一样，需要暴力去找最小能cover worst case的价钱。
     * cost(l, r) = min(i + max(cost(l, i - 1), cost (i + 1, r))), i from 1 .. n
     * <p>
     * Approach1 top-down dfs with memo
     **/
    public int getMoneyAmount(int n) {
        return helper(1, n, new int[n + 1][n + 1]);
    }

    /**
     * 求(l, r)之间能cover 所有worst case中的最小值
     **/
    private int helper(int l, int r, int[][] memo) {
        if (l >= r) return 0;
        if (memo[l][r] != 0) return memo[l][r];
        int res = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            int max = i + Math.max(helper(l, i - 1, memo), helper(i + 1, r, memo));//每次选开销大的那一侧dfs，保证cover worst case
            res = Math.min(max, res);//在所有cover住worst case 的情况里选一个最优解，注意这个res是局部变量
        }
        memo[l][r] = res;
        return res;
    }

    /**
     * Approach2, 改写一下，bottom up, 区间dp
     */
    public int getMoneyAmount_(int n) {
        int[][] table = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int globalMin = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int localMax = k + Math.max(table[i][k - 1], table[k + 1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                table[i][j] = i + 1 == j ? i : globalMin;
            }
        }
        return table[1][n];
    }
}
