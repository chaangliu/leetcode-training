package slidingwindow;

import java.util.HashMap;

/**
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * Example 1:
 * <p>
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 * <p>
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 * <p>
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 * Example 4:
 * <p>
 * Input: cardPoints = [1,1000,1], k = 1
 * Output: 1
 * Explanation: You cannot take the card in the middle. Your best score is 1.
 * Example 5:
 * <p>
 * Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * Output: 202
 * Constraints:
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 * 20200426
 */
public class MaximumPointsYouCanObtainfromCards {
    /**
     * 题意：一串数字，每次从两端选择一个取走，一共能取k个，问最大sum能取到多少。
     * 解法：乍看我觉得很像minmax，因为要从两头取；但是递归+memo写了一下发现总是超时，虽然加了memo，毕竟比不上one pass。
     * 脑子要转个方向，这题其实是sliding window，取k张，最后一定剩下连续n-k张牌，相当于求这n-k张的最小值。
     **/
    public int maxScore(int[] cardPoints, int k) {
        int min = Integer.MAX_VALUE, cur = 0, sum = 0, n = cardPoints.length;
        for (int i = 0; i < n; i++) {
            if (i < n - k) cur += cardPoints[i];
            sum += cardPoints[i];
        }
        min = cur;
        for (int i = 0; i + n - k < n; i++) {
            cur = cur - cardPoints[i] + cardPoints[i + n - k];
            min = Math.min(min, cur);
        }
        return sum - min;
    }


    /**
     * brute force做法: 超时
     */
    public int maxScore__(int[] cards, int k) {
        HashMap<Long, Integer> memo = new HashMap<>();
        return dfs(cards, k, 0, cards.length - 1, 0, memo);
    }

    private int dfs(int[] cards, int k, int l, int r, int sum, HashMap<Long, Integer> memo) {
        if (k == 0 || l > r) return sum;
        long key = l * (int) 1e5 + r;
        if (memo.containsKey(key)) return memo.get(key);
        int ll = dfs(cards, k - 1, l + 1, r, sum + cards[l], memo);
        int rr = dfs(cards, k - 1, l, r - 1, sum + cards[r], memo);
        int res = Math.max(ll, rr);
        memo.put(key, res);
        return res;
    }
}
