package recursion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.
 *
 * Return how many groups have the largest size.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 4
 * Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
 * [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.
 * Example 2:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are 2 groups [1], [2] of size 1.
 * Example 3:
 *
 * Input: n = 15
 * Output: 6
 * Example 4:
 *
 * Input: n = 24
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * 20200410
 */
public class CountLargestGroup {
    /**
     * 题意：从1到n的每个数，各位数字加起来的和相同的归为一组，问组员最多的那个组有多少个数字。
     * 解法：brute force就行，可以留意Collections的几个API。
     */
    private int digitSum(int n) {
        return n == 0 ? 0 : n % 10 + digitSum(n/10);
    }
    public int countLargestGroup(int n) {
        ArrayList<Integer> cnt = new ArrayList<>(Collections.nCopies(37, 0));
        for (int i = 1; i <=n; i ++) {
            int ds = digitSum(i);
            cnt.set(ds, cnt.get(ds) + 1);
        }
        return Collections.frequency(cnt, Collections.max(cnt));
    }
}
