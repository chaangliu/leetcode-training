package dp;

/**
 * A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
 * <p>
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
 * <p>
 * Return the minimum number of flips to make S monotone increasing.
 * Example 1:
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 * <p>
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 * <p>
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 * Note:
 * 1 <= S.length <= 20000
 * S only consists of '0' and '1' characters.
 * 20190809
 */
public class FlipStringToMonotoneIncreasing {
    /**
     * 这题是把数组变成单调增。
     * 解法是前缀和(prefix sum)，也可以说成是dp吧。
     * 从左往右，把string全变成0，需要的步数存放在l里
     * 从右往左，把string全变成1，需要的步数存放在r里
     * 答案就是l[i - 1] + r[i] 的最小值。
     * <p>
     * 这题我还是习惯把下标处理成一一对应的，虽然写起来长一点，但是容易理解。
     * 另外还可以优化成O(1)空间，我没研究了。
     **/
    public int minFlipsMonoIncr(String S) {
        int len = S.length();
        if (len == 1) return 0;
        int[] l = new int[len], r = new int[len];
        l[0] = S.charAt(0) == '0' ? 0 : 1;
        r[len - 1] = S.charAt(len - 1) == '1' ? 0 : 1;
        for (int i = 0; i < len - 1; i++) l[i + 1] = l[i] + (S.charAt(i + 1) == '0' ? 0 : 1);
        for (int i = len - 1; i > 0; i--) r[i - 1] = r[i] + (S.charAt(i - 1) == '1' ? 0 : 1);
        int res = len;
        for (int i = 0; i < len + 1; i++) {
            res = Math.min((i > 0 ? l[i - 1] : 0) + (i < len ? r[i] : 0), res);
        }
        return res;
    }
}
