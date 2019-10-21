package slidingwindow;

/**
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 * <p>
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 * <p>
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 * <p>
 * Return 0 if the string is already balanced.
 * Example 1:
 * <p>
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 * <p>
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 * <p>
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 * Example 4:
 * <p>
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 * 20191021
 * <p>
 * similar questions
 * 1234. Replace the Substring for Balanced String
 * 992. Subarrays with K Different Integers
 * 904. Fruit Into Baskets
 */
public class ReplacetheSubstringforBalancedString {
    /**
     * 题意：一个字符串里包含四种字符QWER, 求最少修改多少个字符可以把这个字符串中每个字符出现的次数都变成相等。字符串的长度是4的倍数。
     * 这题是周赛第三题，我拿到之后感觉像DP，想了一会儿又觉得像滑动窗口，但是不知道怎么滑动，遂gg。
     * 看了题解，大部分人用了sliding window，小部分人用了binary search。至于DP印象里一般是求「最大xxx」的。
     * sliding window很多人都说不知道怎么滑，这题思路确实比较巧妙，有一定的思维难度；
     * 首先统计每个字母出现次数，然后寻找能让[窗口外]的每个字母出现次数都<=len/4的最小窗口长度。这样的话我们总可以通过改变窗口中的字母去弥补窗口外缺少的部分。QQW['E']EERR
     **/
    public int balancedString(String s) {
        int[] count = new int[128];
        int n = s.length(), res = n, l = 0;
        for (char c : s.toCharArray()) count[c]++;
        if (count['Q'] * 4 == n && count['W'] * 4 == n && count['E'] * 4 == n && count['R'] * 4 == n) return 0;
        for (int r = 0; r < n; r++) {//expand
            count[s.charAt(r)]--;
            while (l <= r && count['Q'] * 4 <= n && count['W'] * 4 <= n && count['E'] * 4 <= n && count['R'] * 4 <= n) {
                res = Math.min(res, r - l + 1);
                count[s.charAt(l)]++;
                l++;
            }
        }
        return res;
    }

    /**
     * lee的答案，巧妙地规避了一开始就balance的判断
     */
    public int balancedString__(String s) {
        int[] count = new int[128];
        int n = s.length(), res = n, i = 0;
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j)];
        }
        for (int j = 0; j < n; ++j) {
            --count[s.charAt(j)];
            while (i < n && count['Q'] <= n / 4 && count['W'] <= n / 4 && count['E'] <= n / 4 && count['R'] <= n / 4) {
                res = Math.min(res, j - i + 1);
                ++count[s.charAt(i++)];
            }
        }
        return res;
    }
}
