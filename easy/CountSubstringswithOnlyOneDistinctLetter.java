package easy;

/**
 * Given a string S, return the number of substrings that have only one distinct letter.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: S = "aaaba"
 * Output: 8
 * Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
 * "aaa" occurs 1 time.
 * "aa" occurs 2 times.
 * "a" occurs 4 times.
 * "b" occurs 1 time.
 * So the answer is 1 + 2 + 4 + 1 = 8.
 * Example 2:
 * <p>
 * Input: S = "aaaaaaaaaa"
 * Output: 55
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= S.length <= 1000
 * S[i] consists of only lowercase English letters.
 * 20190908
 */
public class CountSubstringswithOnlyOneDistinctLetter {
    class Solution {
        /**
         * biweekly contest 08的第一题，白给
         * 找规律发现值在于计算连续相同子串的长度，用等差数列公式计算1到n的和
         **/
        public int countLetters(String S) {
            int res = 0;
            int cnt = 1;
            for (int i = 1; i < S.length(); i++) {
                if (S.charAt(i) == S.charAt(i - 1)) {
                    cnt++;
                } else {
                    int cell = (1 + cnt) * cnt / 2;
                    res += cell;
                    cnt = 1;
                }
            }
            return res + (1 + cnt) * cnt / 2;
        }
    }
}
