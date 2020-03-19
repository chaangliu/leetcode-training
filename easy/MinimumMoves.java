package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DrunkPiano on 19/07/2017.
 */

public class MinimumMoves {
	public int minMoves(int[] nums) {
		int count = 0;
		while (true) {
			int i;
			for (i = 1; i < nums.length; i++) {
				if (nums[i] != nums[i - 1]) {
					break;
				}
			}
			if (i == nums.length) {
				return count;
			} else {
				Arrays.sort(nums);
				for (int j = 0; j < nums.length - 1; j++) {
					nums[j]++;
				}
				count++;
			}
		}
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		int[] map = new int[26];
		for (int i = 0; i < magazine.length(); i++) {
			map[magazine.charAt(i) - 'a']++;
		}
		for (int i = 0; i < ransomNote.length(); i++) {
			if (ransomNote.charAt(i) - 'a' == 0) {
				return false;
			}
			map[ransomNote.charAt(i) - 'a']--;
		}
		return true;
	}

	//Approach1: 两个hashset，第一个存储nums1中的unique numbers，然后轮询第二个数组
	// 判断第一个是否contains，然后加入一个新的HashSet，Time: O(n2)

	//Approach2: 用一个超大的数组模拟map。。
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> res = new HashSet<>();
		for (Integer num : nums1) {
			set1.add(num);
		}
		for (int num : nums2) {
			if (set1.contains(num)) {
				res.add(num);
			}
		}

		Object[] arr = res.toArray();
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = (int) arr[i];
		}
		return result;
	}

	//先随便找个方法遍历，遍历到某个node的left child不为空但是left child的左右孩子都为空的时候加入到sum里去。
	int sum = 0;

	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) return 0;
		if (root.left != null && root.left.left == null && root.left.right == null) {
			sum += root.left.val;
		}
		sumOfLeftLeaves(root.left);
		sumOfLeftLeaves(root.right);
		return sum;
	}

	//用一个int[26]记录小写字母出现次数，最后遍历一次，返回第一个值是1的index
	public int firstUniqChar(String s) {
		int[] map = new int[26];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if (map[s.charAt(i) - 'a'] == 1) return i;
		}
		return -1;
	}

	//用这么一个公式吧：
	//sum += (s[i] - 'A' + 1) * Math.pow(26 , len - 1 - i)
	public int titleToNumber(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += (s.charAt(i) - 'A' + 1) * Math.pow(26f, (float) (s.length() - 1 - i));
		}
		return sum;
	}

	//辗转相除(不知道是不是这个名词)
	//test case : 26 -> Z ; 27 -> AA
	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		while (n / 26 > 0) {
			int mod = n % 26;
			sb.insert(0, (char) ('A' + mod - 1));
			n /= 26;
		}
		sb.insert(0, (char) ('A' + n - 1));
		return sb.toString();
	}


	//先sort，再遍历，遇到个数大于n了，返回它
	public int majorityElement(int[] nums) {
		if (nums.length == 1) return nums[1];
		Arrays.sort(nums);
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				count = 1;
			} else {
				count++;
			}
			if (count > nums.length / 2) return nums[i];
		}
		return -1;
	}

	//	public int sumOfLeftLeaves(TreeNode root) {
//		if(root == null) return 0;
//		int ans = 0;
//		Queue<TreeNode> stack = new LinkedList<>();
//		stack.offer(root);
//
//		while(!stack.isEmpty()) {
//			TreeNode node = stack.poll();
//			if(node.left != null) {
//				if (node.left.left == null && node.left.right == null)
//					ans += node.left.val;
//				else
//					stack.offer(node.left);
//			}
//			if(node.right != null) {
//				if (node.right.left != null || node.right.right != null)
//					stack.offer(node.right);
//			}
//		}
//		return ans;
//	}
	public boolean isAnagram(String s, String t) {
		//1. 模拟map
		//2. sort？
		int map[] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			map[t.charAt(i) - 'a']--;
		}
		for (Integer num : map) {
			if (num != 0) {
				return false;
			}
		}
		return true;
	}

	public String convertToBase7(int num) {
		int n = Math.abs(num);
		StringBuilder sb = new StringBuilder();
		while (n / 7 > 0) {
			sb.insert(0, n % 7);
			n /= 7;
		}
		sb.insert(0, n);
		if (num < 0) {
			sb.insert(0, "-");
		}
		return sb.toString();
	}

	//求最长回文。统计个个字母出现次数。奇数的保留最大的那个。偶数全保留。这个思路完全错了。
	//因为奇数中成对的字母也是可以拿来用的，不是说用一个字母就一定要全部都用完。
	//正确的思路，计算成对出现的pair数。
	public int longestPalindrome(String s) {
		Set<Character> set = new HashSet<>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!set.contains(s.charAt(i))) {
				set.add(s.charAt(i));
			} else {
				set.remove(s.charAt(i));
				count++;
			}
		}
		if (!set.isEmpty()) {
			return 2 * count + 1;
		} else return 2 * count;
	}


	//0. O(n2)brute force
	//1. sort
	//2. map/set
	public boolean containsDuplicate(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) return true;
		}
		return false;
	}

	//这题我的思路是用一个长度是10的数组，
	//随机地把1洒落在这个这个数组上，然后对前4个做计算，后6个做计算，
	//拼接起来就行了。问题出怎么才能遍历到所有的撒落的可能性。
	//首先我事先不知道n是几，所以不能提前写好n个for。
	//看答案了。反过来的思路：bitCount。计算0:00到11:59里面所有可能的值然后对比bitCount跟num对比，对得上就输出。挺巧妙的，不过这种需要遍历所有的时间，真的好吗。
	//下面用一个别人的方法：
//	public List<String> readBinaryWatch(int num) {
//		List<String> res = new ArrayList<>();
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 60; j++) {
//				if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
//					res.add(String.format("%d:%02d", i, j));
//				}
//			}
//		}
//		return res;
//	}


	public static void main(String args[]) {
		System.out.println(new MinimumMoves().readBinaryWatch(2));
	}

	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<>();
		int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
		for(int i = 0; i <= num; i++) {
			List<Integer> list1 = generateDigit(nums1, i);
			List<Integer> list2 = generateDigit(nums2, num - i);
			for(int num1: list1) {
				if(num1 >= 12) continue;
				for(int num2: list2) {
					if(num2 >= 60) continue;
					res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
				}
			}
		}
		return res;
	}

	private List<Integer> generateDigit(int[] nums, int count) {
		List<Integer> res = new ArrayList<>();
		generateDigitHelper(nums, count, 0, 0, res);
		return res;
	}

	private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
		if(count == 0) {
			res.add(sum);
			return;
		}

		for(int i = pos; i < nums.length; i++) {
			generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
		}
	}
}
