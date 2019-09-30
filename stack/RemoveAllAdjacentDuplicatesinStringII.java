package stack;

import java.util.Stack;

/**
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made.
 * <p>
 * It is guaranteed that the answer is unique.
 * Example 1:
 * <p>
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 * <p>
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 * <p>
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lower case English letters.
 * 20190930
 */
public class RemoveAllAdjacentDuplicatesinStringII {
    /**
     * 这题是周赛第三题，通过率很高。
     * 跟它的系列第一题题意都是在重复的数字达到一定程度k之后就抵消，像俄罗斯方块那样。但这题的k可能会非常大。
     * 我一开始想利用两个stack，第一个是res，第二个是helper，遇到一个新的char之后放入helper，如果跟peek不同或者没有达到k，就继续放，否则就把helper转移到res里去。
     * 这么做的问题是，后续没办法把res里已有的char也放到helper里利用，于是WA了。
     * <p>
     * 就看了hint之后，提示用一个stack，并且用数字保存peek的char的个数，达到之后就pop。于是很快写出来了。
     */
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.empty()) {
                stack.push(new Pair(c, 1));
            } else if (stack.peek().c != c) {
                stack.push(new Pair(c, 1));
            } else if (stack.peek().cnt == k - 1) {
                stack.pop();
            } else {
                stack.peek().cnt++;
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.empty()) {
            Pair p = stack.pop();
            for (int i = 0; i < p.cnt; i++) {
                res.insert(0, p.c);
            }
        }
        return res.toString();
    }

    class Pair {
        char c;
        int cnt;

        Pair(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }
}
