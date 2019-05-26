package greedy;

/**
 * Given an array A of positive integers (not necessarily distinct), return the lexicographically largest permutation that is smaller than A, that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).  If it cannot be done, then return the same array.
 * Example 1:
 * <p>
 * Input: [3,2,1]
 * Output: [3,1,2]
 * Explanation: Swapping 2 and 1.
 * Example 2:
 * <p>
 * Input: [1,1,5]
 * Output: [1,1,5]
 * Explanation: This is already the smallest permutation.
 * Example 3:
 * <p>
 * Input: [1,9,4,6,7]
 * Output: [1,7,4,6,9]
 * Explanation: Swapping 9 and 7.
 * Example 4:
 * <p>
 * Input: [3,1,1,3]
 * Output: [1,3,1,3]
 * Explanation: Swapping 1 and 3.
 * 20190526
 */
public class PreviousPermutationWithOneSwap {

    /**
     * contest的第三题，很扯，第四个例子在比赛过程中给错了，给出的output[1,1,3,3]，非常误导人
     * <p>
     * 思路上这题跟Next Permutation思路一样，相当于复习一下了；但是最后不需要reverse，因为是one swap。
     */
    public int[] prevPermOpt1(int[] A) {
        int i = A.length - 2;
        while (i >= 0 && A[i + 1] >= A[i]) {
            i--;
        }
        if (i >= 0) {
            int j = A.length - 1;
            while (j >= 0 && A[j] >= A[i] || (j > 0 && A[j] == A[j - 1])) {
                j--;
            }
            swap(A, i, j);
        }
        return A;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
