package array;

/**
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Created by DrunkPiano on 2017/3/15.
 */

public class ImplementstrStr {
    /**
     * 题意：大海捞针；实现c的strStr或者java的indexOf
     * 解法：O(mn)遍历
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}

