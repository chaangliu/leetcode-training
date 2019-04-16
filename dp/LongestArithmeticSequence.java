package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSequence {
    /**
     * approach 1. dp - TLE
     * dp[ A[j] - A[i] ][j] = A[j] - A[i] exist ? max(dp[A[j] - A[i]][i] + 1, dp[A[j] - A[i]][j])
     * <p>
     * 这个方案用C++写起来比较方便，用Java就比较麻烦，而且TLE了；
     * 思路就是上面的转移方程写的那样，二维数组第一个坐标表示两数之差，第二个坐标表示截至当前位置。任意两个数都要比较diff，所以是两次for循环。
     * 由于第一个坐标可能有20000那么大，所以用一个Map来代替，
     * 外面的Map：key: diff, val: 截止到j的最大等差数列长度的Map(key: j, val: 最大长度)。
     */
    public int longestArithSeqLength__TLE(int[] A) {
        int res = 1;
        Map<Integer, Map> diffMap = new HashMap<>();
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int b = A[j], a = A[i];
                if (!diffMap.containsKey(b - a)) {
                    //key: diff, val: 截止到j的最大等差数列长度的Map(key: j, val: 最大长度)。用Map的目的是省空间，因为步长可能非常多
                    diffMap.put(b - a, new HashMap<Integer, Integer>());
                }
                Map<Integer, Integer> jMap = diffMap.get(b - a);
                int prev = jMap.getOrDefault(i, 1), cur = jMap.getOrDefault(j, 1);
                int tmp = Math.max(prev + 1, cur);
                jMap.put(j, tmp);
                res = Math.max(res, tmp);
            }
        }
        return res;
    }

    /**
    C++写法：
    int longestArithSeqLength(vector<int>& A) {
        unordered_map<int, unordered_map<int, int>> dp;
        int res = 2;
        for (int i = 0; i < A.size(); ++i)
            for (int j = i + 1; j < A.size(); ++j)  {
                int a = A[i], b = A[j];
                dp[b - a][j] = max(dp[b - a][j], dp[b - a][i] + 1);
                res = max(res, dp[b - a][j] + 1);
            }
        return res;
    }
     **/


    /**
     * approach 2. dp，
     * 类似LIS，j在i前面；我在思考LIS能不能搞成j 在 i 后面那种(不行👋，因为是一位数组，dp[i]表示截止到i为)
     * dp[i]存放一个Map，key: A[i]对前面所有数的差（等差数列步长），val: i为止，等差数列为key的数列的长度，默认0
     */
    public int longestArithSeqLength(int[] A) {
        if (A.length <= 1) return A.length;
        int longest = 0;
        HashMap<Integer, Integer>[] dp = new HashMap[A.length];
        for (int i = 0; i < A.length; ++i) dp[i] = new HashMap<Integer, Integer>();

        for (int i = 1; i < A.length; ++i) {
            int x = A[i];
            // Iterate over values to the left of i.
            for (int j = 0; j < i; ++j) {
                int y = A[j];
                int d = x - y;
                int len = 2;
                if (dp[j].containsKey(d)) {
                    //如果加入了A[i]，那么当前能构的以A[i] - A[j]为dist的最长等差数列的的长度
                    len = dp[j].get(d) + 1;
                }
                //对于内层循环，dp[i]可能更新过很多次
                int curr = dp[i].getOrDefault(d, 0);

                // Update the max chain length for difference d at index i.
                dp[i].put(d, Math.max(curr, len));

                // Update the global max.
                longest = Math.max(longest, dp[i].get(d));
            }
        }

        return longest;
    }
}
