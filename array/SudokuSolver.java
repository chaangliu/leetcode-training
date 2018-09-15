package array;

/**
 * Created by DrunkPiano on 2017/4/13.
 */

public class SudokuSolver {
	char[][] res;

	public void solveSudoku(char[][] board) {
		helper(board, 0, 0);
		board = res;
	}

	//june 22
	private void helper(char[][] board, int i, int j) {
		if (j >= 9) {
			helper(board, i + 1, 0);
			return;
		}
		if (i == 9) {
			res = board;
			return;
		}
		if (board[i][j] == '.') {
			for (int k = 1; k <= 9; k++) {
				if (res != null) return;
				board[i][j] = (char) (k + '0');
				if (isValid(board, i, j)) {
					helper(board, i, j + 1);
				}
				board[i][j] = '.';
			}
		} else {
			helper(board, i, j + 1);
		}
	}

	private boolean isValid(char[][] board, int i, int j) {
		for (int k = 0; k < 9; k++) {
			if (k != j && board[i][k] == board[i][j])
				return false;
		}
		for (int k = 0; k < 9; k++) {
			if (k != i && board[k][j] == board[i][j])
				return false;
		}
		for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
			for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
				if ((row != i || col != j) && board[row][col] == board[i][j])
					return false;
			}
		}
		return true;
	}


//    //1 为什么boolean而不是void  2. 为什么i+1 而不是i++ 3.为什么恢复现场
//    private boolean dfs(char[][] board, int i, int j) {
//        if (i == 9) return true;
//        if (j == 9) {
//            return dfs(board, ++i, 0);
//        }
//
////        这里没有用到双重for循环遍历棋盘，而是采用了把坐标作为参数的方式
//        if (board[i][j] == '.') {
//            for (int k = 1; k <= 9; k++) {
//                board[i][j] = (char) (k + '0');
//                //先把数字填进去，如果valid的话，就下一个格子；并且如果下一个格子也可以的话，就不把当前的格子还原了，否则还原
//                if (isValidSudoku(board)) {
//                    if (dfs(board, i, j + 1)) return true;
//                }
//                //4. 为什么这里不能用else包：
//                board[i][j] = '.';//这里还是写到if里面去比较好理解
//            }
//        } else {
//            return dfs(board, i, j + 1);
//        }
//        return false;
//    }
//
//    public boolean isValidSudoku(char[][] board) {
//        if (board.length != 9 || board[0].length != 9) return false;
//        int[] temp = new int[9];
//        //判断每一行
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (board[i][j] != '.') {
//                    if (temp[board[i][j] - '1'] != 0) {
//                        return false;
//                    }
//                    temp[board[i][j] - '1'] = 1;
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
//                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
//                    if (board[i][j] != '.') {
//                        if (temp[board[i][j] - '1'] != 0) {
//                            return false;
//                        }
//                        temp[board[i][j] - '1'] = 1;
//                    }
//                }
//            }
//            temp = new int[9];
//        }
//        return true;
//
//    }
//
//
//    private boolean helper(char[][] board, int position) {
//        if (position == 81) return true;
//        int row = position / 9;
//        int col = position % 9;
//
//        if (board[row][col] == '.') {
//            for (char i = '1'; i < '9'; i++) {
//                board[row][col] = i;
//                //如果这个数字不会让棋盘坏掉，就用dfs插下一个数字
//                if (checkValid(board, position, i)) {
//                    //如果这样下去可以构造成一个pos == 80的完整board的话，就不用恢复现场了
//                    if (helper(board, position + 1)) {
//                        return true;
//                    } else {
//                        board[row][col] = '.';
//                    }
//                }
//            }
//        } else {
////            if (helper(board, position + 1)) return true;
//            return helper(board, position + 1);
//        }
//
//        //return false是因为 return true的终止条件已经写在最前面了
//        return false;
//    }

	private boolean checkValid(char[][] board, int position, char ins) {
		int row = position / 9;
		int col = position % 9;

		for (int i = 0; i < 9; i++) {
			if (i != row && board[i][col] == ins)
				return false;

		}

		for (int i = 0; i < 9; i++) {
			if (i != col && board[row][i] == ins)
				return false;
		}

		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++)
			for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
//                i == row && j == col的否命题是(i != row || j != col)，同时注意||优先级小于&&
				if ((i != row || j != col) && board[i][j] == ins) return false;
			}
		return true;
	}


}
