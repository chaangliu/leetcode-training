package array;

import java.util.stream.IntStream;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * Example:
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Created by DrunkPiano on 2017/4/27.
 */

public class PalindromePartitioningii {

    /**
     * 题意：给你一个string，问你最小切几刀能把string分割成全是palindrome substring。
     * 解法1：dp, time, space : O(n^2)
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];//isPalindrome[j][i]表示[j,i]之间是否是palindrome
        int[] minCut = new int[s.length()];//minCut[i]表示[0,i]之间最少切的刀数

        for (int i = 0; i < s.length(); i++) {
            minCut[i] = i;
            for (int j = 0; j <= i; j++) {
                if ((s.charAt(i) == s.charAt(j) && (i - j <= 1 || isPalindrome[j + 1][i - 1]))) {
                    isPalindrome[j][i] = true;//[j,i]是palindrome
                    if (j > 0) {//aba cc
                        //    ji
                        minCut[i] = Math.min(minCut[i], minCut[j - 1] + 1);
                    } else {
                        //j == 0的时候不需要切，整个都是palindrome
                        minCut[i] = 0;
                    }
                }
            }
        }
        return minCut[s.length() - 1];
    }

    /**
     * 解法2，O(n)Space DP
     * 两层循环，
     * 第一层是枚举palindrome的中心
     * 第二层是枚举palindrome的半径(不满足就停止)
     * 判断的逻辑跟解法1一样。
     */
    public int minCut_(String s) {
        if (s == null || s.length() <= 1) return 0;
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid < N; mid++) { // iterate through all chars as mid point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                dp[end] = start == 0 ? 0 : Math.min(dp[end], dp[start - 1] + 1);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                dp[end] = start == 0 ? 0 : Math.min(dp[end], dp[start - 1] + 1);
            }
        }
        return dp[N - 1];
    }

    public static void main(String args[]) {
        System.out.println(new PalindromePartitioningii().minCut("aab"));
    }
}
