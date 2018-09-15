package dfs;

/**
 * Created by DrunkPiano on 2017/4/16.
 */

public class NQueensII {

    int num = 0;

    public int totalNQueens(int n) {
        int[] col = new int[n];
        dfsHelper(n, col, 0);
        return num;
    }

    private void dfsHelper(int n, int[] col, int row) {
        if (row == n) {
            num++;
            return;
        }

        for (int i = 0; i < n; i++) {
            col[row] = i;
            if (checkValid(row, col)) {
                dfsHelper(n, col, row + 1);
            }
        }
    }

    private boolean checkValid(int row, int[] col) {
        for (int i = 0; i < row; i++)
//            for (int j = i + 1; j < row; j++) {
            //不要用double for cycle，因为row之前的已经valid了不需要比对了，只需要把每个i跟row比
            if (Math.abs(i - row) == Math.abs(col[i] - col[row]) || col[i] == col[row]) {
                return false;
//                }
            }
        return true;
    }

    public static void main(String args[]){
        System.out.println(new NQueensII().totalNQueens(4));
    }
}
