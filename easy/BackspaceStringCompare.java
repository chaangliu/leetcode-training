package easy;

import java.util.Stack;

/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 */
public class BackspaceStringCompare {
    /**
     * 题意：给你S,T两个string，其中字母代表输入，#代表删除键，问最终的字符串是否相等。
     * 解法：普通解法：stack模拟
     * 时间空间: O(N+M)
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack(), t = new Stack();
        for (char c : S.toCharArray()) {
            if (c == '#') {
                if (!s.empty()) s.pop();
            } else {
                s.push(c);
            }
        }
        for (char c : T.toCharArray()) {
            if (c == '#') {
                if (!t.empty()) t.pop();
            } else {
                t.push(c);
            }
        }
        while (!s.empty() && !t.empty()) {
            if (s.pop() != t.pop()) return false;
        }
        return s.empty() && t.empty();
    }

    /**
     * one pass解法，two pointers 从后往前遍历
     * 遇到#就记录skip+1，遇到字母就判断是否需要skip
     * 时间O(N+M)
     * 空间O(1)
     */
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            int i = S.length() - 1, j = T.length() - 1;
            int skipS = 0, skipT = 0;

            while (i >= 0 || j >= 0) {
                while (i >= 0) {
                    if (S.charAt(i) == '#') {
                        skipS++;
                        i--;
                    } else if (skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        break; // 遇到不能skip的字母就跳出
                    }
                }
                while (j >= 0) {
                    if (T.charAt(j) == '#') {
                        skipT++;
                        j--;
                    } else if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break; // 遇到不能skip的字母就跳出
                    }
                }
                if (i >= 0 && j >= 0) { // 比较不能skip的字母
                    if (S.charAt(i) != T.charAt(j)) {
                        return false;
                    }
                } else {
                    if (i >= 0 || j >= 0) {
                        return false;
                    }
                }
                i--;
                j--;
            }
            return true;
        }
    }
}
