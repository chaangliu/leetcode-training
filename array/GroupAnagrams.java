package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 * Example:
 * <p>
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * Created by DrunkPiano on 24/05/2017.
 * 20200111 --review
 */

public class GroupAnagrams {
    /**
     * 题意：把anagram归类，顺序没关系。
     * 解法：最intuitive的解法就是sort + map，如下。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray(); //先转换为charArray再sort
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);// String.valueOf(char [])
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * O(m*n)做法，借助一个int [26]来编码
     */
    public List<List<String>> groupAnagrams_(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] arr = new int[26];
            for (int i = 0; i < s.length(); i++) {
                arr[s.charAt(i) - 'a']++;
            }
            String key = Arrays.toString(arr);
            List<String> tempList = map.getOrDefault(key, new LinkedList<String>());
            tempList.add(s);
            map.put(key, tempList);
        }
        return new LinkedList<>(map.values());
    }


    /**
     * cc150有同样的题，但要注意如果用map，必须sort key；map的key似乎是不规则排序的，不一定元素相同顺序就相同。
     */
    public List<List<String>> groupAnagrams__(String[] strs) {
        Map<String, ArrayList<String>> res = new HashMap<>();
        for (String s : strs) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            StringBuilder key = new StringBuilder();
            List<Character> keyList = new ArrayList<>(map.keySet());
            Collections.sort(keyList);
            for (char c : keyList) {
                key.append(c).append(map.get(c));
            }
            res.putIfAbsent(key.toString(), new ArrayList<>());
            res.get(key.toString()).add(s);
        }
        return new ArrayList<>(res.values());
    }
}
