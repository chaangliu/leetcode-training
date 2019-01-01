package jianzhioffer;

/**
 * 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * 20190101
 * <p>
 * 对应LeetCode: https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    /// my code，一些细节不够好，牛客会TLE
//    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
//        if (matrix == null || matrix.length != rows * cols || str == null || str.length == 0) return false;
//        char m[][] = new char[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                m[i][j] = matrix[i * cols + j];///已犯错误1. 这里不要写成i * j..；另外，根本不需要这个额外二维数组，既然可以把1D变成2D，那比较的时候直接模拟成2D就行了
//            }
//        }
//        ///已犯错误2. 注意这里要从每个起点开始。这个跟其他flood fill不一样
//        for (int i = 0; i < rows; i++)
//            for (int j = 0; j < cols; j++) {
//                if (helper(m, str, 0, 0, new StringBuilder(), new boolean[rows][cols])) return true;
//            }
//        return false;
//    }
//
//    private boolean helper(char[][] matrix, char[] str, int i, int j, StringBuilder item, boolean[][] used) {
//        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || used[i][j]) return false;
//        item.append(matrix[i][j]);
//        if (item.length() >= str.length) {
    //此处也只需要比较当前字符就行了
//            if (item.toString().equals(String.valueOf(str))) {
//                return true;
//            }
//            item.deleteCharAt(item.length() - 1);///已犯错误3. 这次backtracking不能忘记
//            return false;
//        }
//        used[i][j] = true;
//        if (helper(matrix, str, i + 1, j, item, used)) return true;///已犯错误4.这里要return，否则后面会覆盖前面
//        if (helper(matrix, str, i - 1, j, item, used)) return true;
//        if (helper(matrix, str, i, j + 1, item, used)) return true;
//        if (helper(matrix, str, i, j - 1, item, used)) return true;
//        used[i][j] = false;
//        item.deleteCharAt(item.length() - 1);
//        return false;
//    }

//    链接：https://www.nowcoder.com/questionTerminal/c61c6999eecb4b8f88a98f66b273a3cc

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if (k == str.length - 1)
            return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }

    public static void main(String args[]) {
        char[] m = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] arr = {'A', 'S', 'A'};
        System.out.println(new WordSearch().hasPath(m, 3, 4, arr));
    }
}
