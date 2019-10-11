package dp;

/**
 * Alice plays the following game, loosely based on the card game "21".
 * <p>
 * Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
 * <p>
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 * <p>
 * Example 1:
 * <p>
 * Input: N = 10, K = 1, W = 10
 * Output: 1.00000
 * Explanation:  Alice gets a single card, then stops.
 * Example 2:
 * <p>
 * Input: N = 6, K = 1, W = 10
 * Output: 0.60000
 * Explanation:  Alice gets a single card, then stops.
 * In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 * Example 3:
 * <p>
 * Input: N = 21, K = 17, W = 10
 * Output: 0.73278
 * Note:
 * <p>
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * The judging time limit has been reduced for this question.
 * 20191011
 */
public class New21Game {
    /**
     * 题意：在[1,W]中不停地抽卡，sum>=K时停止抽卡，求sum最终落在[K,N]之间的概率
     * 挺难的数学题。。傻子一样看了一个小时终于差不多懂了，看到怀疑人生。
     * 用到了条件概率，dp[i]表示达到sum=i的概率；
     * dp[i] = dp[i - W] * 1/W + ... dp[i-2] * 1/W + dp[i-1] * 1/W，也就是抽一张卡可以达到i，可以化简成dp[i] = Wsum/W
     * 详细可参考lee答案下的评论。
     **/
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) {
            return 1;
        }
        double result = 0;
        double dp[] = new double[N + 1];//获得sum = i的概率
        double Wsum = 1;//最后W个dp的和，也就是dp[i] + dp[i - 1] + .. + dp[i - W - 1]
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = Wsum / W;//抽到的sum是i的概率是条件概率想加，等于[i-W, i]窗口中所有概率的和/W，因为只需要在[1,W]中抽取一张就能达到i，概率都是1/W；dp[i] = dp[1] * 1/W + dp[2] * 1/W + dp[3] * 1/W + ... dp[i-2] * 1/W + dp[i-1] * 1/W
            if (i < K) {
                Wsum += dp[i];
            } else {
                result += dp[i];//i >= k, dp[K] + dp[K + 1] + .. + dp[N]就是sum落在[K,N]之间的概率
            }
            // when i is over W, then we need to move the window
            if (i - W >= 0) {
                Wsum -= dp[i - W];
            }
        }
        return result;
    }
}
