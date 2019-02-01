package dfs;

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
     * ===============================================================================================
     * recursion
     * ===============================================================================================
     */
    int index = 0;

    public String decodeString(String s) {
        return decodeArray(s.toCharArray());
    }

    private String decodeArray(char[] array) {
        StringBuilder tmp = new StringBuilder();//这个不能定义成一个全局变量
        while (index < array.length && array[index] != ']') {//遇到]就退出
            if (!Character.isDigit(array[index])) {
                //字母
                tmp.append(array[index++]);
            } else {
                int n = 0;
                while (array[index] >= '0' && array[index] <= '9')
                    n = 10 * n + array[index++] - '0';

                index++;// 跳过[
                String sub = decodeArray(array);
                index++;// 跳过]，递归出来index是在字母的下一位，也就是]的位置
                while (n-- > 0) {
                    tmp.append(sub);
                }
            }
        }
        return tmp.toString();
    }


    /**
     * ===============================================================================================
     * stack
     * ===============================================================================================
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
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String args[]) {

        System.out.println(new DecodeString().decodeString___Stack("3[a2[c]]"));
    }
}
