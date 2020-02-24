package math;

/**
 * Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.
 * Return the two integers in any order.
 * Example 1:
 * Input: num = 8
 * Output: [3,3]
 * Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 = 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
 * Example 2:
 * Input: num = 123
 * Output: [5,25]
 * Example 3:
 * Input: num = 999
 * Output: [40,25]
 * Constraints:
 * 1 <= num <= 10^9
 * 20200224
 */
public class ClosestDivisors {
    /**
     * 题意：求num+1或者num+2的两个最相近的因子。
     * 解法：brute force。
     */
    public int[] closestDivisors(int num) {
        int a = (int) Math.ceil(Math.sqrt(num + 2)), b;
        for (; a > 0; a--) {
            if ((num + 1) % a == 0) {
                b = (num + 1) / a;
                return new int[]{a, b};
            }
            if ((num + 2) % a == 0) {
                b = (num + 2) / a;
                return new int[]{a, b};
            }
        }
        return null;
    }
}
