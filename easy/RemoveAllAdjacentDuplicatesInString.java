package easy;

/**
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * <p>
 * We repeatedly make duplicate removals on S until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 * <p>
 * 20190519 contest 137
 */
public class RemoveAllAdjacentDuplicatesInString {
    /**
     * 这题是easy思路也很简单但是我处理中断的时候却想了好一会儿。。最后用了个标志位。
     */
    String res;
    boolean shouldStop = false;

    public String removeDuplicates(String S) {
        if (S.length() <= 1) return S;
        res = S;
        helper(S);
        return res;
    }

    private void helper(String S) {
        if (shouldStop) return;
        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) == S.charAt(i + 1)) {
                res = S.substring(0, i) + (i + 2 < S.length() ? S.substring(i + 2) : "");
                helper(res);
                break;
            }
        }
        if (res.length() == S.length()) shouldStop = true;
    }

    /**
     * 高效的解法，无需重复从头遍历
     */
    public String removeDuplicates_(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == c) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * two pointers
     */
    public String removeDuplicates__(String S) {
        char[] a = S.toCharArray();
        int end = -1;
        for (char c : a) {
            if (end >= 0 && a[end] == c) {
                --end;
            } else {
                a[++end] = c;
            }
        }
        return String.valueOf(a, 0, end + 1);
    }
}
