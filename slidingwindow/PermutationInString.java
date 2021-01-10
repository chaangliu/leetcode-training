package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Created by DrunkPiano on 2017/5/1.
 */

public class PermutationInString {
    /**
     * 题意：给你两个字符串，判断s1的某个permutation是否是s2的substring。
     * 解法：滑动窗口。map记录s1中的字符在s2的窗口中每个字符出现的次数，正数代表还差多少个，负数代表多出多少个。
     */
    public static boolean checkInclusion(String s1, String s2) {
        char[] chars = s1.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        chars = s2.toCharArray();
        int l = 0, r = 0;
        while (r < s2.length()) {
            char c = chars[r++];
            map.computeIfPresent(c, (k, v) -> v - 1); // expand
            while (l < r && (!map.containsKey(c) || map.get(c) < 0)) { // s1中不包含或者多余s1中的数量
                map.computeIfPresent(chars[l++], (k, v) -> v + 1); // shrink
            }
            if (r - l == s1.length()) return true;
        }
        return false;
    }


    String sss;

    public boolean checkInclusion_(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        if (s2 == null) return false;
        sss = s2;
        boolean[] used = new boolean[s1.length()];
        String s = "";
        return dfs(s, s1, used);
    }

    private boolean dfs(String s, String s1, boolean[] used) {
        if (s.length() == s1.length()) {
            if (isSubstring(s)) return true;
            else return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                if (dfs(s + s1.charAt(i), s1, used)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    private boolean isSubstring(String s1) {
        for (int i = 0; i < sss.length() - s1.length() + 1; i++) {
            if (sss.substring(i, i + s1.length()).equals(s1)) return true;
        }
        return false;
    }
}
