package bitmanipulation;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * 20200228
 */
public class MissingNumber {
    /**
     * 题意：给你一个0~n的数组，其中有个数丢失了。找出那个数。要求O(n)time O(1)space
     * 解法：直接用set，看到O(1)又直觉用swap，但swap真的很多corner case。。
     * 其实这题只要用a little bit math..sum 或者bit manipulation
     */
    public int missingNumber_(int[] nums) { // sum
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        for (int i = 0; i < len; i++)
            sum -= nums[i];
        return sum;
    }

    public int missingNumber__(int[] nums) { // bit manipulation

        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }

    public int missingNumber(int[] A) { // swap
        int n = A.length;
        if (n == 1) return A[0] == 1 ? 0 : 1;
        for (int i = 0; i < n; i++) if (A[i] == n) A[i] = -1;
        for (int i = 0; i < n; i++) {

            while (A[i] < n && A[i] != -1 && A[A[i]] != A[i]) {
                int tmp = A[A[i]];
                A[A[i]] = A[i];
                A[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[i] == -1) return i;
            System.out.print(A[i]);
        }
        return n;
    }
}
