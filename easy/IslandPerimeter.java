package easy;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class IslandPerimeter {
	public int islandPerimeter(int[][] grid) {
		int res = 0;
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					res += calc(row, col, grid);
				}
			}
		return res;
	}

	private int calc(int row, int col, int[][] matrix) {
		int count = 0;
		if (row == 0 || row > 0 && matrix[row - 1][col] == 0) {
			count++;
		}
		if (col == 0 || col > 0 && matrix[row][col - 1] == 0) {
			count++;
		}
		if (row == matrix.length - 1 || row < matrix.length - 1 && matrix[row + 1][col] == 0) {
			count++;
		}
		if (col == matrix[0].length - 1 || col < matrix[0].length - 1 && matrix[row][col + 1] == 0) {
			count++;
		}
		return count;
	}
}
