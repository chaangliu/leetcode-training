package jianzhioffer;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * <p>
 * 20180102
 */
public class RobotMovingRange {
    int res = 0;

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) return 0;
        dfs(threshold, rows, cols, 0, 0, new boolean[rows][cols]);
        return res;
    }

    //flood fill
    private void dfs(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || addDigits(i) + addDigits(j) > threshold || visited[i][j]) {
            return;
        }
        res++;
        visited[i][j] = true;
        dfs(threshold, rows, cols, i + 1, j, visited);
        dfs(threshold, rows, cols, i - 1, j, visited);
        dfs(threshold, rows, cols, i, j + 1, visited);
        dfs(threshold, rows, cols, i, j - 1, visited);
        //注意：这题不能把v过的置为false，跟200. Number of Islands一样
        //visited[i][j] = false;
    }

    private int addDigits(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(new RobotMovingRange().movingCount(5, 10, 10));
    }
}
