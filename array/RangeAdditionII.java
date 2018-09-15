package array;

/**
 * Created by DrunkPiano on 28/05/2017.
 */

public class RangeAdditionII {
	public int maxCount(int m, int n, int[][] ops) {
		int minCol1 = m, minCol2 = n;
		for (int i = 0; i < ops.length; i++) {
			if (ops[i][0] != 0 && ops[i][0] < minCol1) {
				minCol1 = ops[i][0];
			}
			if (ops[i][1] != 0 && ops[i][1] < minCol2) {
				minCol2 = ops[i][1];
			}
		}
		return minCol1 * minCol2;
	}
}
