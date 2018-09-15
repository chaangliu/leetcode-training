package array;

/**
 * Created by DrunkPiano on 14/05/2017.
 */

public class ShortestUnsortedContinuousSubarrays {
//	public int findUnsortedSubarray(int[] nums) {
//		if (nums == null || nums.length == 0) return 0;
//
//		int sorted[] = new int[nums.length];
//		System.arraycopy(nums, 0, sorted, 0, nums.length);
//
//		Arrays.sort(sorted);
//		if (Arrays.equals(sorted, nums)) return 0;
//
//		int minLen = nums.length;
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i + 1; j < nums.length; j++) {
//				int[] subarray = Arrays.copyOfRange(nums, i, j + 1);
//				Arrays.sort(subarray);
//				for (int k = i; k <= j; k++) {
//					int temp[] = new int[nums.length];
//					System.arraycopy(nums, 0, temp, 0, nums.length);
//					System.arraycopy(subarray, 0, temp, i, subarray.length);
//					if (Arrays.equals(temp, sorted)) {
//						minLen = Math.min(j - i + 1, minLen);
//					}
//				}
//			}
//		}
//		return minLen;
//	}

//	public int findUnsortedSubarray(int[] nums) {
//		if (nums == null || nums.length == 0) return 0;
//
//		int sorted[] = new int[nums.length];
//		System.arraycopy(nums, 0, sorted, 0, nums.length);
//
//		Arrays.sort(sorted);
//		if (Arrays.equals(sorted, nums)) return 0;
//
//		int minLen = nums.length;
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i + 1; j < nums.length; j++) {
//				int temp[] = new int[nums.length];
//				System.arraycopy(nums, 0, temp, 0, nums.length);
//				Arrays.sort(temp, i, j + 1);
//				if (Arrays.equals(sorted, temp)) {
//					minLen = Math.min(j + 1 - i, minLen);
//					break;
//				}
//			}
//		}
//		return minLen;
//	}

//	public int findUnsortedSubarray(int[] A) {
//		int start = -1, end = -2, min = Integer.MAX_VALUE, max = A[0];
//
//		for (int i = 1; i < A.length; i++) {
//			if (A[i] < max) {
//				if (start == -1) start = i - 1;
//				end = i;
//				min = Math.min(min, A[i]);
//			} else max = A[i];
//		}
//
//		for (int i = 0; i < A.length; i++)
//			if (min < A[i]) {
//				start = i;
//				break;
//			}
//
//		return end - start + 1;
//	}


//	public int findUnsortedSubarray(int[] nums) {
//		int [] temp = new int[nums.length];
//		System.arraycopy(nums,0 , temp , 0 , nums.length);
//		Arrays.sort(temp);
//		int start = 0 , end = nums.length -1 ;
//		while (start< nums.length && temp[start] == nums[start] ){
//			start ++ ;
//		}
//		while (end> start && temp[end] == nums[end]){
//			end -- ;
//		}
//		return end + 1 - start;
//	}

	public int findUnsortedSubarray(int[] A) {
		int start = -1, end = -2, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (A[i] < max) {
				if (start == -1) start = i - 1;
				end = i;
				min = Math.min(min, A[i]);
			} else max = A[i];
		}

		for (int i = 0; i < A.length; i++)
			if (A[i] > min) {
				start = i;
				break;
			}

		return end - start + 1;
	}

	public static void main(String args[]) {
//		int [] nums = {2, 6, 4, 8, 10, 9, 15};
		int[] nums = {2, 6, 4, 8, 10, 9, 15};
		System.out.println(new ShortestUnsortedContinuousSubarrays().findUnsortedSubarray(nums));
	}
}
