package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * For example:
 * Given "25525511135",
 * <p>
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * <p>
 * <p>
 * Created by DrunkPiano on 01/06/2017.
 */

public class RestoreIPAddresses {
//	public List<String> restoreIpAddresses(String s) {
//		int len = s.length();
//		List<String> res = new ArrayList<>();
//		if (len < 4 || len > 12) return res;
////		for (int i = 1; i < len - 2; i++)
////			for (int j = i + 1; j < len - 1; j++)
////				for (int k = j + 1; k < len; k++) {
////		加上j < i + 4这个条件可以少循环很多次
//		for (int i = 1; i < 4 && i < len - 2; i++)
//			for (int j = i + 1; j < i + 4 && j < len - 1; j++)
//				for (int k = j + 1; k < j + 4 && k < len; k++) {
//					if (isValid(s.substring(0, i))
//							&& isValid(s.substring(i, j))
//							&& isValid(s.substring(j, k))
//							&& isValid(s.substring(k, len))) {
//						String seg = s.substring(0, i) + "." + s.substring(i, j) + "." + s.substring(j, k) + "." + s.substring(k, len);
//						res.add(seg);
//					}
//				}
//		return res;
//	}
//
//	private boolean isValid(String s) {
//		if (s.length() > 3) return false;
//		if (s.length() > 1 && s.charAt(0) == '0') return false;
//		return Integer.parseInt(s) <= 255;
//	}
//
//	public List<String> restoreIpAddresses(String s) {
//		int len = s.length();
//		List<String> res = new ArrayList<>();
//		if (len < 4 || len > 12) return res;
//		dfs(s, res, 0, 0, "");
//		return res;
//	}
//
//	private void dfs(String s, List<String> res, int count, int idx, String ans) {
//		if (count == 4 && idx == s.length() - 1) {
//			res.add(ans);
//			return;
//		}
//		if (count == 4 && idx != s.length() - 1)
//			return;
//		for (int i = idx; i < s.length(); i++) {
//			if (idx > s.length()) break;
//				String sub = s.substring(i, idx + 1);
//			if (!isValid(sub)) continue;
//			dfs(s, res, ++count, ans.length() + sub.length() - count, ans + (count < 4 ? sub + "." : sub));
//			System.out.print(ans);
//		}
//
//	}

	private boolean isValid(String s) {
		if (s.length() == 0 || s.length() > 3) return false;
		if (s.length() > 1 && s.charAt(0) == '0') return false;
		return Integer.parseInt(s) <= 255;
	}

	public static void main(String args[]) {
		new RestoreIPAddresses().restoreIpAddresses("010010");
	}


	public List<String> restoreIpAddresses(String s) {
		List<String> solutions = new ArrayList<String>();
		restoreIp(s, solutions, 0, "", 0);
		return solutions;
	}

	private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
		if (count > 4) return;
		if (count == 4 && idx == ip.length()) solutions.add(restored);

		for (int i = 1; i < 4; i++) {
			if (idx + i > ip.length()) break;
			String s = ip.substring(idx, idx + i);
			if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256)) continue;
			restoreIp(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1);
		}
	}
}
