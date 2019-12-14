package array;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 * <p>
 * Input: "aba"
 * Output: False
 * Example 3:
 * <p>
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * 20191213
 */
public class RepeatedSubstringPattern {
    /**
     * 题意：从一个字符串s里找一个substring，这个substring不断重复能够构成s。
     * 这题是easy题，于是我拿到直接就brute force了，就是枚举length，然后用StringBuilder不断append，尝试构造s。这么做的复杂度严格来说是O(n^3)，因为equals()也是有O(n)的对比过程，于是超时了。
     * 然后我尝试剪枝，想了一个办法，首先，如果cell长度不能被s长度整除，continue; 其次，我们可以对每个substring长度的字符串和cell比较，一旦不相等就break。
     * 做了这两个剪枝之后，速度超过了98%的人(看答案发现只要做第一个剪枝就能AC了，第二个就更快了)。
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = n / 2; i >= 1; i--) {
            if (n % i != 0) continue;
            String cell = s.substring(0, i);
            int j = i;
            while (j + i <= s.length()) {
                String tmp = s.substring(j, j + i);
                if (!tmp.equals(cell)) break;
                if (j + i == s.length()) return true;
                j += i;
            }
        }
        return false;
    }

    /**
     * 讨论区答案
     */
    public boolean repeatedSubstringPattern__(String str) {
        int len = str.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int m = len / i;
                String subS = str.substring(0, i);
                int j;
                for (j = 1; j < m; j++) {
                    if (!subS.equals(str.substring(j * i, i + j * i))) break;
                }
                if (j == m)
                    return true;
            }
        }
        return false;
    }
}
