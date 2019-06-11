package greedy;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example 1:
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 * Input: "cbacdcbc"
 * Output: "acdb"
 * 20190611
 */
public class RemoveDuplicateLetters {
    /**
     * 这题跟1081. Smallest Subsequence of Distinct Characters一模一样，一行都不用改
     * 思路就是greedy，只要没遇到过就先添加进来，并检查前面有没有比它大的，如果有，并且后面还有这个数，就出栈，再入栈。所以也可以用Stack做。讨论区里还有用递归做的。
     */
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        boolean used[] = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        for (char c : s.toCharArray()) {
            cnt[c - 'a']--;//已犯错误：漏了。没把前面遍历过的去掉。
            if (used[c - 'a']) continue;
            while (sb.length() != 0 && sb.charAt(sb.length() - 1) > c && cnt[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                used[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            used[c - 'a'] = true;
        }
        return sb.toString();
    }
}
