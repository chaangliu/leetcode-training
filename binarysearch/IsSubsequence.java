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
     */
    public boolean isSubsequence__BINARYSEARCH(String s, String t) {
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
            //之所以用二分查找，是因为t对于list里面有些index是不可用的，必须取prev后面的数。比如s=ab, t=bab，那么t中第0个b就不能用，prev保证了b中最早的那个，跟two pointers异曲同工
            int j = Collections.binarySearch(idx[si], prev);
            if (j < 0) j = -j - 1;//如果没找到prev，会返回-(insertion point) - 1，于是-(-(insertion point) - 1)-1可以恢复j；insertion point代表应该插入的位置，也就是比它大的那个数前面1个数的index
            if (j == idx[si].size()) return false;
            prev = idx[si].get(j) + 1;
        }
        return true;
    }

    public static void main(String args[]) {
        new IsSubsequence().isSubsequence__BINARYSEARCH("abc", "xabcbbbbbbb");
//        new IsSubsequence().isSubsequence__BINARYSEARCH("abc", "bahgdcb");
    }
}
