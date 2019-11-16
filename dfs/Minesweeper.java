package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 * <p>
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * <p>
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
 * <p>
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * Example 1:
 * Input:
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * Example 2:
 * Input:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * Note:
 * The range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have been revealed).
 * For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 * 20191116
 */
public class Minesweeper {
    /**
     * 题意：扫雷游戏，给你一个点击位置，让你返回点完之后的棋盘状态。规则：
     * 1. 如果点击M，游戏结束，M->X
     * 2. 如果点击空格，会一直蔓延到外层的数字
     * 3. 如果点击数字，会直接显示当前数字
     * <p>
     * 解法1：DFS
     **/
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        //如果八个方向有地雷，显示数字，返回
        //如果八个方向没有地雷，DFS地找到第一个是数字的格子
        dfs(board, x, y);
        return board;
    }

    private void dfs(char[][] b, int x, int y) {
        if (x < 0 || x >= b.length || y < 0 || y >= b[0].length) return;
        if (b[x][y] == 'B') return;
        int m = getMines(b, x, y);
        if (m > 0) {
            b[x][y] = (char) (m + '0');
            return;
        }
        b[x][y] = 'B';
        for (int i = -1; i <= 1; i++)//已犯错误：注意这里是8个方向，我一开始思维定势地向4个方向DFS了。
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int x1 = x + i, y1 = y + j;
                if (x1 < 0 || x1 >= b.length || y1 < 0 || y1 >= b[0].length) continue;
                dfs(b, x1, y1);
            }
    }

    //统计八个方向的地雷数量
    private int getMines(char[][] b, int x, int y) {
        int res = 0;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] d : dir) {
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 < 0 || x1 >= b.length || y1 < 0 || y1 >= b[0].length) continue;
            if (b[x1][y1] == 'M') res++;
        }
        return res;
    }

    /**
     * 解法2：BFS
     **/
    public char[][] updateBoard__BFS(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        bfs(board, x, y);
        return board;
    }

    //向8个方向BFS，遇到B就把它变成E，然后加入到QUEUE中
    private void bfs(char[][] b, int x, int y) {
        int m = getMines(b, x, y);
        if (m > 0) {
            b[x][y] = (char) (m + '0');
            return;
        }
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x1 = p[0], y1 = p[1];
            if (b[x1][y1] != 'E') continue;
            int c = getMines(b, x1, y1);
            if (c > 0) {
                b[x1][y1] = (char) (c + '0');
                continue;
            }
            b[x1][y1] = 'B';
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    int x2 = x1 + i, y2 = y1 + j;
                    if (x2 < 0 || x2 >= b.length || y2 < 0 || y2 >= b[0].length) continue;
                    q.offer(new int[]{x2, y2});
                }
        }
    }
}
