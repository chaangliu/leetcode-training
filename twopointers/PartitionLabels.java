package twopointers;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    /**
     * 题意：给你一个字符串, 让你尽可能多地划分区间，要求同一个字母只能出现在同一个片段。返回每个区间的长度。
     * 解法：先遍历一遍记录每个字母最后出现的位置；再重头遍历，用end记录迄今为止遇到的所有字母最后出现的位置，
     * 最巧妙的就是这儿，如果当前位置和(迄今所有字母)最后出现位置end一样，说明迄今为止可以划分成一个新区间了(贪心)，并且下一个区间的start是end+1。
     **/
    public List<Integer> partitionLabels(String S) {
        int[] lastOf = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastOf[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(lastOf[S.charAt(i) - 'a'], end); // 最巧妙的就是这儿, end记录迄今为止所有字母出现的最右位置
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}
