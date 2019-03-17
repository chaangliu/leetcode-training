package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * 20190313
 */
public class FindAllAnagramsinAString {

    /**
     * 两个map的解法；
     * left,right两个指针都从0开始，right不断右移，map维护left~right中字母出现次数，直到目标字母数量匹配，left开始右移；时间O(n)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) return res;
        Map<Character, Integer> ref = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            ref.put(p.charAt(i), ref.getOrDefault(p.charAt(i), 0) + 1);
        }
        int left = 0, right = 0, match = 0;
        while (right < s.length()) {
            char rChar = s.charAt(right);
            window.put(rChar, window.getOrDefault(rChar, 0) + 1);
            if (window.get(rChar).equals(ref.get(rChar))) match++;
            right++;//expand
            while (match == ref.size()) {//not p.length()
                if (right - left == p.length()) res.add(left);
                char lChar = s.charAt(left);//char to be expelled
                window.put(lChar, window.get(lChar) - 1);
                if (ref.getOrDefault(lChar, 0) > (window.get(lChar))) { //if condition can't be ref.containsKey(lChar)
                    match--;
                }
                left++;//shrink
            }
        }
        return res;
    }


    /**
     * 单Map + 计数器的解法；有点难理解
     */
    public List<Integer> findAnagrams____(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length()) return result;
        //Map里保存的key是p中字母，value是窗口中p中字母对应于s窗口中对应字母出现次数的差。比如s=aaabc,p=ac,那r=3时Map中是{a:-2}
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        int counter = map.size(), begin = 0, end = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);//右边进来的数在Map里存在，就减去一个出现次数，表示还需要寻找的当前字母的数量减1
                if (map.get(c) == 0) counter--;//右边进来的数导致当前字母的数量足够了，count--；count代表与p的指定字母相比left~right中数量不一致的字母的个数（仅统计p中存在的字母）
            }
            end++;
            while (counter == 0) {
                if (end - begin == p.length()) result.add(begin);
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);//窗口中指定字母数量-1
                    if (map.get(tempc) > 0) {//窗口中的字母数量不足了，stop shrinking, start expanding
                        counter++;
                    }
                }
                begin++;
            }
        }
        return result;
    }

    /**
     * 数组解法。。不过这个解法被很多人喷了，因为用了太多的++--在同一行里，看似简洁但是非常难读。不适合当场写，所以我觉得用Map挺好的
     */
    public List<Integer> findAnagrams___(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256];
        for (char c : p.toCharArray()) hash[c]++;
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            if (hash[s.charAt(right++)]-- >= 1) count--;//如果右边进来的数在p里存在，count--；count代表与p相比left~right中缺少的字母的个数
            right++;
            if (count == 0) list.add(left);
            //只当length == p的长度时左边才右移
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)//如果左边即将出去的数在hash里面，说明差异度即将变大，count(缺少的字母个数)又要多一个了
                count++;
        }
        return list;
    }


    public static void main(String args[]) {
        List res = new FindAllAnagramsinAString().findAnagrams____("cbaebabacd", "abc");
        System.out.print(res);
    }
}
