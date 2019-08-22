package stack;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * 20180822
 */
public class RemoveKDigits {
    /**
     * 这种题要找规律
     * 从高位开始，贪心地把较小的数留下。什么样的数是较小的？当前数和后面的数对比，如果比后面的大，优先把当前数删掉，留下的就是较小的。
     * <p>
     * 问题是，做的时候发现一个case，1234567890，k = 9，才知道这题需要一个技巧，类似stack，在找到0的时候stack.peek()对比k次
     **/
    public String removeKdigits_(String num, int k) {
        int len = num.length();
        if (k == len) return "0";
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        // corner case like "1111"
        while (k > 0) {
            stack.pop();
            k--;
        }
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();
        //remove all the 0 at the head
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();
    }

    //    public String removeKdigits__WA(String num, int k) {
    //        if (k >= nums.length()) return "0";
    //        StringBuilder sb = new StringBuilder();
    //        int i = 0;
    //        for (; i < nums.length() - 1 && k > 0; ++i) {
    //            if (nums.charAt(i) > nums.charAt(i + 1)) {
    //                k--;
    //            } else {
    //                sb.append(nums.charAt(i));
    //            }
    //        }
    //        StringBuilder res = new StringBuilder(sb.toString() + nums.substring(i));//我发现，for循环执行完了之后即使中间的条件不满足了，++i仍然会执行一次；例如for (i = 0; k > 0; i++)执行到k == 0的时候，i仍然会+1才退出
    //        while (res.length() > 0 && res.charAt(0) == '0') res.deleteCharAt(0);
    //        if (k > 0)
    //            res = new StringBuilder(res.substring(0, res.length() - k));
    //        return res.length() == 0 ? "0" : res.toString();
    //    }
}
