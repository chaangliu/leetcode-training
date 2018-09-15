package array;

import java.util.HashSet;

/**
 * Created by DrunkPiano on 2017/3/5.
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
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
        return Math.max(maxLen,runner-walker);

    }

    public static void main(String args[]) {
        LongestSubstringWithoutRepeatingCharacters lis = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(lis.lengthOfLongestSubstring("pwwkew"));
    }
}
