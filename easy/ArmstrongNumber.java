package easy;

/**
 * * The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.
 * <p>
 * Given a positive integer N, return true if and only if it is an Armstrong number.
 * Example 1:
 * <p>
 * Input: 153
 * Output: true
 * Explanation:
 * 153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
 * Example 2:
 * <p>
 * Input: 123
 * Output: false
 * Explanation:
 * 123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 = 36.
 * Note:
 * 1 <= N <= 10^8
 * <p>
 * 20190728
 */
public class ArmstrongNumber {
    public boolean isArmstrong(int N) {
        String s = N + "";
        int sum = 0;
        for (char c : s.toCharArray()) {
            int n = c - '0';
            sum += Math.pow(n, s.length());
            if (sum < 0) return false;
        }
        return sum == N;
    }
}
