package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s, we make queries on substrings of s.
 * <p>
 * For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right], and then choose up to k of them to replace with any lowercase English letter.
 * <p>
 * If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.
 * <p>
 * Return an array answer[], where answer[i] is the result of the i-th query queries[i].
 * <p>
 * Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)
 * Example :
 * <p>
 * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * Output: [true,false,false,true,true]
 * Explanation:
 * queries[0] : substring = "d", is palidrome.
 * queries[1] : substring = "bc", is not palidrome.
 * queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
 * queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
 * queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
 * Constraints:
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s only contains lowercase English letters.
 * <p>
 * 20190901
 */
public class CanMakePalindromefromSubstring {
    /**
     * 方法1:
     * 首先，通过观察发现，
     * substring的长度无论奇偶，change的次数至少需要k >= 出现奇数次的字母个数/2
     * 验证：
     * "bc", k = 0 < 2/2 = 1  ;false
     * "abcd", k = 1 < 4/2 = 2;false
     * "abcd", k = 2 >= 4/2 = 2;true
     * "d",     k = 0 >= 1/2 = 0 ; true
     * "abcda", k = 1 >= 3 / 2 = 1; true
     * <p>
     * 当然，这种解法不是出题者想要的，毕竟query的长度达到了1e5，肯定是不想对于每个query都O(n)搞一遍；但是如果能想到k>=13一定是true，也勉强让你过了
     **/
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isValid(s, query));
        }
        return res;
    }

    private boolean isValid(String s, int[] query) {
        if (query[2] >= 13) return true;//如果不加这句就会超时
        Set<Character> set = new HashSet<>();
        for (int i = query[0]; i <= query[1]; i++) {
            if (!set.add(s.charAt(i))) set.remove(s.charAt(i));//one pass查找出现次数为奇数的字母的方法
        }
        return set.size() / 2 <= query[2];
    }

    /**
     * 方法2 Prefix Sum，前缀和，或者说是DP吧; clone的手法有点新颖。
     * dp[i][j]表示s.substring(0,i)中字母('a' + j)的个数(后面可以利用这个求区间内某个字母出现的次数，这样做的好处是少用一维)
     * 然后对于每个query，计算26个字母出现的次数，方法是dp[end + 1][i] - dp[start][i]，i从a到z
     * 最后和方法1一样判断是否valid
     */
    public List<Boolean> canMakePaliQueries_(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int[][] cnt = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); ++i) {
            cnt[i + 1] = cnt[i].clone(); // copy previous sum.
            ++cnt[i + 1][s.charAt(i) - 'a'];
        }
        for (int[] q : queries) {
            int sum = 0;
            for (int i = 0; i < 26; ++i) {
                sum += (cnt[q[1] + 1][i] - cnt[q[0]][i]) % 2;
            }
            ans.add(sum / 2 <= q[2]);
        }
        return ans;
    }
}
