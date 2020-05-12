package heap;

import java.util.PriorityQueue;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.
 * Example 1:
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 * <p>
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 * <p>
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 * Example 4:
 * <p>
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] is a non decreasing array.
 * 20200512
 */
public class FindtheKthSmallestSumofaMatrixWithSortedRows {
    /**
     * 题意：给你一个m * n的矩阵，你可以从每一行取一个数，最终加起来得到一个和。求第k小的和。
     * 暴力的解法肯定是O(n ^ m) 级别。
     * 解法1：堆。看到topic写的是heap，从左到右模拟了好久也没搞定。看了讨论区，很巧妙，别人是从上到下模拟的，大小为k的最大堆中保存截至上一轮为止最小的k个sum，
     * 注意下一轮要把堆中所有的数字都拿出来跟这一行的数字加一下放到堆里；最后堆顶正好就是我们要的数。
     * <p>
     * 另，这题可以用binary search：https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/solution/er-fen-by-newbie-19-3/
     **/
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<Integer> prev = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) prev.offer(mat[0][i]);
        for (int i = 1; i < m; i++) {
            PriorityQueue<Integer> cur = new PriorityQueue<>((a, b) -> b - a);
            for (int node : prev) { // 堆中的k个数都要和下一行的数相加一次，这样可以得到迄今为止最小的k个数
                for (int j = 0; j < n; j++) {
                    cur.offer(node + mat[i][j]);
                    if (cur.size() > k) cur.poll();
                }
            }
            prev = cur;
        }
        return prev.poll();
    }
}
