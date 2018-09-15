package easy;

/**
 * Created by DrunkPiano on 15/06/2017.
 */

public class NextGreaterElementI {
//	public int[] nextGreaterElement(int[] findNums, int[] nums) {
//		int[] res = new int[findNums.length];
//		for (int i = 0; i < findNums.length; i++) {
//			int index = 0;
//			for (int j = 0; j < nums.length; j++) {
//				if (nums[j] == findNums[i]) {
//					index = j;
//					break;
//				}
//			}
//			for (int k = index + 1; k < nums.length; k++) {
//				if (nums[k] > findNums[i]) {
//					res[i] = nums[k];
//					break;
//				}
//			}
//			if (res[i] == 0) {
//				res[i] = -1;
//			}
//		}
//		return res;
//	}

	public static void sort(int array[], int low, int high) {
		if (low >= high) return;
		int i = low, j = high;
		int pivot = array[low];
		while (i < j) {
			while (i < j && array[j] > pivot)
				j--;
			if (i < j)
				array[i++] = array[j];
			while (i < j && array[i] < pivot)
				i++;
			if (i < j)
				array[j--] = array[i];
		}
		array[i] = pivot;
		sort(array, low, i - 1);
		sort(array, i + 1, high);
	}

	public static void quickSort(int array[]) {
		sort(array, 0, array.length - 1);
	}

	public static void main(String args[]) {
		int i = 0;
//		int a[] = {3, 4, 1, 2, 6, 1, 3, 6};
		int a[] = {3, 4, 1, 2, 5, 7, 11, 32, 6, 1, 3, 6};
		int len = a.length;
		quickSort(a);
		for (i = 0; i < len; i++)
			System.out.print(a[i] + " ");
		System.out.println(a.length);
	}
}
