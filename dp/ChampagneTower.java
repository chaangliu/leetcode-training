package dp;

/**
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup (250ml) of champagne.
 * Then, some champagne is poured in the first glass at the top.  When the top most glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has it's excess champagne fall on the floor.)
 * For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.
 * Now after pouring some non-negative integer cups of champagne, return how full the j-th glass in the i-th row is (both i and j are 0 indexed.)
 * Example 1:
 * Input: poured = 1, query_glass = 1, query_row = 1
 * Output: 0.0
 * Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.
 * Example 2:
 * Input: poured = 2, query_glass = 1, query_row = 1
 * Output: 0.5
 * Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.
 * Note:
 * poured will be in the range of [0, 10 ^ 9].
 * query_glass and query_row will be in the range of [0, 99].
 * 20191123
 */
public class ChampagneTower {
    /**
     * 这题我本来想用数学的角度去考虑，计算每层每个杯子里面香槟的比例，但是后来发现想的太天真，思维有漏洞。
     * 解法还是要用递推,dp[i][j]表示流经i行j个杯子的香槟数量。
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[102][102];
        dp[0][0] = (double) poured;
        for (int r = 0; r <= query_row; ++r) {
            for (int c = 0; c <= r; ++c) {
                double q = (dp[r][c] - 1.0) / 2.0;
                if (q > 0) {
                    dp[r + 1][c] += q;
                    dp[r + 1][c + 1] += q;
                }
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }
}
