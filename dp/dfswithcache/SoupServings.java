package dp.dfswithcache;

/**
 * There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:
 * <p>
 * Serve 100 ml of soup A and 0 ml of soup B
 * Serve 75 ml of soup A and 25 ml of soup B
 * Serve 50 ml of soup A and 50 ml of soup B
 * Serve 25 ml of soup A and 75 ml of soup B
 * When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.
 * <p>
 * Note that we do not have the operation where all 100 ml's of soup B are used first.
 * <p>
 * Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.
 * Example:
 * Input: N = 50
 * Output: 0.625
 * Explanation:
 * If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
 * <p>
 * Notes:
 * <p>
 * 0 <= N <= 10^9.
 * Answers within 10^-6 of the true value will be accepted as correct.
 * 20191017
 */
public class SoupServings {
    /**
     * 题意：每次serve A,B两种汤一定的毫升，有四种套餐，每次选择概率都是1/4，当有一个上完了就停止，如果不够一份，当做一份serve。返回A先empty的概率+ 0.5 * AB同时empty的概率
     * 思路，DFS with memo，模拟四种套餐。值得注意的是,两种套餐的计量要分开保存在memo，这一点我没想到。memo[i][j]表示i毫升的A和j毫升的B需要返回的概率。最终的概率计算，仍然是0.25 * (A + AB)
     **/
    public double soupServings(int N) {
        if (N > 5000) return 1.0;//由于A平均取得多，答案会趋向于1; 题目允许误差10^-6，N = 5k已经满足这个误差
        return dfs(N, N, new Double[N + 1][N + 1]);
    }

    private double dfs(int a, int b, Double[][] memo) {//trick, Double
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;
        if (memo[a][b] != null) return memo[a][b];
        int[] A = new int[]{100, 75, 50, 25};
        int[] B = new int[]{0, 25, 50, 75};
        memo[a][b] = 0.0;
        for (int i = 0; i < 4; i++) {
            memo[a][b] += dfs(a - A[i], b - B[i], memo);//四种取法最后的概率相加，乘以0.25，就是所求的结果
        }
        memo[a][b] *= 0.25;
        return memo[a][b];
    }
}
