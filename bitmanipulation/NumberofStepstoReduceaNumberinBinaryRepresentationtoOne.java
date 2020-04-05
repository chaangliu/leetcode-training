package bitmanipulation;

/**
 * Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It's guaranteed that you can always reach to one for all testcases.
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 * 20200405
 */
public class NumberofStepstoReduceaNumberinBinaryRepresentationtoOne {
    /**
     * 题意：奇数+1，偶数/2；判断需要几步才能把一个二进制string变成1.
     * 我的解法：模拟
     */
    public int numSteps(String s) {
        StringBuilder sb = new StringBuilder(s);
        int res = 0;
        while (true) {
            int ones = 0;
            for (int i = 0; i < sb.length(); i++) if (sb.charAt(i) == '1') ones++;
            if (ones == 1 && sb.charAt(sb.length() - 1) == '1')
                return res;
            if (sb.charAt(sb.length() - 1) == '0') {
                sb.deleteCharAt(sb.length() - 1); // 偶数，右移
            } else {
                boolean changed = false;
                for (int i = sb.length() - 1; i >= 0; i--) {
                    if (sb.charAt(i) == '1') {
                        sb.replace(i, i + 1, "0");
                    } else {
                        sb.replace(i, i + 1, "1");
                        changed = true;
                        break;
                    }
                }
                if (!changed)
                    sb.insert(0, '1');
            }
            res++;
        }
    }

    /**
     * most voted solution from discussion
     */
    public int numSteps_(String s) {
        int res = 0, carry = 0;
        for (int i = s.length() - 1; i > 0; --i)
            // 0 + 1 = 1 odd, need  two steps (add 1 and divided by 2), (carry = 1)
            // 1 + 0 = 1 odd, need  two steps (add 1 and divided by 2), (carry = 0)
            if (s.charAt(i) - '0' + carry == 1) {
                carry = 1;
                res += 2;
            } else
                // 0 + 0 = 0 even, need one step(divided by 2), (carry = 0)
                // 1 + 1 = 0 even, need one step(divided by 2), (carry = 1 ,  carry keeps 1 in next round.)
                res += 1;
        }
        return res + carry;
    }
}
