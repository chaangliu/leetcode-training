package binarysearch.templateii;

import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2
 * <p>
 * 20190525
 * 相似题目：668. Kth Smallest Number in Multiplication Table，373. Find K Pairs with Smallest Sums
 */
public class KthSmallestElementInASortedMatrix {
    /**
     * 题意：给你一个每行、每列都排好序的数组，求第k小元素。
     * 非常好的一道题，两种方法都很值得学习。
     * Approach1. 最小堆
     * 这题一看就是二分搜索了，O(n)肯定超时。
     * 但是先看一种最小堆的解法。做法有点像BFS，
     * 这个操作跟373 Find K Pairs with Smallest Sums几乎一模一样，
     * 先把第一行的都加入堆（有点像贪心？），
     * 每次从堆顶取一个元素n出来，取出之后把n【下方】的元素加入堆(堆会重新排序)；
     * 操作k次，取出元素就是想要的元素。O(k log n)
     * 跟373一样，都相当于merge k sorted linked list, 这题是把纵向的n列看成n个sorted linked list
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> heap = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            heap.add(new Tuple(0, i, matrix[0][i]));
        }
        while (k-- > 1) {
            Tuple t = heap.poll();
            if (t.x == matrix.length - 1) continue;
            heap.add(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return heap.peek().val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

    /**
     * Approach2. binary search
     * 枚举一个数，寻找数组中有多少个数字<=它，满足，则hi = mid， 相当于lower_bound，所以一定会落在数组中的某个数上。
     * 时间：O(n log(r - l)), r和l分别是数组中的最大和最小的数字
     */
    public int kthSmallest__(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi), binary search Template II，这里我试了不+1也能AC
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; i++) {// 计算数组中一共有多少个数<=mid，记为count
                while (j >= 0 && matrix[i][j] > mid) j--;// 计算第i行有多少个数<=mid(其实这里横向应该也可以二分搜索，参考668题)
                count += (j + 1);
            }
            if (count >= k)
                hi = mid;
            else
                lo = mid + 1;// 显然，如果比<=mid的数 < k个，要找的数比mid大
        }
        return lo;
    }

    /**
     * 两次二分, beats 71.62%
     */
    public int kthSmallest_(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            for (int i = 0; i < matrix.length; i++) {
                int l = 0, r = n;
                while (l < r) {
                    int m = l + (r - l) / 2;
                    if (matrix[i][m] > mid) r = m;
                    else l = m + 1;
                }
                count += l;
            }
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
