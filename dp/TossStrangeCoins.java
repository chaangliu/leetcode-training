package dp;

/**
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 * Example 1:
 * Input: prob = [0.4], target = 1
 * Output: 0.40000
 * Example 2:
 * <p>
 * Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * Output: 0.03125
 * Constraints:
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * 20191021
 */
public class TossStrangeCoins {
    /**
     * 双周赛第三题。
     * 题意：给你一个prob数组存放抛每个硬币出现heads的概率；问把所有硬币抛一遍正好有target个heads朝上的概率。
     * 做法：概率DP。
     * dp[i][j] 代表抛完第i枚硬币后有j枚朝上的概率。dp[i][j]就等于抛完i-1枚之后有j-1枚朝上的概率 * 抛第i枚的时候朝上的概率 + 抛完i-1枚之后有j枚朝上的概率 * 抛第i枚时朝下的概率（也类似于爬台阶，当前状态=前两个状态之和）
     * dp[i][j] = dp[i - 1][j] * (1 - prob[i]) + dp[i - 1][j - 1] * prob[i]
     **/
    public double probabilityOfHeads(double[] prob, int target) {
        int len = prob.length;
        double[][] dp = new double[len + 1][len + 1];
        dp[1][0] = 1 - prob[0];
        dp[1][1] = prob[0];
        for (int i = 2; i <= len; i++) {
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);//这里注意prob的index
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1]) + dp[i - 1][j - 1] * prob[i - 1];
            }
        }
        return dp[len][target];
    }

    public double probabilityOfHeads__ROLLING_ARRAY(double[] prob, int target) {
        int len = prob.length;
        double[][] dp = new double[2][len + 1];
        dp[1][0] = 1 - prob[0];
        dp[1][1] = prob[0];
        for (int i = 2; i <= len; i++) {
            int cur = i & 1, prev = cur ^ 1;
            dp[cur][0] = dp[prev][0] * (1 - prob[i - 1]);
            for (int j = 1; j <= target; j++) {
                dp[cur][j] = dp[prev][j] * (1 - prob[i - 1]) + dp[prev][j - 1] * prob[i - 1];
            }
        }
        return dp[len & 1][target];
    }
}
