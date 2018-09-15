package array;

/**
 * Created by DrunkPiano on 17/05/2017.
 */

public class LongestCommonPrefix {
//	public String longestCommonPrefix(String[] strs) {
//		if (strs == null || strs.length == 0) return "";
//		if (strs.length == 1) return strs[0];
//		int minLen = Integer.MAX_VALUE;
//		for (String s : strs) {
//			minLen = Math.min(s.length(), minLen);
//		}
//		if (minLen == 0) return "";
//		int end = 1;
//		for (; end <= minLen; end++) {
//			for (String s : strs) {
//				String temp = strs[0].substring(0, end);
//				if (!s.substring(0, end).equals(temp))
//					return strs[0].substring(0, end - 1);
//			}
//		}
//		int  s = strs[0].indexOf("a");
//
//		return strs[0].substring(0, end-1);
//
//	}

	//	divide and conquer
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix(strs, l, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}

	public static void main(String args[]) {
//		String[] s = {"acff", "aca", "accb", "ac"};
//		System.out.println(new LongestCommonPrefix().longestCommonPrefix(s));
		String s = "轻质防滑粉";
		System.out.println(s.length());
		System.out.println(s.substring(0,2));
	}
}
