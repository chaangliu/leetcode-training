package basics;

/**
 * Edited on 2019/06/17.
 */
public class LCS {
    /**
     * 【最长公共子序列】
     * LCS的转移方程是这样的：
     * dp[i][j] = 0, if(i == 0) or (j == 0)
     * dp[i][j] = dp[i-1][j-1] + 1, if(s[i] == t[j])
     * dp[i][j] = max{dp[i][j-1] , dp[i-1][j] } , if(s[i] != t[j])
     */
    int[][] dp;

    /**
     * 求长度
     */
    public int LCS(String s1, String s2) {
        char[] arr1 = s1.toCharArray(), arr2 = s2.toCharArray();
        int m = arr1.length, n = arr2.length;
        dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }

        return dp[m][n];
    }

    /**
     * 求具体sequence，结合那张回溯的图来看，从后往前推导。
     */
    public String StringOfLCS(String s1, String s2) {
        int i = s1.length(), j = s2.length();
        StringBuilder res = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                res.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] < dp[i][j - 1]) {//哪个能更长就向哪个方向移动
                j--;
            } else {
                i--;
            }
        }
        return res.reverse().toString();
    }


    public static void main(String args[]) {
        LCS lcs = new LCS();
        System.out.println(lcs.LCS("XMJYAUZ", "MZJAWXU"));
        System.out.println(lcs.StringOfLCS("XMJYAUZ", "MZJAWXU"));
    }

    //        void llcs() {
    //            int i, j, z = 0;
    //            char c[ 1001];
    //            memset(c, 0, sizeof(c));
    //            i = len1, j = len2;
    //            while (i != 0 && j != 0) {
    //                if (a[i - 1] == b[j - 1]) {
    //                    c[z++] = a[--i];
    //                    j--;
    //                } else if (dp[i - 1][j] < dp[i][j - 1])
    //                    j--;
    //                else if (dp[i][j - 1] <= dp[i - 1][j])
    //                    i--;
    //            }
    //            for (i = z - 1; i >= 0; i--)
    //                printf("%c", c[i]);
    //            printf("\n");
    //
    //        }
}
