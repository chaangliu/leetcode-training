package bitmanipulation;

/**
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * Example 1:
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 * Constraints:
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 * 20200112
 */
public class MinimumFlipstoMakeaORbEqualtoc {
    /**
     * 题意：求最少翻转a，b中的几bit才能让a | b == c。
     * 解法非常intuitive，就是遍历。contest的时候我直接转换成string做了，本能地回避bit manipulation..这样不好。
     * ROCK的答案：
     */
    public int minFlips(int a, int b, int c) {
        int ans = 0, ab = a | b, equal = ab ^ c;
        for (int i = 0; i < 31; ++i) {
            int mask = 1 << i;
            if ((equal & mask) > 0) {
                ans += (ab & mask) < (c & mask) || (a & mask) != (b & mask) ? 1 : 2;
                ab ^= mask;
            }
        }
        return ans;
    }

    /**
     * 另一个答案：
     */
    public int minFlips__(int a, int b, int c) {
        int count = 0;
        for (int i = 1; i <= 32; i++) {
            int b1 = 0, b2 = 0, b3 = 0;
            if (((a >> (i - 1)) & 1) >= 1) b1 = 1; // check the ith bit of a
            if (((b >> (i - 1)) & 1) >= 1) b2 = 1; // check the ith bit of b
            if (((c >> (i - 1)) & 1) >= 1) b3 = 1; // check the ith bit of c
            if (b3 == 0 && (b1 == 1 || b2 == 1)) count += b1 + b2; // if the ith bit of c is 0 and any of the ith bits of a or b is 1
            else if (b3 == 1 && b1 == 0 && b2 == 0) count++; // if the ith bit of c is 1, check the ith bits of a and b
        }
        return count;
    }

    /**
     * 我的偷懒做法, Integer.toBinaryString()
     */
    public int minFlips_(int a, int b, int c) {
        int res = 0;
        String sa = Integer.toBinaryString(a);
        String sb = Integer.toBinaryString(b);
        String sc = Integer.toBinaryString(c);
        int maxLength = Math.max(sa.length(), Math.max(sb.length(), sc.length()));
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < maxLength - sa.length(); i++) {
            prefix.append('0');
        }
        sa = prefix.toString() + sa;
        prefix = new StringBuilder();
        for (int i = 0; i < maxLength - sb.length(); i++) {
            prefix.append('0');
        }
        sb = prefix.toString() + sb;
        prefix = new StringBuilder();
        for (int i = 0; i < maxLength - sc.length(); i++) {
            prefix.append('0');
        }
        sc = prefix.toString() + sc;
        for (int i = 0; i < maxLength; i++) {
            if (sc.charAt(i) == '1') {
                if (sa.charAt(i) == '0' && sb.charAt(i) == '0') res++;
            } else {
                if (sa.charAt(i) == '1') res++;
                if (sb.charAt(i) == '1') res++;
            }
        }
        return res;
    }
}
