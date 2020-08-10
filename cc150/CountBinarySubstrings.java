package cc150;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 */
public class CountBinarySubstrings {
    /**
     * 题意：给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，条件：子字符串中的所有0和所有1都是组合在一起的。
     * 一开始想用slidingwindow但是发现不好操作。然后试了下找连续1的数量，然后往两边找0，发现写起来各种WA。
     * 正确做法只要One Pass即可，每次遍历完一个区域，查看上一个区域的连续1或者0的数量，取min加到res即可。
     */
    public int countBinarySubstrings(String s) {
        int beforeCnt = 0;
        int cnt = 1;
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
            } else {
                res = res + Math.min(beforeCnt, cnt);
                beforeCnt = cnt;
                cnt = 1;
            }
        }
        res = res + Math.min(beforeCnt, cnt);
        return res;
    }
}
