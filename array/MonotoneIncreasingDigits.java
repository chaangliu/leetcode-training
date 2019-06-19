package array;

/**
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 * <p>
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 * <p>
 * Example 1:
 * Input: N = 10
 * Output: 9
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 * 2019/06/19
 */
public class MonotoneIncreasingDigits {
    /**
     * 这题如果从后往前一个个判断是不是满足，是TLE的。
     * 其中一个正确做法：
     * 从后往前判断哪里出现了cliff，比如1324，会发现3->2出现了cliff，那么把3 - 1，然后把2开始到结尾都改成9.
     * 这种做法要多写几个数字找规律(会发现很多都是这种结尾是9的情况)
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chars = Integer.toString(N).toCharArray();
        int marker = chars.length;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] - '0' < chars[i - 1] - '0') {
                marker = i;
                chars[i - 1] -= 1;
            }
        }
        for (int i = marker; i < chars.length; i++) chars[i] = '9';
        return Integer.valueOf(new String(chars));
    }
}
