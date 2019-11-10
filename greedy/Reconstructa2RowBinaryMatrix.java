package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the following details of a matrix with n columns and 2 rows :
 * The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
 * The sum of elements of the 0-th(upper) row is given as upper.
 * The sum of elements of the 1-st(lower) row is given as lower.
 * The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 * Return it as a 2-D integer array.
 * If there are more than one valid solution, any of them will be accepted.
 * If no valid solution exists, return an empty 2-D array.
 * Example 1:
 * Input: upper = 2, lower = 1, colsum = [1,1,1]
 * Output: [[1,1,0],[0,0,1]]
 * Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
 * Example 2:
 * Input: upper = 2, lower = 3, colsum = [2,2,1,1]
 * Output: []
 * Example 3:
 * Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * Constraints:
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 * 20191110
 */
public class Reconstructa2RowBinaryMatrix {
    /**
     * 题意：一个2行n列的数组，第一行的sum是upper，第二行的sum是lower，每一列的sum存在colsum里。还原出任意一个数组。
     * 有两个容易出错的地方：
     * 1. colsum是1的时候不能优先给第一行赋1，因为后面有可能有2的情况。这里可以给剩下的1容量多的那一行赋1.
     * 2. 最后要再判断一下upper和lower有没有用光。
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> res = new ArrayList<>();
        int tmp = 0;
        for (int col : colsum) tmp += col;
        if (upper + lower != tmp) return res;
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        List<Integer> row1 = res.get(0);
        List<Integer> row2 = res.get(1);
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 0) {
                row1.add(0);
                row2.add(0);
            } else if (colsum[i] == 2) {
                row1.add(1);
                row2.add(1);
                upper--;
                lower--;
            } else {
                if (upper > lower) {//greedy
                    row1.add(1);
                    row2.add(0);
                    upper--;
                } else {
                    row1.add(0);
                    row2.add(1);
                    lower--;
                }
            }
        }
        if (upper != 0 || lower != 0) return new ArrayList<>();
        return res;
    }
}
