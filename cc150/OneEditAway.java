package cc150;

/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 */
public class OneEditAway {
    /**
     * 题意：判断两个字符串的edit distance是否<=1
     * 解法：可以直接用Edit distance那题的DP，也可以用双指针。
     * 双指针可参考：https://leetcode-cn.com/problems/one-away-lcci/solution/zi-fu-chuan-bi-jiao-by-luorong/
     */
    public boolean oneEditAway(String first, String second) {
        if (first == null || second == null) return false;
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) return false;
        if (len2 > len1) return oneEditAway(second, first);

        // 保持第一个比第二个长
        for (int i = 0; i < len2; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                // 如果是长度相同字符串，那就比较下一个，如果长度不一样，那就从该字符开始进行比较。
                return first.substring(i + 1).equals(second.substring(len1 == len2 ? i + 1 : i));
            }
        }
        return true;
    }
}
