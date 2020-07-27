package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DrunkPiano on 05/07/2017.
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * Return true.
 */

public class IsSubsequence {
    /**
     * 题意：判断s是否是t的子序列
     * 解法：2 pointers
     * follow up: 多个s怎么办？ binary search
     */
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


    /**
     * follow up，如果有很多个incoming的s的解法
     * 思路，可以避免每次都扫描s，而是采取把s中的字母保存到类似Map中去匹配
     * binary search
     */
    public boolean isSubsequence__(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity
        //模拟HashMap，生成256个buckets
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)//把t的每一位字母用拉链法hash到bucket里，保存的是字母对应的index
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }

        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i) : s中的每个字母
            char si = s.charAt(i);
            //如果t中没有s返回false
            if (idx[si] == null) return false; // Note: char of S does NOT exist in T causing NPE
            //在t中相同字母的index列表中寻找prev，如果没找到，说明prev代表的index在t上无法匹配，那么返回在t中最早出现的位置即可
            int j = lower_bound(idx[si], prev); // 在当前字母indices列表中找一个最早出现于上一个字母之后的
            if (j == idx[si].size()) {
                return false;
            }
            prev = idx[si].get(j) + 1;
        }
        return true;
    }

    private int lower_bound(List<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) < target)
                lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
