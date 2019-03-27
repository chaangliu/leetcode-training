package slidingwindow;

import java.util.HashMap;

/**
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 * <p>
 * Note:
 * Both the string's length and k will not exceed 104.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "ABAB", k = 2
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input:
 * s = "AABABBA", k = 1
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * <p>
 * 20190327
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * 这题我一开始被example误导，理解错了，以为只能替换同一种字母；实际可以替换多个不同字母为1个
     * <p>
     * 记录window中出现次数最多的数Target，把其他的数都替换成它；
     * while (right - left + 1 - targetCount > k) -> left ++; else right++;
     * 维护出现次数最多的数的出现次数maxCharCount，这样就不用在shrink或者expand的时候记录新的target了！因为shrink的时候maxCharCount不会变化
     */
    public int characterReplacement(String s, int k) {
        int res = 0, left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) return 0;
        int maxCharCount = 0;
        while (right < s.length()) {
            char rChar = s.charAt(right);
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            if (map.get(rChar) > maxCharCount) {
                maxCharCount = map.get(rChar);
            }
            while (left < right && right - left + 1 - maxCharCount > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 如果维护出现次数最多的target，是需要寻找新的target的
     */
    public int characterReplacement_____(String s, int k) {
        int res = 0, left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) return 0;
        char target = s.charAt(0);
        while (right < s.length()) {
            char rChar = s.charAt(right);
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            if (map.get(rChar) > map.get(target)) {
                target = rChar;
            }
            while (left < right && right - left + 1 - map.get(target) > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                for (int i = 0; i < 26; i++) {
                    char c = (char) ('A' + i);
                    if (map.get(target) < map.getOrDefault(c, 0)) {
                        target = c;
                    }
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

//    public int characterReplacement(String s, int k) {
//        int res = 0, left = 0, right = 0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        if (s == null || s.length() == 0) return 0;
//        char target = s.charAt(0);
//        char viceTarget = target;
//        while (right < s.length()) {
//            char rChar = s.charAt(right);
//            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
//            if (map.get(rChar) > map.get(target)) {
//                viceTarget = target;
//                target = rChar;
//            }
//            while (left < right && right - left + 1 - map.get(target) > k) {
//                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
//                if (map.get(target) < map.get(viceTarget)) {
//                    char tmp = target;
//                    target = viceTarget;
//                    viceTarget = tmp;
//                }
//                left++;
//            }
//            res = Math.max(res, right - left + 1);
//            right++;
//        }
//        return res;
//    }
//    public int characterReplacement(String s, int k) {
//        int res = 0, left = 0, right = 0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        int count = 0;//Map中记录的出现次数都大于2的数的个数（关键，有了它就不用遍历map）
//        int diversity = 0;
//        while (right < s.length()) {
//            char lChar = s.charAt(left);
//            char rChar = s.charAt(right);
//            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
//            if (map.get(rChar) == 1)
//                diversity++;
//            if (map.get(rChar) == k + 1) count++;
//            while (left < right && (count > 1 || diversity > 2)) {
//                map.put(lChar, map.get(lChar) - 1);
//                if (map.get(lChar) <= k)
//                    count--;
//                if (map.get(lChar) == 0)
//                    diversity--;
//                left++;
//            }
//            res = Math.max(res, right - left + 1);
//            right++;
//        }
//        return res;
//    }

}
