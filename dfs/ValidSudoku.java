package dfs;

/**
 * Created by DrunkPiano on 2017/4/13.
 */

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return false;
        int[] temp = new int[9];
        //判断每一行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (temp[board[i][j] - '1'] != 0) {
                        return false;
                    }
                    temp[board[i][j] - '1'] = 1;
                }
            }
            temp = new int[9];
        }

        //判断每一列
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (temp[board[j][i] - '1'] != 0) {
                        return false;
                    }
                    temp[board[j][i] - '1'] = 1;
                }
            }
            temp = new int[9];
        }

        //判断9个小units
        for (int block = 0; block < 9; block++) {
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (temp[board[i][j] - '1'] != 0) {
                            return false;
                        }
                        temp[board[i][j] - '1'] = 1;
                    }
                }
            }
            temp = new int[9];
        }
        return true;

    }

//    private boolean checkValid(char board[][]) {
////        HashMap map = new HashMap();
////        int[] temp = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
//        int[] temp = new int[9];
//        //判断每一行
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (board[i][j] != '.') {
//                    if (temp[board[i][j] - '1'] != 0) {
//                        return false;
//                    }
//                    temp[board[j][i] - '1'] = 1;
//                }
//            }
//            temp = new int[9];
//        }
//
//        //判断每一列
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (board[j][i] != '.') {
//                    if (temp[board[j][i] - '1'] != 0) {
//                        return false;
//                    }
//                    temp[board[j][i] - '1'] = 1;
//                }
//            }
//            temp = new int[9];
//        }
//
//        //判断9个小units
//        for (int block = 0; block < 9; block++) {
//            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
//                for (int j = block % 3; j < block % 3 + 3; j++) {
//                    if (board[i][j] != '.') {
//                        if (temp[board[i][j] - '0'] != 0) {
//                            return false;
//                        }
//                        temp[board[i][j]] = 1;
//                    }
//                }
//            }
//            temp = new int[9];
//        }
//        return true;
//    }

    public static void main(String args[]) {
        char i = 9;
        System.out.println(i - 1);
    }
}
