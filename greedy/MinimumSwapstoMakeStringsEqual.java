package greedy;

/**
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].
 * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 * Example 1:
 * Input: s1 = "xx", s2 = "yy"
 * Output: 1
 * Explanation:
 * Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
 * Example 2:
 * Input: s1 = "xy", s2 = "yx"
 * Output: 2
 * Explanation:
 * Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
 * Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
 * Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
 * Example 3:
 * Input: s1 = "xx", s2 = "xy"
 * Output: -1
 * Example 4:
 * Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 * Output: 4
 * Constraints:
 * 1 <= s1.length, s2.length <= 1000
 * s1, s2 only contain 'x' or 'y'.
 * 20191103
 */
public class MinimumSwapstoMakeStringsEqual {
    /**
     * 题意：给你两个只含有xy的长度相等的字符串，每次把第一个中的一个字母和第二个中的一个字母交换，问最小交换多少次能让两个字符串相同。
     * 周赛第一题本应是签到题，结果观察了良久没观察出来策略。这种题没什么套路可循，反而挺难的
     * 答案如下，仍然是观察法；
     * - 相同位置字母相同的，跳过
     * - x的总数和y的总数如果有一个不是偶数，无法处理，返回-1
     * - 把xx对应的yy或者把yy对应的xx弄成相同只需要swap一次，所以优先处理
     * - 把xy对应的yx或者把yx对应的xy弄成相同需要两次swap
     * => swaps = x1 / 2 + y1 / 2 + (x1 % 2) * 2
     */
    public int minimumSwap(String s1, String s2) {
        int x1 = 0; // number of 'x' in s1 (skip equal chars at same index)
        int y1 = 0; // number of 'y' in s1 (skip equal chars at same index)
        int x2 = 0; // number of 'x' in s2 (skip equal chars at same index)
        int y2 = 0; // number of 'y' in s2 (skip equal chars at same index)

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == c2) { // skip chars that are equal at the same index in s1 and s2
                continue;
            }
            if (c1 == 'x') {
                x1++;
            } else {
                y1++;
            }
            if (c2 == 'x') {
                x2++;
            } else {
                y2++;
            }
        }

        //x的总数和y的总数都必须是偶数才能交换，否则返回-1
        if ((x1 + x2) % 2 != 0 || (y1 + y2) % 2 != 0) {
            return -1; // if number of 'x' or 'y' is odd, we can not make s1 equals to s2
        }

        //
        int swaps = x1 / 2 + y1 / 2 + (x1 % 2) * 2;
        // Cases to do 1 swap:
        // "xx" => x1 / 2 => how many pairs of 'x' we have ?
        // "yy" => y1 / 2 => how many pairs of 'y' we have ?
        //
        // Cases to do 2 swaps:
        // "xy" or "yx" =>  x1 % 2

        return swaps;
    }
}
