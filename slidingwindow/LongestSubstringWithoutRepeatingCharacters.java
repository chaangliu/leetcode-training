package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by DrunkPiano on 2017/3/5.
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring____Set(String s) {
        if (s.length() == 0) return 0;
        int runner = 0, walker = 0;
        int maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        while (runner < s.length()) {
            if (!set.contains(s.charAt(runner))) {
                set.add(s.charAt(runner));
                runner++;
            } else {
                if (runner - walker > maxLen) maxLen = runner - walker;
                while (s.charAt(runner) != s.charAt(walker)) {
                    set.remove(s.charAt(walker));
                    walker++;
                }
//                分别再走一步
                walker++;
                runner++;
            }
        }
        return Math.max(maxLen, runner - walker);
    }


    //20190124review hashMap
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int runner = 0, walker = 0; runner < s.length(); runner++) {
            if (map.containsKey(s.charAt(runner))) {
                walker = Math.max(walker, map.get(s.charAt(runner)));//这里要取walker和已有数字的index里面大的那个，因为walker可能在已有数字index后面了(比如abba)，而index~walker之间已经有重复了
            }
            res = Math.max(runner - walker + 1, res);
            map.put(s.charAt(runner), runner + 1);
        }
        return res;
    }

    public static void main(String args[]) {
        LongestSubstringWithoutRepeatingCharacters lis = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(lis.lengthOfLongestSubstring("abba"));
    }
}
