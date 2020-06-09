package jianzhioffer;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Created by DrunkPiano on 2017/4/9.
 */

public class DecodeWays {

    /**
     * 题意：给你一个数，1~26分别映射A-Z，问一一共有多少种解码方式。
     * 解法：dfs with memo/ dp，类似
     * 62. Unique Paths
     * 70. Climbing Stairs
     * 509. Fibonacci Number
     * <p>
     * DFS with memo:
     */
    public int numDecodings(String s) {
        return dfs(s, new Integer[s.length()], 0);
    }

    /**
     * dfs返回从i开始的string有多少种decode方式。
     */
    private int dfs(String s, Integer[] memo, int i) {
        if (i >= s.length()) return 1; //注意这儿对于越界，要返回1而非0；考虑dfs(s,memo,i+2),i+2==length的时候，应该有一种
        if (s.charAt(i) == '0') return 0;
        if (i == s.length() - 1) return 1;
        if (memo[i] != null) return memo[i];
        if (i + 2 <= s.length()) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            memo[i] = (num <= 26 ? dfs(s, memo, i + 2) : 0) + dfs(s, memo, i + 1);//226可以从2 26和22 6得出，227只能从22 7得出，也就是i+2不一定成立，i+1一定成立
            return memo[i];
        }
        return 0;
    }

    /**
     * DP：
     * 20190412 review
     * 两年后这题的思路跟两年前一样，做了20分钟发现corner case不停出现于是放弃
     * <p>
     * 看了下高票答案，从后往前，用了类似counting stairs的思想
     * For the case "226" (dp[i]), you can split it into "22 6"(dp[i-1]) and "2 26"(dp[i-2]).
     * f(i)=f(i+1)+f(i+2)[i−1≥0,10≤x≤25]
     */
    public int numDecodings__(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];//like counting stairs；226可以从i + 1和i + 2的位置跳过来(2 26和22 6)，227就只能从i+1的位置跳过来
        }
        return memo[0];
    }

    /**
     * 转移方程也可以这么考虑：f(i)=f(i−1)+f(i−2)[i−1≥0,10≤x≤25]
     * 滚动数组
     */
    public int translateNum_O1SPACE(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

    /**
     * 剑指offer，面试题46. 把数字翻译成字符串
     * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
     * 20200609 重写了一遍，还写了挺久的，主要是dfs里我一开始用了for循环，寻思着跟permutation那种一样的，但其实不用，只要从前往后搞一次就行了
     * 另外一点，需要memo吗？需要的。比如我打印了一下，1238214143，会有很多重复计算。有点像count stairs
     */
    public int translateNum(int num) {
        char[] A = (num + "").toCharArray();
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i] - '0';
        }
        return dfs(B, 0, new Integer[A.length]);
    }

    /**
     * return 从 A[start]开始一共有多少种翻译方法
     * A[i] = A[i + 1] + s.substr(i,i+1) <= 25 ? A[i + 2] : 0
     **/
    private int dfs(int[] A, int start, Integer[] memo) {
        if (start >= A.length) return 0;
        if (start == A.length - 1) return 1;
        if (start == A.length - 2) return A[start] != 0 && A[start] * 10 + A[start + 1] <= 25 ? 2 : 1;
        if (memo[start] != null) {
            System.out.println(start + " has been calculated");
            return memo[start];
        }
        int res = 0;
        // for (int i = start; i < A.length; i++) { // 不需要for的
        res += dfs(A, start + 1, memo);
        if (start + 1 < A.length && A[start] != 0 && A[start] * 10 + A[start + 1] <= 25) {
            res += dfs(A, start + 2, memo);
        }
        // }
        return memo[start] = res;
    }
}