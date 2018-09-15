package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 29/06/2017.
 */

public class RotateFunction {
	public int maxRotateFunction(int[] A) {
		if (A == null || A.length == 0) return 0;
		int n = A.length;
		int max = Integer.MIN_VALUE;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		for (int i = 0; i < n; i++) {
			int product = 0;
			for (int j = 0; j < n; j++) {
				product += list.get(j) * A[j];
			}
			max = Math.max(max, product);
			list.add(list.remove(0));
		}
		return max;
	}

	public static void main(String args[]) {
		int[] A = {4, 3, 2, 6};
		new RotateFunction().maxRotateFunction(A);
	}
}
