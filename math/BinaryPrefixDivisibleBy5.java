package math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)
 * <p>
 * Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.
 * <p>
 * Example 1:
 * <p>
 * Input: [0,1,1]
 * Output: [true,false,false]
 * Explanation:
 * The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.
 * Example 2:
 * <p>
 * Input: [1,1,1]
 * Output: [false,false,false]
 * Example 3:
 * <p>
 * Input: [0,1,1,1,1,1]
 * Output: [true,false,false,false,true,false]
 * Example 4:
 * <p>
 * Input: [1,1,1,0,1]
 * Output: [false,false,false,false,false]
 * <p>
 * 20190331
 */
public class BinaryPrefixDivisibleBy5 {
    /**
     * 这题因为A的长度是30000，所以我一开始从int改成long，也没用的还是会超；
     * 后来经过点拨 发现对于判断取余的题其实只要保留最后一位，或者​多保留几位也行，不超过int就行
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            //% 10或者% 1000等等都行，就代表保留几位数
            sum = (sum * 2 + A[i]) % 10;
            res.add(sum % 5 == 0);
        }
        return res;
    }
}
