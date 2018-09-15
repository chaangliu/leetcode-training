package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * NQUEENS
 * Created by DrunkPiano on 2017/3/4.
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */

public class NQueens {
	//    public ArrayList<String[]> solveNQueens(int n) {
	public List<List<String>> solveNQueens(int n) {

		List<List<String>> res = new ArrayList<>();
		if (n <= 0)
			return res;

		dfs(res, 0, n, new int[n]);
		return res;
	}

	public boolean dfs(List<List<String>> result, int row, int n, int[] col) {
		if (row == n) {
			List<String> cell = new ArrayList<>();
			//打印一个解
			for (int i = 0; i < row; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < row; j++) {
					if (col[i] == j) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				cell.add(sb.toString());
			}
			result.add(new ArrayList<String>(cell));
			return true;
		}
		for (int i = 0; i < n; i++) {
			col[row] = i;
			if (checkValid(row, col)) {
				if (dfs(result, row + 1, n, col))
					return true;
			}
		}
		return false;
	}
	//columnVal横坐标表示Q所在行，纵坐标表示Q所在列
//	public void dfs(List<List<String>> result, int row, int n, int[] col) {
//		if (row == n) {
//			//col = [1,4,0,3];
//			List<String> cell = new ArrayList<>();
//			for (int i = 0; i < row; i++) {
//				StringBuilder sb = new StringBuilder();
//				for (int j = 0; j < row; j++) {
//					if (col[i] == j) {
//						sb.append("Q");
//					} else {
//						sb.append(".");
//					}
//				}
//				cell.add(sb.toString());
//			}
//			result.add(new ArrayList<String>(cell));
//			return;
//		}
//
//		for (int i = 0; i < n; i++) {
//			if (result.size() > 0)
//				return;
//			//i表示Q所在的列 从头到尾遍历一遍
//			col[row] = i;
//			if (checkValid(row, col)) {
//				dfs(result, row + 1, n, col);
//			}
//		}
//
//	}

	public boolean checkValid(int row, int[] col) {
		for (int i = 0; i < row; i++) {
			if (col[i] == col[row] || Math.abs(i - row) == Math.abs(col[i] - col[row]))//同一列 || 斜线
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		NQueens nQueens = new NQueens();
		System.out.println(nQueens.solveNQueens(4));


//        TreeNode a = new TreeNode(1);
//
//        TreeNode b = new TreeNode(100);
//        b = a ;
//
//        TreeNode c = a ;
//
//        System.out.println(b == c);
//
//        b.val = (2);
//
//        System.out.println(a.val);
	}
}
