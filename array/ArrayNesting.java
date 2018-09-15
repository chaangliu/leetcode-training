package array;

/**
 * Input: A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * <p>
 * One of the longest S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * <p>
 * Created by DrunkPiano on 28/05/2017.
 */

public class ArrayNesting {
//	public int arrayNesting(int[] nums) {
//		int maxSize = 1;
//		if (nums == null || nums.length == 0) return maxSize;
//		for (int i = 0; i < nums.length; i++) {
//			int nextIdx = nums[i];
//			int curSize = 1;
//			while (nums[nextIdx]!= Math.abs(nextIdx)) {
//				nextIdx = -1 * nums[nextIdx];
//				curSize++;
//				maxSize = Math.max(curSize, maxSize);
//			}
//		}
//		return maxSize;
//	}
//
	public int arrayNesting(int[] nums) {
		int res = 0;
		for (int i=0;i<nums.length;i++) {
			if (nums[i] < 0) continue;
			int length = 1, val = nums[i];
			while (Math.abs(val) != i) {
				length++;
				val = nums[Math.abs(val)];
				nums[Math.abs(val)] *= -1;
			}
			res = Math.max(res, length);
		}
		return res;
	}

	public static void main(String args[]) {
		int[] nums = {5, 4, 0, 3, 1, 6, 2};
		new ArrayNesting().arrayNesting(nums);
	}
}
