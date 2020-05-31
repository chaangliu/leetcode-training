package slidingwindow.fixedlength;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个二进制字符串 s 和一个整数 k 。
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
 * 示例 1：
 * 输入：s = "00110110", k = 2
 * 输出：true
 * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
 * 示例 2：
 * <p>
 * 输入：s = "00110", k = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "0110", k = 1
 * 输出：true
 * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
 */
public class CheckIfaStringContainsAllBinaryCodesofSizeK {
    /**
     * 题意：如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False。也就是检查是否包含了所有k位二进制的排列组合。
     * 解法：sliding window + set, 看最后是否set.size == 1 << k
     */
    public boolean hasAllCodes(String s, int k) {
        Set<String> seen = new HashSet<>();
        for (int i = k; i <= s.length(); ++i) {
            seen.add(s.substring(i - k, i));
        }
        return seen.size() == 1 << k;
    }

    /**
     * submitted to contest
     */
    public boolean hasAllCodes_(String s, int k) {
        if (s.length() < (int) Math.pow(2, k)) return false;
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder(s.substring(0, k));
        set.add(sb.toString());
        for (int i = k; i < s.length(); i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            set.add(sb.toString());
        }
        return set.size() == (int) Math.pow(2, k);
    }
}
