package array;

import java.util.LinkedList;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Created by DrunkPiano on 2017/4/21.
 */

public class SurroundRegions {

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O')
                dfs(board, i, 0, row, col);
            if (col > 1)
                if (board[i][col-1] == 'O')
                    //这样的话只有一列的情况不用单独处理了
                    dfs(board, i, col - 1, row, col);
        }
        //这里可以掐头去尾
        for (int i = 1; i < col - 1; i++) {
            if (board[0][i] == 'O')
                dfs(board, 0, i, row, col);
            if (row > 1)
                if (board[row-1][i] == 'O')
                    dfs(board, row - 1, i, row, col);
        }
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
    }

    private void dfs(char[][] board, int i, int j, int row, int col) {

        //泛型只能有一个参数
        LinkedList<Pair> queue = new LinkedList<>();
        Pair pair = new Pair(i, j);
        queue.add(pair);
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int x = p.x;
            int y = p.y;
            //因为floodfill向四个方向扩散，所以可能回到刚才蔓延过的地方
            if (board[x][y] == 'Y') continue;
            if (board[x][y] == 'O') {
                board[x][y] = 'Y';
            }

            if (x - 1 >= 0 && board[x - 1][y] == 'O') {
                queue.add(new Pair(x - 1, y));
            }
            if (x + 1 < row && board[x + 1][y] == 'O') {
                queue.add(new Pair(x + 1, y));
            }
            if (y - 1 >= 0 && board[x][y - 1] == 'O') {
                queue.add(new Pair(x, y - 1));
            }
            if (y + 1 < col && board[x][y + 1] == 'O') {
                queue.add(new Pair(x, y + 1));
            }
        }
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) {
        char[][] matrix = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        new SurroundRegions().solve(matrix);
        System.out.println(matrix);
    }
}


