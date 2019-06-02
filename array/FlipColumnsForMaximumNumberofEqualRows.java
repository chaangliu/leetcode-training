package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
 * <p>
 * Return the maximum number of rows that have all values equal after some number of flips.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * Example 2:
 * <p>
 * Input: [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 * Example 3:
 * <p>
 * Input: [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= matrix.length <= 300
 * 1 <= matrix[i].length <= 300
 * All matrix[i].length's are equal
 * matrix[i][j] is 0 or 1
 * <p>
 * 20190602
 */
public class FlipColumnsForMaximumNumberofEqualRows {

    /**
     * 本周contest的第二题，没想出来。一直在想用DP，但这题应该是找规律。
     * 思路是把每一行都flip一遍保存起来，最后检查那种pattern出现次数最多，就是最大有多少行可以相同。
     * 原因最后只要把出现次数最多的那个pattern中的0所在列全部flip成1，或者1所在列全部flip成0即可让最多的行数数字相同。
     * 于是就转换成寻找哪个patter或者pattern的补码出现次数最多：For every row, like matrix[i], the maximum number of equal rows equals the sum of row which equals or complement matrix[i] in all columns. For example, matrix[i]={1,0,1,0,1,0},then the maximum number is the sum of rows like {1,0,1,0,1,0} and {0,1,0,1,0,1}
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int r : row) {
                sb1.append(r);
                sb2.append(1 - r);
            }
            String str1 = sb1.toString();
            String str2 = sb2.toString();

            map.put(str1, map.getOrDefault(str1, 0) + 1);
            map.put(str2, map.getOrDefault(str2, 0) + 1);
        }
        int res = 0;
        for (int v : map.values())
            res = Math.max(res, v);
        return res;
    }
}
