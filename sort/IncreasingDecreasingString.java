package sort;

/**
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 *
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 *
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * 示例 1：
 *
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 */
public class IncreasingDecreasingString {
    /**
     * 题意：从左往右输出最小字符，然后从右往左输出最小字符，直到结束。
     * 解法：模拟。从左往右扫，从右往左扫，如此往复。一个技巧：直接判断ret的长度和s的长度是否相等就知道桶空了没有。
     */
    public String sortString(String s) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean added = false;
            for (int i = 0; i < 26; i++) {
                if (map[i] != 0) {
                    added = true;
                    sb.append((char) (i + 'a'));
                    map[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (map[i] != 0) {
                    added = true;
                    sb.append((char) (i + 'a'));
                    map[i]--;
                }
            }
            if (!added) break;
        }
        return sb.toString();
    }
}
