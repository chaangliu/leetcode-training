package array;

/**
 * Created by DrunkPiano on 05/07/2017.
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * Return true.
 */

public class IsSubsequence {
	public boolean isSubsequence(String s, String t) {
		if (s == null || s.length() == 0) return true;
		int nextIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			int j = nextIndex;
			while (j < t.length()) {
				if (s.charAt(i) == t.charAt(j)) {
					if (i == s.length() - 1) {
						return true;
					}
					nextIndex = j + 1;
					break;
				}
				j++;
			}
			//一旦s中的letter不满足就停止
			if (j == t.length()) {
				return false;
			}
		}
		return false;
	}


//	public boolean isSubsequence(String s, String t) {
//		if (s == null || s.length() == 0) return true;
//		for (int i = 0; i < s.length(); i++) {
//
//		}
//
//
//	}
//
//	private int binarySearch(int start, int end, char c, String t) {
//		if (start > end) {
//			return -1;
//		}
//		int mid =  (start + end) / 2;
//		if (t.charAt(mid) == c) {
//			return mid;
//		}
//		if (t)
//	}

}
