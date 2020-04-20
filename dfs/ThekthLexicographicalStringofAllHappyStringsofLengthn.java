package dfs;

/**
 * A happy string is a string that:
 * <p>
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 * Example 1:
 * <p>
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * Example 2:
 * <p>
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 * <p>
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 * Example 4:
 * <p>
 * Input: n = 2, k = 7
 * Output: ""
 * Example 5:
 * <p>
 * Input: n = 10, k = 100
 * Output: "abacbabacb"
 * Constraints:
 * 1 <= n <= 10
 * 1 <= k <= 100
 */
public class ThekthLexicographicalStringofAllHappyStringsofLengthn {
    /**
     * 题意：定义happy string是指由abc组成的，且相邻两个字母不相同的string；求长度为n的字典序的第k个happy string。
     * 解法：注意由于只有abc组成，所以这样的happy string即便是n=10数据规模也不大。直接暴力permutation就行了；我一开始还用了最大堆，其实不用，permutation就是按顺序输出的。
     */
    public String getHappyString(int n, int k) {
        dfs("", n, k);
        return result;
    }

    String result = "";
    int cnt = 0;

    private void dfs(String cur, int n, int k) {
        if (cur.length() == n) {
            if (++cnt == k) {
                result = cur;
                return;
            }
            return;
        }
        char[] list = {'a', 'b', 'c'};
        for (char c : list) {
            if (cur.length() > 0 && c == cur.charAt(cur.length() - 1)) continue;
            dfs(cur + c, n, k);
        }
    }
}
