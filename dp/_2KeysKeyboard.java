package dp;

/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 * <p>
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * <p>
 * <p>
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * <p>
 * 20190330
 */
public class _2KeysKeyboard {

    /**
     * approach1. 质因数分解(Prime Factorization)
     * 这题通过找规律发现结果正好是质因数分解后所有质数的和，于是在网上找了质因数分解的代码。注意corner case n == 1. 属实有点Math了。
     */
    public int minSteps(int n) {
        if (n == 1) return 0;
        int res = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                res += i;
                n /= i;
                i--;
            }
        }
        return res + n;
    }

    /**
     * approach2. dp（其实也是质因数分解）
     * 我感觉这题能用dp，但是没想出来方程，觉得不能根据当前情况来推算后面的情况
     * <p>
     * Essentially, we find the next smaller length sequence (than the one under consideration) which can be copied and then pasted over multiple times to generate the desired sequence.
     * The moment we find a length that divides our required sequence length perfectly, then we don't need to check for any smaller length sequences.
     */
    public int minSteps__DP(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }

            }
        }
        return dp[n];
    }
}
