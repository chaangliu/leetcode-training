package slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 * 示例 1：
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 * <p>
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * 20200505
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {

    /**
     * 题意：给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit。
     * 这题非常好，把单调栈运用得淋漓尽致；
     * 这题很像上周做的那个1425. Constrained Subsequence Sum，那题需要找出过去k个数字里面最大的，和sliding window maximum一样；
     * 这题是要找出window中最大和最小的数字。我当时就觉得是可以O(n)的，但是还是用了两个heap来做。
     * O(n)做法是，用两个Deque，一个是单调减的（head是最大的，tail是最小的），一个是单调增的；这样两个deque的head就分别记录了迄今最大和最小的值；
     * 当不满足limit时，就看刚加入的，要看窗口开始的那个数是在哪个deque里，然后从head remove掉，左边index++。因为每次都要把右边的数字加进来，
     * 所以两个index会一直右移，window不会缩小，最大的窗口会一直维持到最后。
     */
    public int longestSubarray(int[] A, int limit) {
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        int i = 0, j;
        for (j = 0; j < A.length; ++j) {
            while (!maxd.isEmpty() && A[j] > maxd.peekLast())
                maxd.pollLast();
            while (!mind.isEmpty() && A[j] < mind.peekLast())
                mind.pollLast();
            maxd.add(A[j]);
            mind.add(A[j]);
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == A[i])
                    maxd.poll();
                if (mind.peek() == A[i])
                    mind.poll();
                ++i;
            }
        }
        return j - i;
    }

    /**
     * 我的做法，两个heap，时间O(N log N)
     */
    public int longestSubarray_(int[] nums, int limit) {
        int n = nums.length, l = 0, r = 1, res = 1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        maxHeap.offer(nums[l]);
        minHeap.offer(nums[l]);
        while (r < n) {
            maxHeap.offer(nums[r]);
            minHeap.offer(nums[r]);
            if (Math.abs(maxHeap.peek() - minHeap.peek()) <= limit) {
                res = Math.max(res, r - l + 1);
                r++;
            } else {
                if (!minHeap.isEmpty() && nums[r] == minHeap.peek()) {
                    while (!maxHeap.isEmpty() && maxHeap.peek() - nums[r] > limit) {
                        maxHeap.remove(nums[l]);
                        minHeap.remove(nums[l]);
                        l++;
                    }
                    r++;
                } else {
                    while (!minHeap.isEmpty() && nums[r] - minHeap.peek() > limit) {
                        maxHeap.remove(nums[l]);
                        minHeap.remove(nums[l]);
                        l++;
                    }
                    r++;
                }
            }
        }
        return res;
    }

    /**
     * TreeMap做法，O(N log N)
     */
    public int longestSubarray__(int[] A, int limit) {
        int i = 0, j;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (j = 0; j < A.length; j++) {
            m.put(A[j], 1 + m.getOrDefault(A[j], 0));
            if (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                m.put(A[i], m.get(A[i]) - 1);
                if (m.get(A[i]) == 0)
                    m.remove(A[i]);
                i++;
            }
        }
        return j - i;
    }

    public static void main(String[] args) {
        new LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit().longestSubarray(new int[]{2, 1, 100, 200, 300, 400}, 4);
    }
}
