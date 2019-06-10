package greedy;

/**
 * Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.
 * Example 1:
 * Input: "cdadabcc"
 * Output: "adbc"
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: "abcd"
 * Example 3:
 * <p>
 * Input: "ecbacba"
 * Output: "eacb"
 * Example 4:
 * <p>
 * Input: "leetcode"
 * Output: "letcod"
 * Note:
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters.
 * <p>
 * 20190610
 */
public class SmallestSubsequenceOfDistinctCharacters {
    /**
     * 昨天contest的第四题，没做出来，感觉像是dp；
     * 其实不用dp，如果用的话更想不出了。。。这题最好解法是greedy思想，据说跟316题一样（幸好316没做过，明天做一下）
     */
    public String smallestSubsequence(String text) {
        int[] cnt = new int[26];
        boolean[] used = new boolean[26];
        StringBuilder res = new StringBuilder();
        for (char c : text.toCharArray()) cnt[c - 'a']++;
        for (char c : text.toCharArray()) {
            cnt[c - 'a']--;//更新后面还有多少个c字符
            if (used[c - 'a']) continue;
            //当最后一个c大于当前c，并且后面还有这最后一个c出现，那么就先把这个数删掉，反正后面还有(greedy思想)
            while (res.length() != 0 && res.charAt(res.length() - 1) > c && cnt[res.charAt(res.length() - 1) - 'a'] > 0) {
                used[res.charAt(res.length() - 1) - 'a'] = false;//不要忘了把使用过最后一个数的记录去掉
                res.deleteCharAt(res.length() - 1);
            }
            res.append(c);
            used[c - 'a'] = true;
        }
        return res.toString();
    }
}
