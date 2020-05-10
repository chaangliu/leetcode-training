package bitmanipulation;

/**
 * 给你一个整数数组 arr 。
 * <p>
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * <p>
 * a 和 b 定义如下：
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 示例 1：
 * <p>
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * <p>
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * <p>
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * <p>
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * 提示：
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 * 20200510
 */
public class CountTripletsThatCanFormTwoArraysofEqualXOR {
    /**
     * 题意：找出i,j,k的三元组，使得[i,j) == [j,k]范围内的异或
     * 解法：我看到这个立刻想到了prefix，之前做过的，但是写的时候发现即便用prefix也需要O(n ^ 3)的。这题数据量很小
     * O(n^3)解法：
     */
    public int countTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int a = 0;
            for (int j = i; j < arr.length; j++) {
                a ^= arr[j];
                int b = 0;
                for (int k = j + 1; k < arr.length; k++) {
                    b ^= arr[k];
                    if (a == b)
                        count++;
                }
            }
        }
        return count;
    }

    /**
     * O(n^2)解法
     * 这种解法需要记住异或的两个性质：
     * 如果a == b，那么a ^ b == 0。反之也成立。
     * 异或具有结合率。(a1 ^ a2) ^ a3 =a1 ^ (a2 ^ a3)
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/solution/5405java-liang-chong-jie-fa-xiang-jie-by-ustcyyw/
     */
    public int countTriplets__(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                temp ^= arr[k];
                if (temp == 0)
                    count += k - i;
            }
        }
        return count;
    }

    /**
     * 多此一举的prefix解法。。
     */
    public int countTriplets_(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    if ((prefix[j - 1] ^ (i == 0 ? 0 : prefix[i - 1])) == (prefix[k] ^ prefix[j - 1])) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
