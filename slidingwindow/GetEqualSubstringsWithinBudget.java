package slidingwindow;

/**
 * You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 * <p>
 * You are also given an integer maxCost.
 * <p>
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.
 * <p>
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcd", t = "bcdf", cost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 * Example 2:
 * <p>
 * Input: s = "abcd", t = "cdef", cost = 3
 * Output: 1
 * Explanation: Each charactor in s costs 2 to change to charactor in t, so the maximum length is 1.
 * Example 3:
 * <p>
 * Input: s = "abcd", t = "acde", cost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s and t only contain lower case English letters.
 * 20190929
 */
public class GetEqualSubstringsWithinBudget {
    /**
     * 这题是想把s,t两个string里面的尽量长的substring转换为相同，转换限制是ascii相差最大为maxCost。
     * 首先从数据规模看出肯定是O(n)解法。另外，这题是连续的substring，那么不能用最小堆去贪心分配。
     * 我想到能不能利用前缀和，不过发现前缀和貌似只能O(n^2)。
     * 然后就想到sliding window，思路很简单但是写起来有一定技巧，right一直expand，left用while来shrink兜底。模板可以参考 Find All Anagrams in a String那题讨论区，https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int l = 0, r = 0, cost = 0, res = 0;
        while (r < len) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));
            if (cost <= maxCost)
                res = Math.max(res, r - l + 1);
            while (cost > maxCost) {//left用while来shrink
                cost -= Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
            r++;//right不停地expand
        }
        return res;
    }
}
