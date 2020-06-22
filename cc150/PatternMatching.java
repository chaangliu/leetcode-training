package cc150;

import java.util.HashMap;
import java.util.Map;

/**
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 * 20200622
 */
public class PatternMatching {
    /**
     * 题意：给你pattern和一个value，让你判断pattern能否匹配value，其中pattern由a和b组成。
     * 解法：我的想法就是枚举a代表的长度，这样可以根据出现次数计算b的长度。构造一下，再比对一下就行了。不过这题的corner case特别多。
     */
    public boolean patternMatching(String pattern, String value) {
        if (pattern.length() == 0) return value.length() == 0;
        int n = value.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        if (value.length() == 0) return map.size() <= 1; // "a" "" => true, "ab" "" => false;
        for (int aLen = 0; aLen <= n; aLen++) {  // <=, 一个字母匹配整个value
            StringBuilder sb = new StringBuilder();
            int aTotal = aLen * map.getOrDefault('a', 0);
            if (aTotal > n) break;
            int bTimes = map.getOrDefault('b', 0);
            if (bTimes != 0 && (n - aTotal) % bTimes != 0) continue; // ab不能完整匹配value
            int bLen = bTimes == 0 ? 0 : (n - aTotal) / bTimes;
            String aSub = "", bSub = "";
            // char ch = pattern.charAt(0);
            int aIdx = -1, bIdx = -1, idx = 0;
            for (int i = 0; i < pattern.length(); i++) {
                char ch = pattern.charAt(i);
                if (ch == 'a') {
                    if (idx + aLen > n) continue;
                    aSub = value.substring(idx, idx + aLen);
                    idx += aLen;
                } else {
                    if (idx + bLen > n) continue;
                    bSub = value.substring(idx, idx + bLen);
                    idx += bLen;
                }
                if (aSub.length() != 0 && bSub.length() != 0) break;
            }
            if (aSub.equals(bSub)) continue;
            //    System.out.println("aTimes, bTimes, aSub, bSub are: " + map.getOrDefault('a', 0) + ", " + map.getOrDefault('b', 0) + ", " + aSub + ", " + bSub);
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (c == 'a')
                    sb.append(aSub);
                else
                    sb.append(bSub);
            }
            //    System.out.println("sb is : " + sb.toString());
            if (sb.toString().equals(value)) return true;
        }
        return false;
    }
}
