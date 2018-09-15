package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DrunkPiano on 23/07/2017.
 */

public class SetMismatch {
	//	The set S originally contains numbers from 1 to n
	//1. 用一个大小是n的map确定谁出现了两次，谁出现了0次。
	//2. 排序。
	//如果是[1,1]或者[2,2]，答案都应该是[1,2]
	//[1,1,3][2,2,3]答案都应该是[1,2,3]
	//[1,2,3,3,5]
	public int[] findErrorNums(int[] nums) {
//		int res[] = new int[2];
//		Arrays.sort(nums);
//		for (int i = 1; i < nums.length; i++) {
//			if (nums[i] == nums[i - 1]) {
//				if (i == 1) {
//					//res[0]表示重复的数字，res[1]表示重复的那个数字应该是什么
//					res[0] = nums[i];
//					if (nums[i] == 1) {
//						res[1] = 2;
//					} else {
//						res[1] = 1;
//					}
//
//				} else {
//					res[0] = nums[i];
//					res[1] = nums[i] + 1;
//				}
//			}
//		}
//		return res;

		int[] res = new int[2];
		int[] map = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			map[nums[i] - 1]++;
		}
		for (int i = 0; i < nums.length; i++) {
			if (map[i] == 2) {
				res[0] = i + 1;
			}
			if (map[i] == 0) {
				res[1] = i + 1;
			}
		}
		return res;
	}

//	int max = 1;
////dfs
//	public int findLongestChain(int[][] pairs) {
//		if (pairs == null || pairs.length == 0) return 0;
////		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
//		Arrays.sort(pairs, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[0] - o2[0];
//			}
//		});
//		chainDfs(0, pairs, new boolean[pairs.length], 0, Integer.MIN_VALUE);
//		return max;
//	}
//
//	private void chainDfs(int index, int[][] pairs, boolean[] used, int count, int b) {
//		if (index == pairs.length)
//			return;
//		for (int i = index; i < pairs.length; i++) {
//			if (!used[i] && pairs[i][0] > b) {
//				used[i] = true;
//				max = Math.max(count + 1, max);
//				chainDfs(i + 1, pairs, used, count + 1, pairs[i][1]);
//				used[i] = false;
//			}
//		}
//	}

	public int findLongestChain(int[][] pairs) {
		if (pairs == null || pairs.length == 0) return 0;
		Arrays.sort(pairs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		recursion(pairs, 0);
		return cnt;
	}

	int cnt = 1;

	private void recursion(int[][] pairs, int index) {
		int m = index + 1;
		while (m < pairs.length && pairs[index][1] >= pairs[m][0]) {
			m++;
		}
		if (m < pairs.length) {
			cnt++;
			recursion(pairs, m);
		}
	}


	public static void main(String args[]) {
		int[][] s = {{3, 4}, {2, 3}, {1, 2}};
		new SetMismatch().findLongestChain(s);
	}


	public List<List<Integer>> permute(int[] num) {
		List<List<Integer>> res = new ArrayList<>();
		if (num == null || num.length == 0) return res;
		dfsPermute(res, new ArrayList<Integer>(), new boolean[num.length], num);
		return res;
	}

	private void dfsPermute(List<List<Integer>> res, List<Integer> item, boolean[] used, int[] num) {
		if (item.size() == num.length) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (!used[i]) {
				item.add(num[i]);
				used[i] = true;
				dfsPermute(res, item, used, num);
				item.remove(item.size() - 1);
				used[i] = false;
			}
		}
	}


}
