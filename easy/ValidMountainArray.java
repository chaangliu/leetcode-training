package easy;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 */
public class ValidMountainArray {
    /**
     * 题意：判断数组是否是valid mountain array（长度至少3，严格递增递减）。
     * 解法：平扫一遍就行。
     */
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

    public boolean validMountainArray_(int[] A) {
        int flag = -1;
        if (A.length < 3 || A[1] - A[0] <= 0) return false;
        for (int i = 1; i < A.length; i++) {
            int d = A[i] - A[i - 1];
            if (d == 0) return false;
            if (d * flag > 0) {
                flag = 1;
            }
            if (d * flag > 0) return false;
        }
        return flag == 1;
    }
}
