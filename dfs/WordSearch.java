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
 */

public class WordSearch {
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

    public static void main(String args[]) {
        char[][] a = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(a, "SEE"));
    }
}
