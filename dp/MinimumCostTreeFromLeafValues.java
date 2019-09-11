package dp;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 * <p>
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 * Example 1:
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 * 24            24
 * /  \          /  \
 * 12   4        6    8
 * /  \               / \
 * 6    2             2   4
 * Constraints:
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 * 20190722
 */
public class MinimumCostTreeFromLeafValues {
    /**
     * 这题是典型的区间DP，经验丰富的人似乎一眼就看出来了。(我参考这个视频写的:https://www.bilibili.com/video/av60100178?t=3460, 类似的答案还有：https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340027/Java-DP-easy-to-understand)
     * 我想了一下为什么这个场景适合区间DP，这题是从小的区间(node自底向上)向大的区间扩展。
     * 另外，lee给了一种O(n)的做法，我先不了解了，因为区间DP在大佬们眼里都已经是naive解法了，我还是第一次遇到。。
     * <p>
     * 第一个for: 枚举区间长度len
     * 第二个for: 枚举起点l
     * 第三个for: 枚举[l, l + len)上的每一个位置
     */
    public int mctFromLeafValues(int[] arr) {
        int size = arr.length;
        int dp[][] = new int[size][size];//dp[i][j]代表[i,j]区间的非叶节点的最小sum
        for (int len = 1; len < size; len++) {//len starts from 1
            for (int l = 0; l < size; l++) {
                int r = l + len;
                if (r >= size) break;
                dp[l][r] = Integer.MAX_VALUE;
                for (int mid = l; mid < r; mid++) {
                    int lmax = findMax(arr, l, mid), rmax = findMax(arr, mid + 1, r);
                    int val = lmax * rmax + dp[l][mid] + dp[mid + 1][r];
                    if (dp[l][r] > val) dp[l][r] = val;
                }
            }
        }
        return dp[0][size - 1];
    }

    private int findMax(int[] arr, int l, int r) {
        int res = arr[l];
        for (int i = l + 1; i <= r; i++) res = Math.max(res, arr[i]);
        return res;
    }
}
