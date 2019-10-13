package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * <p>
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * <p>
 * You may return the answer in any order.
 * Example 1:
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 * Note:
 * <p>
 * 1 <= N <= 9
 * 0 <= K <= 9
 * 20191012
 */
public class NumbersWithSameConsecutiveDifferences {
    /**
     * 题意：对于N位整数，求相邻digit的绝对差值是K的所有数字（顺序无所谓）。
     * 这题我首先感觉像bfs开锁那题（似乎也是可以的），看到题目TAG是DP，就想到求LCS的具体序列，把每一步的序列都记录下来。
     * 我的解法：
     * dp[i] represents integer of length i such that abs diff between every 2 consecutive digits is K
     * ArrayList<String> dp[] = new ArrayList<String> dp[N + 1];
     * dp[i] = for str in dp[i - 1] { str + str[len - 1]  +/- K }
     * 优化：这里由于只用到了dp[i - 1]所以也是可以空间轮换的。
     */
    public int[] numsSameConsecDiff(int N, int K) {
        ArrayList<ArrayList<String>> dp = new ArrayList<ArrayList<String>>();//这里不能用数组里套ArrayList,Java不支持，会产生Generic Creation error
        ArrayList<String> cell = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {//initialize
            cell.add(i + "");
        }
        dp.add(cell);
        for (int i = 1; i < N; i++) {
            ArrayList<String> tmp = new ArrayList<>();
            dp.add(tmp);
            for (String str : dp.get(i - 1)) {
                int digit = str.charAt(str.length() - 1) - '0';
                String lStr = digit + K <= 9 ? str + (digit + K) : "";//K = 7, 18
                String rStr = digit - K >= 0 ? str + (digit - K) : "";//K = 7, 1-6
                if (lStr.length() != 0 && !lStr.startsWith("0")) {
                    tmp.add(lStr);
                }
                if (!lStr.equals(rStr) && rStr.length() != 0 && !rStr.startsWith("0")) {//漏掉了case: K = 0会出现11,11,22,22..这样的重复
                    tmp.add(rStr);
                }
            }
        }
        int[] res = new int[dp.get(N - 1).size()];
        int i = 0;
        for (String str : dp.get(N - 1)) {
            res[i++] = Integer.parseInt(str);
        }
        return res;
    }

    /**
     * 类似题目：求具体LCS序列
     */
    public String StringOfLCS2(String str1, String str2) {
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], "");//把二维数组的所有cell都初始化成空字符串""
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    /**
     * lee的做法
     */
    public int[] numsSameConsecDiff_(int N, int K) {
        List<Integer> cur = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 2; i <= N; ++i) {
            List<Integer> cur2 = new ArrayList<>();
            for (int x : cur) {
                int y = x % 10;
                if (x > 0 && y + K < 10)
                    cur2.add(x * 10 + y + K);
                if (x > 0 && K > 0 && y - K >= 0)
                    cur2.add(x * 10 + y - K);
            }
            cur = cur2;
        }
        return cur.stream().mapToInt(j -> j).toArray();
    }

    /**
     * BFS
     */
    public int[] numsSameConsecDiff__(int N, int K) {
        Queue<Integer> q = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        if (N == 1) {
            q.offer(0);
        } // in case N is 1.
        while (N-- > 1) {
            for (int sz = q.size(); sz > 0; --sz) {
                int num = q.poll();
                int digit1 = num % 10 - K, digit2 = num % 10 + K;
                if (digit1 >= 0) {
                    q.offer(num * 10 + digit1);
                }
                if (digit2 < 10 && digit1 != digit2) {
                    q.offer(num * 10 + digit2);
                }
            }
        }
        return q.stream().mapToInt(i -> i).toArray();
    }

    /**
     * DFS
     */
    public int[] numsSameConsecDiff___(int N, int K) {
        List<Integer> list = new ArrayList<>();
        if (N == 0)
            return new int[0];
        if (N == 1)
            list.add(0);      // edge case
        dfs(N, K, list, 0);
        return list.stream().mapToInt(i -> i).toArray();   //list.toArray(new int[list.size()]); doesn't work for primitives
    }

    public void dfs(int N, int K, List<Integer> list, int number) {
        if (N == 0) {   // base case, if you have added enough number of integers then append it to list; Here N is used as the total digits in temporary number
            list.add(number);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            if (i == 0 && number == 0)    // Do not add 0 at begining of a number
                continue;
            else if (number == 0 && i != 0) {     // base case, we add all the digits when we do not have any previous digit to check if difference = K
                dfs(N - 1, K, list, i);
            } else {
                if (Math.abs((number % 10) - i) == K) {
                    dfs(N - 1, K, list, number * 10 + i);    // General dfs to add the digit at the units place and reducing the number of digits by 1.
                }
            }
        }
    }
}
