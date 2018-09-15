package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrunkPiano on 13/07/2017.
 */

public class RelativeRanks {
	//O(nlogn)
	public String[] findRelativeRanks(int[] nums) {
		int[] arr = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			arr[i] = nums[i];
		}
		Arrays.sort(arr);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(arr[i], nums.length - i);
		}

		String res[] = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int rank = map.get(nums[i]);
			switch (rank) {
				case 1:
					res[i] = "Gold Medal";
					break;
				case 2:
					res[i] = "Silver Medal";
					break;
				case 3:
					res[i] = "Bronze Medal";
					break;
				default:
					res[i] = rank + "";
			}
		}
		return res;
	}


	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		int i = 0, j = numbers.length - 1;
		while (i < j) {
			if (numbers[i] + numbers[j] == target) {
				res[0] = numbers[i];
				res[1] = numbers[j];
				return res;
			}
			if (numbers[i] + numbers[j] < target) {
				i++;
			} else {
				j--;
			}
		}
		return null;
	}

	//	Input: [1,2], [1,2,3]
	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int count = 0;
		int index = 0;
		for (int i = 0; i < g.length; i++) {
			if (g[i] > s[i]) {
				return count;
			}
			for (int j = index; i < s.length; j++) {
				if (g[i] <= s[j]) {
					count++;
					index = j + 1;
					break;
				}
			}
		}
		return count;
	}
}
