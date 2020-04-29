package slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subset of that array such that for every two consecutive integers in the subset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * A subset of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
 * Example 1:
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subset is [10, 2, 5, 20].
 * Example 2:
 * <p>
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subset must be non-empty, so we choose the largest number.
 * Example 3:
 * <p>
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subset is [10, -2, -5, 20].
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 20200428
 */
public class ConstrainedSubsetSum {
    /**
     * 题意：给你一个整形数组，找出一个子数组，但是它们的元素在原数组中的相隔不能超过k，问这个字数组的最大和是多少。
     * 转移方程（dp[i] = nums[i] + max(0, dp[i-k], dp[i-k+1], ..., dp[i-1])）比较容易理解，关键点显然在于怎么找过去k个数字中的最大元素。
     * 解法：DP + sliding window(双端队列，单调递减队列，顶部小的栈)
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length]; // dp[i]存放截至index i为止，必须包含nums[i]的subset的最大和
        dp[0] = nums[0];
        int res = dp[0];
        Deque<Integer> deque = new ArrayDeque();//单调递减队列，队首是窗口的最大值，
        deque.addFirst(dp[0]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(deque.getFirst() + nums[i], nums[i]);
            // System.out.println(dp[i]);
            res = Math.max(res, dp[i]);
            while (!deque.isEmpty() && deque.getLast() < dp[i]) {
                deque.removeLast(); // 栈顶比当前最大值小的元素出队
            }
            deque.addLast(dp[i]);
            if (i - k >= 0 && dp[i - k] == deque.getFirst()) //窗口外的出队
                // 如果栈底中最大元素就等于下一轮即将出队的那个元素为止的最大值，说明这个栈底的数已经不能要了，例子：[10, -1, -1, -1, 20],k=2，在i=3的时候必须要将截至0为止的最大值10移除了；
                // 而对于[10, 2, -10, 5, 20]，k=2，i=3，dp[3] = 17, dp[3 - 2] = 12，这时候就不能把栈底移除。
                deque.removeFirst();
        }
        return res;
    }

    /**
     * 我模仿sliding window maximum 的答案，stack中存放的是下标，这样判断出队比较清晰
     */
    public int constrainedSubsetSum_(int[] nums, int k) {
        int[] dp = nums.clone();
        dp[0] = nums[0];
        int res = dp[0];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        for (int i = 1; i < nums.length; i++) {
            if (!q.isEmpty() && i - q.peekFirst() > k) { //窗口外的出队
                q.pollFirst();
            }
            dp[i] = Math.max(nums[i], dp[q.peekFirst()] + nums[i]);
            // System.out.println(dp[i]);
            res = Math.max(dp[i], res);
            while (!q.isEmpty() && dp[i] > dp[q.peekLast()]) { //栈顶比nums[i]小的数字都出队
                q.pollLast();
            }
            q.offer(i);
        }
        return res;
    }

    public static void main(String[] arg) {
        new ConstrainedSubsetSum().constrainedSubsetSum_(new int[]{10, -2, -10, -5, 20}, 2);
    }
}
