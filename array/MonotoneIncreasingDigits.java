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
     * 题意：给你一个数字，让你求第一个<=这个数字的、并且满足各位非递减的数字。
     * 解法：首先这题如果从后往前一个个判断是不是满足，是TLE的。需要找规律。
     * 我想了一下，从前往后遍历，判断当前数字是否比后面的数字大，如果比后边的大，那这个数字就必须缩小，后面的全变成9。但遇到的问题是，399443这种case，还需要继续处理前面的数字。
     * 一个正确做法是从后往前遍历：
     * 从后往前维护最早出现cliff的地方，比如1324，会发现3->2出现了cliff，那么把3减去1，然后把2开始到结尾都改成9.
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chars = Integer.toString(N).toCharArray();
        int marker = chars.length;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i - 1] - '0' > chars[i] - '0') { // 399443 => 388443 => 389999
                marker = i;
                chars[i - 1] -= 1;
            }
        }
        for (int i = marker; i < chars.length; i++) chars[i] = '9';
        return Integer.valueOf(new String(chars));
    }
}
