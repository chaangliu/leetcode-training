package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, return the number of substrings of length K with no repeated characters.
 * Example 1:
 * Input: S = "havefunonleetcode", K = 5
 * Output: 6
 * Explanation:
 * There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
 * Example 2:
 * <p>
 * Input: S = "home", K = 5
 * Output: 0
 * Explanation:
 * Notice K can be larger than the length of S. In this case is not possible to find any substring.
 * Note:
 * 1 <= S.length <= 10^4
 * All characters of S are lowercase English letters.
 * 1 <= K <= 10^4
 * 20190630
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {
    /**
     * 题目：寻找长度是K的不含重复字母的substring的数量。
     * 这题我一开始用Set，结果WA了好久，而且调试了很久看不出为什么。。。属实弟弟，晚上没状态？
     * S = "havefunonleetcode", K = 5
     * 'havef','avefu','vefun','efuno','etcod','tcode'.
     * 原因就是如果用set的话，对于eetcode这种，window右移时，移出前一个e会导致把当前的e也移调。
     * 所以用Map。
     */
    public int numKLenSubstrNoRepeats(String S, int K) {
        if (K > S.length()) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < K; i++) map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        if (map.size() == K) res = 1;
        for (int l = 1; l <= S.length() - K; l++) {
            if (map.get(S.charAt(l - 1)) == 1) {
                map.remove(S.charAt(l - 1));
            } else {
                map.put(S.charAt(l - 1), map.get(S.charAt(l - 1)) - 1);
            }
            map.put(S.charAt(l + K - 1), map.getOrDefault(S.charAt(l + K - 1), 0) + 1);
            if (map.size() == K) {
                res++;
            }
        }
        return res;
    }
}
