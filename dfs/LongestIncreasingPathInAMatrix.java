package dfs;


/**
 * Created by DrunkPiano on 14/06/2017.
 */

public class LongestIncreasingPathInAMatrix {
//	int maxLen = 1;
//
//	public int longestIncreasingPath(int[][] matrix) {
//		int m = matrix.length;
//		if (m == 0) return 0;
//		int n = matrix[0].length;
//		if (n == 0) return 0;
//		boolean[][] visited = new boolean[m][n];
//		for (int i = 0; i < m; i++)
//			for (int j = 0; j < n; j++) {
//				dfs(matrix, visited, 1, i, j, Integer.MIN_VALUE);
//			}
//		return maxLen;
//	}
//
//	private void dfs(int[][] matrix, boolean[][] visited, int curLen, int x, int y, int pre) {
//		if (x >= matrix.length || x < 0 || y >= matrix[0].length || y < 0) {
//			return;
//		}
//		if (visited[x][y]) {
//			return;
//		}
//		if (matrix[x][y] <= pre) {
//			return;
//		}
//		if (curLen > maxLen) {
//			maxLen = curLen;
//		}
//		visited[x][y] = true;
//		dfs(matrix, visited, curLen + 1, x + 1, y, matrix[x][y]);
//		dfs(matrix, visited, curLen + 1, x - 1, y, matrix[x][y]);
//		dfs(matrix, visited, curLen + 1, x, y + 1, matrix[x][y]);
//		dfs(matrix, visited, curLen + 1, x, y - 1, matrix[x][y]);
//		visited[x][y] = false;
//	}

	public static void main(String args[]) {
		int[][] nums = {
				{9, 9, 4},
				{6, 6, 8},
				{2, 1, 1}
		};
//		int[][] nums = {
//				{3, 4, 5 , 6},
//				{3, 2, 6 , 7},
//				{2, 2, 1 , 6}
//		};
		new LongestIncreasingPathInAMatrix().
				longestIncreasingPath(nums);
	}

	public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0) return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][] cache = new int[m][n];
		int max = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int len = dfs(matrix, i, j, cache);
				max = Math.max(len, max);
			}
		}
		return max;
	}

	public int dfs(int[][] matrix, int i, int j, int[][] maxLenFromHere) {
		if (maxLenFromHere[i][j] != 0)
			return maxLenFromHere[i][j];
		int max = 1;
		for (int[] dir : dirs) {
			int x = i + dir[0], y = j + dir[1];
			if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
				continue;
			}
			//如果四个方向都不满足，下面两行就不会走
			int len = 1 + dfs(matrix, x, y, maxLenFromHere);
			//这个max让我有些疑惑，max = len不行吗？不行的，因为一直在递归，len不是递增的。
			// 另外，max定义在for外面一层刚好，不需要设置成global的
			max = Math.max(max, len);
		}
		maxLenFromHere[i][j] = max;
		return max;
	}


//	int m, n, res = 0, matrix[][], maxMatrix[][];
//
//	public int longestIncreasingPath(int[][] matrix) {
//		this.matrix = matrix;
//		m = matrix.length;
//		if (0 < m) {
//			n = matrix[0].length;
//			maxMatrix = new int[m][n];
//			for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (0 == maxMatrix[i][j]) longestIncreasingPath(i, j);
//		}
//		return res;
//	}
//
//	int longestIncreasingPath(int i, int j) {
//		if (maxMatrix[i][j] > 0) return maxMatrix[i][j];
//		maxMatrix[i][j] = 1;
//		if (0 < i && matrix[i - 1][j] > matrix[i][j])
//			maxMatrix[i][j] = Math.max(maxMatrix[i][j], longestIncreasingPath(i - 1, j) + 1);
//		if (m - 1 > i && matrix[i + 1][j] > matrix[i][j])
//			maxMatrix[i][j] = Math.max(maxMatrix[i][j], longestIncreasingPath(i + 1, j) + 1);
//		if (0 < j && matrix[i][j - 1] > matrix[i][j])
//			maxMatrix[i][j] = Math.max(maxMatrix[i][j], longestIncreasingPath(i, j - 1) + 1);
//		if (n - 1 > j && matrix[i][j + 1] > matrix[i][j])
//			maxMatrix[i][j] = Math.max(maxMatrix[i][j], longestIncreasingPath(i, j + 1) + 1);
//		res = Math.max(res, maxMatrix[i][j]);
//		return maxMatrix[i][j];
//	}

}
