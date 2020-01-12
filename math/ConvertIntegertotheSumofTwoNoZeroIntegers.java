package math;

/**
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 * <p>
 * Return a list of two integers [A, B] where:
 * <p>
 * A and B are No-Zero integers.
 * A + B = n
 * It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.
 * Example 1:
 * Input: n = 2
 * Output: [1,1]
 * Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in their decimal representation.
 * Example 2:
 * Input: n = 11
 * Output: [2,9]
 * Example 3:
 * Input: n = 10000
 * Output: [1,9999]
 * Example 4:
 * Input: n = 69
 * Output: [1,68]
 * Example 5:
 * Input: n = 1010
 * Output: [11,999]
 * Constraints:
 * 2 <= n <= 10^4
 * 20200112
 */
public class ConvertIntegertotheSumofTwoNoZeroIntegers {
    /**
     * 题意：把一个数分解成任意两个不含0的数字的和。
     * 解法，从0枚举。我用了string的方法判断数字是否含有0.
     * 好像还有不是暴力的方法。
     */
    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            if (valid(i) && valid(n - i)) return new int[]{i, n - i};
        }
        return res;
    }

    private boolean valid(int a) {
        String s = a + "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') return false;
        }
        return true;
    }
}
