package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 * Example 1:
 * Input: n = 234
 * Output: 15
 * Explanation:
 * Product of digits = 2 * 3 * 4 = 24
 * Sum of digits = 2 + 3 + 4 = 9
 * Result = 24 - 9 = 15
 * Example 2:
 * <p>
 * Input: n = 4421
 * Output: 21
 * Explanation:
 * Product of digits = 4 * 4 * 2 * 1 = 32
 * Sum of digits = 4 + 4 + 2 + 1 = 11
 * Result = 32 - 11 = 21
 * Constraints:
 * 1 <= n <= 10^5
 * 20191208
 */
public class SubtracttheProductandSumofDigitsofanInteger {
    /**
     * 题意：每一位的积和之差。
     * contest签到题。
     */
    public int subtractProductAndSum(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        int mul = 1, sum = 0;
        for (int num : list) {
            mul *= num;
            sum += num;
        }
        return mul - sum;
    }

    /**
     * O(1) space
     */
    public int subtractProductAndSum_(int n) {
        int sum = 0, product = 1;
        while (n > 0) {
            sum += n % 10;
            product *= n % 10;
            n /= 10;
        }
        return product - sum;
    }

}
