package dfs;

/**
 * Created by DrunkPiano on 16/06/2017.
 */

public class PermutationSequence {
//	String res = "";
//
//	public String getPermutation(int n, int k) {
//		int[] count = new int[1];
//		boolean[] used = new boolean[n + 1];
//		dfs(n, k, count, used, "", 1);
//		return res;
//	}
//
//	private String dfs(int n, int k, int[] count, boolean[] used, String item, int arrayIndex) {
//		if (item.length() == n) {
//			count[0]++;
//			return null;
//		}
//		if (k == count[0]) {
//			res = item;
////			return item;
//		}
//		for (int i = arrayIndex; i <= n; i++) {
//			if (!used[i]) {
//				used[i] = true;
//				dfs(n, k, count, used, item + i, arrayIndex + 1);
//				used[i] = false;
//			}
//		}
//		return null;
//	}

	String res = "";
	public String getPermutation(int n, int k) {
		int[] count = new int[1];
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = i + 1;
		}
		backtracking(count, k, num, "", new boolean[num.length]);
		return res;

	}

	private void backtracking(int[] count, int k, int[] nums, String item, boolean[] used) {
		if (item.length() == nums.length) {
			if (++count[0] == k) {
				res = item;
			}
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				used[i] = true;
				backtracking(count, k, nums, item + nums[i], used);
				used[i] = false;
			}
		}
	}

	public static void main(String args[]) {
		System.out.println(new PermutationSequence().getPermutation(3, 5));
	}
}
