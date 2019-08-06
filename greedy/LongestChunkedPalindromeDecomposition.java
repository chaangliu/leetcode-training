package greedy;

/**
 * Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:
 * <p>
 * Each a_i is a non-empty string;
 * Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 * Example 1:
 * <p>
 * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
 * Output: 7
 * Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 * Example 2:
 * <p>
 * Input: text = "merchant"
 * Output: 1
 * Explanation: We can split the string on "(merchant)".
 * Example 3:
 * <p>
 * Input: text = "antaprezatepzapreanta"
 * Output: 11
 * Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
 * Example 4:
 * <p>
 * Input: text = "aaa"
 * Output: 3
 * Explanation: We can split the string on "(a)(a)(a)".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * text consists only of lowercase English characters.
 * 1 <= text.length <= 1000
 * 20190806
 */
public class LongestChunkedPalindromeDecomposition {
    /**
     * 找一个字符串中最多有多少个chunk是palindrome的。这题可以用greedy，但是需要证明。
     * Prove of the greedy: "Assumption, longer chunk with same start and same end can create larger amount of chunks." Based on the assumption, it wil always has the pattern (🧒 👤🧒.........🧒👤 🧒), which means it can be further decomposed into 3 smaller chunk, which contract with the assumption that longer chunk with same start/same end can create larger amount of chunks.
     * [greedy][recursive]
     */
    public int longestDecomposition(String text) {
        int n = text.length();
        for (int i = 1; i <= n / 2; i++) {
            String l = text.substring(0, i);
            String r = text.substring(n - i);
            if (l.equals(r)) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return n == 0 ? 0 : 1;//这里只有text无法再进入递归的时候会走到
    }

    /**
     * [greedy][iterative]
     */
    public int longestDecomposition_(String S) {
        int res = 0, n = S.length();
        String l = "", r = "";
        for (int i = 0; i < n; ++i) {
            l = l + S.charAt(i);
            r = S.charAt(n - i - 1) + r;
            if (l.equals(r)) {
                ++res;
                l = "";
                r = "";
            }
        }
        return res;
    }

    /**
     * DP做法
     */
    public int longestDecomposition__(String text) {
        int N = text.length();
        // instantiate dp array 1 longer than half the text, since we need dp[0] = 0 for base case, every i is stored with "+1" offset
        int[] dp = new int[N / 2 + 1];
        int max = 0;
        for (int j = 0; j < N / 2; j++) {
            for (int i = 0; i <= j; i++) {
                // s[i:j] (inclusive both ends) has a matching palindrome
                // and we need to make sure that i covers the entire string or it has matching pairs to its left
                if (text.substring(i, j + 1).equals(text.substring(N - 1 - j, N - i)) && (i == 0 || dp[i] != 0)) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[i] + 2);
                    max = Math.max(max, dp[i] + 2);
                }
            }
        }
        // I feel like this part is not as straight-forward and took me a while
        // if string is even length and has dp[n/2] != 0 then we just want to return that, otherwise, we need to add 1 to the max(dp) (odd length string always has one unmatched segment in the middle)
        return ((N % 2 == 0) && dp[N / 2] != 0) ? dp[N / 2] : max + 1;
    }
}
