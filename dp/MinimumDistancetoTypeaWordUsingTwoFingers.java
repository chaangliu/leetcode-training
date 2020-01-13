package dp;

/**
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 * <p>
 * Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 * <p>
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
 * Example 1:
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 * Example 2:
 * <p>
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation:
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 * Example 3:
 * <p>
 * Input: word = "NEW"
 * Output: 3
 * Example 4:
 * <p>
 * Input: word = "YEAR"
 * Output: 7
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= word.length <= 300
 * Each word[i] is an English uppercase letter.
 * 20200113
 */
public class MinimumDistancetoTypeaWordUsingTwoFingers {
    /**
     * 题意：两支手指打字，问合作打完一串字符串最短需要多少曼哈顿距离。
     * 解法：DP。top-down, bottom-up都比较容易理解
     * dp[i][j][k]: 左手在i，右手在j，即将打出在字符串k位置的char，至少还需要多少代价
     * 两只手指分别需要多申请一个状态，代表手指悬空状态，需要的代价是0。
     * Top down with memo:
     * Time: O(n * 27 ^ m): where m is the number of fingers.
     * Memory: O(n * 27 ^ m) for memoisation.
     */
    public int minimumDistance(String word) {
        Integer[][][] memo = new Integer[27][27][301];
        dfs(word, 26, 26, 0, memo);
        return memo[26][26][0];
    }

    /**
     * dfs返回左手在left上，右手在right上，在word.pos处的最优解
     **/
    private int dfs(String word, int l, int r, int pos, Integer[][][] memo) {
        if (pos >= word.length()) {
            return 0;
        }
        if (memo[l][r][pos] == null) {
            int nextChar = word.charAt(pos) - 'A';
            memo[l][r][pos] = Math.min(cost(l, nextChar) + dfs(word, nextChar, r, pos + 1, memo),
                    cost(r, nextChar) + dfs(word, l, nextChar, pos + 1, memo));
        }
        return memo[l][r][pos];
    }

    private int cost(int from, int to) {
        if (from == 26) return 0; // 26 初始状态手指悬空
        //return Math.abs((from-to)/ 6) + Math.abs(from%6 - to%6);// 纵向 + 横向 = 曼哈顿距离 //已犯错误：注意Math.abs((from-to)/ 6)和Math.abs(from/6 - to/6)是不一样的，后者才能表示行距。
        return Math.abs(from / 6 - to / 6) + Math.abs(from % 6 - to % 6);// 纵向 + 横向 = 曼哈顿距离
    }

    /**
     * Top down with memo，空间优化，比较抽象....
     * 哪根手指先动，效果是一样的，只需要记录一根手指的状态first，另一跟手指的状态就是刚才按下pos-1的手指的状态
     **/
    public int minimumDistance_(String word) {
        Integer[][] memo = new Integer[27][301];
        dfs__(word, 26, 1, memo); //当前手指悬空，放置在1的位置
        return memo[26][1];
    }

    private int dfs__(String word, int cur, int pos, Integer[][] memo) {
        if (pos == word.length()) return 0;
        if (memo[cur][pos] == null) {
            int nextChar = word.charAt(pos) - 'A', second = word.charAt(pos - 1) - 'A';
            memo[cur][pos] = Math.min(cost(second, nextChar) + dfs__(word, cur, pos + 1, memo),//可以用当前手指按pos，也可以继续用上一次用的字母按pos
                    cost(cur, nextChar) + dfs__(word, second, pos + 1, memo));
        }
        return memo[cur][pos];
    }

    /**
     * bottom up, 最后的状态是0，所以从后往前
     **/
    public int minimumDistance__(String word) {
        int[][][] dp = new int[301][27][27];
        for (int pos = word.length() - 1; pos >= 0; --pos) {
            int to = word.charAt(pos) - 'A';
            for (int i = 0; i < 27; ++i) {
                for (int j = 0; j < 27; ++j) {
                    dp[pos][i][j] = Math.min(dp[pos + 1][to][i] + cost(j, to), dp[pos + 1][to][j] + cost(i, to));//左手不动，移动右手 or 右手不动，移动左手
                }
            }
        }
        return dp[0][26][26];
    }
}
