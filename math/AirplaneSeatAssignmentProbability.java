package math;


/**
 * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of passengers will:
 * Take their own seat if it is still available,
 * Pick other seats randomly when they find their seat occupied
 * What is the probability that the n-th person can get his own seat?
 * Example 1:
 * Input: n = 1
 * Output: 1.00000
 * Explanation: The first person can only get the first seat.
 * Example 2:
 * Input: n = 2
 * Output: 0.50000
 * Explanation: The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
 * Constraints:
 * 1 <= n <= 10^5
 * 20191109
 */
public class AirplaneSeatAssignmentProbability {
    /**
     * 题意：有一个n座的飞机，第一个人因为票丢了所以会随便坐；后面的人会优先选择自己的座位，如果被占了，就随便坐。问第n个人能坐到自己座位的概率。
     * Hint: dp[i] indicates the probability that the i-th person can get his seat when     there're i persons in total. It's okay to start with O(n^2) solution and then optimize it.
     * 第一个人：1/n + (n - 2)/n * 第二个人没占用第n个人的座位的概率（我选了自己的座位，或者我没选自己座位和第n个人的座位，剩下的就看第二个人的选择了）
     * 第二个人：1/(n-1) + ((n - 1) - 2)/(n - 1)
     **/
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
}
