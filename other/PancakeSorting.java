package other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 * <p>
 * Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 * Example 1:
 * <p>
 * Input: [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k=4): A = [1, 4, 2, 3]
 * After 2nd flip (k=2): A = [4, 1, 2, 3]
 * After 3rd flip (k=4): A = [3, 2, 1, 4]
 * After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
 * Example 2:
 * <p>
 * Input: [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 * Note:
 * 1 <= A.length <= 100
 * A[i] is a permutation of [1, 2, ..., A.length]
 * 20191011
 */
public class PancakeSorting {
    class Solution {
        /**
         * 题意：给你一个1到n的数组的permutation，让你通过多次reverse前k个数字来最终实现数组排序，返回k的记录
         * 这题仍然是个模拟题，我没想出来(首先我没注意到n的permutation这一条件)，看了答案是这么模拟的：
         * 0. 用一个pointer x指向末位元素的index
         * 1. 找到数组中最大的数的位置k
         * 2. 对0~k位置进行reverse，这样最大的数变成第一个了；同时把k加入结果集
         * 3. 对0~x位置进行reverse，这样最大数翻转到它应有的位置上；同时把k加入结果集
         */
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> res = new ArrayList<>();
            for (int x = A.length, i; x > 0; --x) {
                for (i = 0; A[i] != x; ++i) ;
                reverse(A, i + 1);
                res.add(i + 1);
                reverse(A, x);
                res.add(x);
            }
            return res;
        }

        public void reverse(int[] A, int k) {
            for (int i = 0, j = k - 1; i < j; i++, j--) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
    }
}
