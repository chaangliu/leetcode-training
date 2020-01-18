package dfs;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * <p>
 * Created by DrunkPiano on 2017/2/9.
 * 20190404 review
 * 20200118 review
 */

public class WordSearch {
    /**
     * 20190404 review
     * 不用visited[][]空间
     */
    public boolean exist__(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs__(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean dfs__(char[][] board, String word, int index, int x, int y) {
        if (index == word.length()) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(index) || board[x][y] == '#') return false;
        char tmp = board[x][y];
        board[x][y] = '#';
        if (dfs__(board, word, index + 1, x - 1, y)) return true;
        if (dfs__(board, word, index + 1, x + 1, y)) return true;
        if (dfs__(board, word, index + 1, x, y - 1)) return true;
        if (dfs__(board, word, index + 1, x, y + 1)) return true;
        board[x][y] = tmp;
        return false;
    }

    /**
     * 题意：一个二维数组里搜索单词，字母之间只能垂直或者水平相邻。同一个cell不能用多次。能找到就返回true。
     * 解法：回溯。我以为这题我分分钟就能A掉，但是写了之后TLE好几次。一开始忘了剪枝；另外还用了额外空间，这题可以直接in pace标记visited的，v过就没有利用价值了，所以可以标记成任何东西。
     **/
    public boolean exist_(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;
        int m = board.length, n = board[0].length;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) != board[i][j]) continue;
                visited[i][j] = true;
                if (dfs(board, word, visited, board[i][j] + "", i, j, dirs)) return true;
                visited[i][j] = false;
            }

        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] visited, String cur, int i, int j, int[][] dirs) {
        if (cur.length() == word.length() && word.equals(cur)) return true;
        int m = board.length, n = board[0].length;
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x < m && x >= 0 && y < n && y >= 0 && !visited[x][y]) {
                if (word.length() > cur.length() && board[x][y] != word.charAt(cur.length())) continue;
                visited[x][y] = true;
                if (dfs(board, word, visited, cur + board[x][y], x, y, dirs)) return true;
                visited[x][y] = false;
            }
        }
        return false;
    }

    /**
     * SOLUTIONS里的写法
     */
    public boolean exist___(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1)
                || exist(board, y, x - 1, word, i + 1)
                || exist(board, y + 1, x, word, i + 1)
                || exist(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
    }

    /**
     * original post---------------------------------------------------------------------------------------------------------------------------------------------
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(visited, board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean dfs(boolean[][] visited, char[][] board, String word, int index, int x, int y) {
        //define some restrictions to force the function to quit when doesn't meet the standard
        //这是唯一的true 的条件
        if (index == word.length()) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        if (board[x][y] != word.charAt(index)) return false;
        if (visited[x][y]) return false;

        visited[x][y] = true;
        if (dfs(visited, board, word, index + 1, x - 1, y)) return true;
        if (dfs(visited, board, word, index + 1, x + 1, y)) return true;
        if (dfs(visited, board, word, index + 1, x, y - 1)) return true;
        if (dfs(visited, board, word, index + 1, x, y + 1)) return true;
        //if didn't reach the true answer, recover the state of visited sigh. this is the idea of BACKTRACKING.
        visited[x][y] = false;

        return false;
    }

    /**
     * test--------------------------------------------------------------------------------------------------------------------------------------------------------
     */


    public static void main(String args[]) {
        char[][] a = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(a, "SEE"));
    }
}
