package binarysearch;

import java.util.Arrays;

/**
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
 * <p>
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * 20190713
 */
public class FindKthSmallestPairDistance {
    /**
     * 数组中任意两个数之间的距离，求第k小的那一个。既然是第k小，那常理上应该算出所有的pair之间的距离(O(n2))然后做quick select。
     * 或者有没有办法不用找出所有pair之间的距离？
     * Solutions的第一种heap解法就不用找出所有pair的距离，但是仍然超时了；
     * 思路是先sort，定义一个node(root, neighbor)结构，第一轮用(i, i+1)构造node然后传入piorityqueue(comparator规则是邻居的值减去自身的值)，然后做k次poll，同同时把nei+1替换nei。类似bfs。
     * 能够AC的办法有两种，第一种bucket，第二种binary search + dp，这里记录第二种，看题解看了好久没看懂，看花花视频基本看懂了，才知道这是一种类似dp的思想。
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = nums[n - 1] - nums[0];//距离的最大值
        while (l < r) {
            int cnt = 0;
            int m = l + (r - l) / 2;//二分查找m，计算distance <= m的pairs的数量cnt
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && nums[j] - nums[i] <= m) j++;//寻找第一个distance>m的数的index j，也就是upper bound
                cnt += j - 1 - i;//累加，相当于dp[i] = dp[i - 1] + (j - 1 - i), dp[i]代表截止到nums[i]，<=m的pair的个数
            }
            if (cnt < k) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;//其实l == m。要求的是第k小的距离，这个距离就在l和r之间。
    }
}
