package twopointers;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * 20201016
 */
public class SquaresOfASortedArray {
    /**
     * 题意：给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。其中，A 已按非递减顺序排序。
     * 解法：two pointers，利用A 已按非递减顺序排序的条件。
     */
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int l = 0, r = n - 1;
        int[] res = new int[n];
        int i = n - 1;
        while (l <= r) {
            if (Math.abs(A[l]) < Math.abs(A[r])) {
                res[i--] = A[r] * A[r--];
            } else {
                res[i--] = A[l] * A[l++];
            }
        }
        return res;
    }
}
