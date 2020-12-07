package greedy;

/**
 * We have a two dimensional matrix A where each value is 0 or 1.
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 * Return the highest possible score.
 * Example 1:
 * Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * Output: 39
 * Explanation:
 * Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * Note:
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] is 0 or 1.
 * 20200404
 */
public class ScoreAfterFlippingMatrix {
    /**
     * 题意：给你一个含有1和0的数组，每次可以flip一行或者一列，最后按行统计二进制的值，问最大的和是多少。
     * 解法：这题的思路是，首先要贪心地保证第一列全是1，因为后面所有加起来都没有第一列重要。然后就从第二列开始统计flip之后0的个数，按列累加到结果里去。
     * 思路不难理解，但是写起来有一定的技巧，可以巧妙地利用异或操作来统计flip之后每行的0的个数。
     */
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length, res = 0;
        for (int i = 0; i < n; i++) {
            int zeros = 0;
            for (int j = 0; j < m; j++) {
                zeros += A[j][0] ^ A[j][i]; // 统计flip之后每列0的个数之和(用第一个数字跟后面的xor，很巧妙)
            }
            res += Math.max(zeros, m - zeros) * (1 << (n - 1 - i)); // 如果0多，就flip这一列；否则保持不变。
        }
        return res;
    }
}
