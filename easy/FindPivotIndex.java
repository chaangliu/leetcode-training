package easy;

public class FindPivotIndex {
    /**
     * 题意：找到第一个index，使得它左边所有数字的和等于右边所有数字的和。
     * 解法：prefix sum。
     */
    public int pivotIndex(int[] A) {
        if (A.length == 1) return 0;
        int sum = 0, preSum = 0;
        for (int i : A) sum += i;
        for (int i = 0; i < A.length; i++) {
            if (i > 0) preSum += A[i - 1];
            if (preSum * 2 == sum - A[i]) return i;
        }
        return -1;
    }
}
