package bitmanipulation;

import java.util.HashMap;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * 示例 1：
 * <p>
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 * <p>
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 * <p>
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 * 提示：
 * <p>
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 * 20200416
 */
public class FindtheLongestSubstringContainingVowelsinEvenCounts {
    /**
     * 题意：找出最长的元音字母出现偶数次的子串。
     * 解法：one pass, 异或操作；核心思想是，把aeiou映射到不同的位上，这样如果pattern相同，说明之前也出现过，说明之前到现在为止的子串的pattern是0，所以记录第一次出现的位置就好了.
     * 这种情况用位操作的好处是，模式匹配很容易。如果你用数组或者string来表示模式的话会很难把它用作map的key来计算第一次出现的位置。
     */
    public int findTheLongestSubstring(String s) {
        int res = 0, cur = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            switch (s.charAt(i)) {
                case 'a':
                    cur ^= 1;
                    break;
                case 'e':
                    cur ^= 2;
                    break;
                case 'i':
                    cur ^= 4;
                    break;
                case 'o':
                    cur ^= 8;
                    break;
                case 'u':
                    cur ^= 16;
                    break;
            }
            seen.putIfAbsent(cur, i);
            res = Math.max(res, i - seen.get(cur));
        }
        return res;
    }
}
