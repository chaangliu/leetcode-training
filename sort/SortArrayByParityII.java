package sort;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class SortArrayByParityII {
    /**
     * 题意：给你一个数组，让你把奇数放到奇数位，偶数放到偶数位。
     * 解法：容易想到swap+双指针。
     */
    public int[] sortArrayByParityII(int[] A) {
        int odd = 1, even = 0;
        for (int i = 0; i < A.length; i++) {
            while (i % 2 == 1 && A[i] % 2 == 0) {
                swap(A, i, even);
                even += 2;
            }
            while (i % 2 == 0 && A[i] % 2 == 1) {
                swap(A, i, odd);
                odd += 2;
            }
        }
        return A;
    }

    /**
     * official写法，只处理奇数位即可，偶数会自动归位
     */
    public int[] sortArrayByParityII_(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
