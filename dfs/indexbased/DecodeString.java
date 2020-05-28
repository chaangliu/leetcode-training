package dfs.indexbased;

import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Examples:
 * <p>
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * <p>
 * 20190131
 */
public class DecodeString {

    /**
     * 题意：给你一个字符串，包含数字、中括号和字母，让你把它递归地打印出来。
     * 解法：我给这种题取了个名字，index based dfs
     * 20200528review 没做出来，一直WA，具体可看提交记录 https://leetcode-cn.com/submissions/detail/74347746/
     * 原因可能是，这种index要写成全局变量。另外这种类型不常做。
     * 这题用stack也挺好做的。
     * 1. 递归写法
     */
    int index = 0;

    public String decodeString(String s) {
        return dfs2(s.toCharArray());
    }

    private String dfs(char[] array) {
        StringBuilder tmp = new StringBuilder();//这个不能定义成一个全局变量
        // 注意这个while不能变成if, 否则处理完一层之后遇到]就不会再继续递归。
        while (index < array.length && array[index] != ']') {//观察发现，最后一位可能是字母或者]。遇到]就退出; 这个while相当于继续执行dfs，详见dfs2的写法，可能更直观些。
            if (!Character.isDigit(array[index])) { // 一层开始，可能是字母或者数字。如果是字母，就先记录。
                tmp.append(array[index++]);
            } else {
                int n = 0;
                while (array[index] >= '0' && array[index] <= '9')
                    n = 10 * n + array[index++] - '0';

                index++;// 数字后面肯定是[, 跳过[
                String sub = dfs(array); // 进入下一层
                index++;// 跳过]，递归出来index是在字母的下一位，也就是]的位置
                while (n-- > 0) {
                    tmp.append(sub);
                }
            }
        }
        return tmp.toString();
    }

    private String dfs2(char[] array) {
        if (index >= array.length || array[index] == ']') return "";
        StringBuilder tmp = new StringBuilder();//这个不能定义成一个全局变量
        if (!Character.isDigit(array[index])) {
            tmp.append(array[index++]);
        } else {
            int n = 0;
            while (array[index] >= '0' && array[index] <= '9') n = 10 * n + array[index++] - '0';
            index++;// 数字后面肯定是[, 跳过[
            String sub = dfs2(array);
            index++;// 跳过]，递归出来index是在字母的下一位，也就是]的位置
            while (n-- > 0) {
                tmp.append(sub);
            }
        }
        return tmp.toString() + dfs2(array);
    }

    /**
     * 递归写法2, lc官方题解
     */
    class Solution {
        String src;
        int ptr;

        public String decodeString(String s) {
            src = s;
            ptr = 0;
            return dfs();
        }

        public String dfs() {
            if (ptr == src.length() || src.charAt(ptr) == ']') {
                // String -> EPS
                return "";
            }

            char cur = src.charAt(ptr);
            int repTime = 1;
            String ret = "";

            if (Character.isDigit(cur)) {
                // String -> Digits [ String ] String
                // 解析 Digits
                repTime = getDigits();
                // 过滤左括号
                ++ptr;
                // 解析 String
                String str = dfs();
                // 过滤右括号
                ++ptr;
                // 构造字符串
                while (repTime-- > 0) {
                    ret += str;
                }
            } else if (Character.isLetter(cur)) {
                // String -> Char String
                // 解析 Char
                ret = String.valueOf(src.charAt(ptr++));
            }

            return ret + dfs();
        }

        public int getDigits() {
            int ret = 0;
            while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
                ret = ret * 10 + src.charAt(ptr++) - '0';
            }
            return ret;
        }
    }


    /**
     * stack写法，其实这题用stack也很好理解
     */
    public String decodeString___Stack(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
    }

}
