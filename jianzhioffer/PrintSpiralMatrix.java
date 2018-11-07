package jianzhioffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * <p>
 * 这题就是LeetCode 54. Spiral Matrix。
 */
public class PrintSpiralMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return res;
        int right = 0, down = 1, left = 2, up = 3, curDirection = 0;
        int lEdge = 0, rEdge = matrix[0].length - 1, tEdge = 0, bEdge = matrix.length - 1;
        while (lEdge <= rEdge && tEdge <= bEdge) {
            curDirection %= 4;
            if (curDirection == right) {
                for (int i = lEdge; i <= rEdge; i++) {
                    res.add(matrix[tEdge][i]);
                }
                tEdge++;
                curDirection++;
            } else if (curDirection == down) {
                for (int i = tEdge; i <= bEdge; i++) {
                    res.add(matrix[i][rEdge]);
                }
                rEdge--;
                curDirection++;
            } else if (curDirection == left) {
                for (int i = rEdge; i >= lEdge; i--) {
                    res.add(matrix[bEdge][i]);
                }
                bEdge--;
                curDirection++;
            } else if (curDirection == up) {
                for (int i = bEdge; i >= tEdge; i--) {
                    res.add(matrix[i][lEdge]);
                }
                lEdge++;
                curDirection++;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> res = new PrintSpiralMatrix().printMatrix(arr);
    }
}
