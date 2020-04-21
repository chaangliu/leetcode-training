package dp;

/**
 * Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple “croak” are mixed. Return the minimum number of different frogs to finish all the croak in the given string.
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of valid "croak" return -1.
 * Example 1:
 * <p>
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1
 * Explanation: One frog yelling "croak" twice.
 * Example 2:
 * <p>
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2
 * Explanation: The minimum number of frogs is two.
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 * Example 3:
 * <p>
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from different frogs.
 * Example 4:
 * <p>
 * Input: croakOfFrogs = "croakcroa"
 * Output: -1
 * Constraints:
 * <p>
 * 1 <= croakOfFrogs.length <= 10^5
 * All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
 * 20200421
 */
public class MinimumNumberofFrogsCroaking {
    /**
     * 题意：给你一个字符串代表青蛙叫，每只青蛙叫声是croak，叫完才能叫下一句；问最少有几只青蛙。
     * 意思就是找出最小需要几个线程执行任务；
     * 解法：状态转移；遇到新的c，就需要多一个frog；遇到k，空闲一个frog；遇到c以外的字母，执行前一个字母的线程数量-1
     **/
    public int minNumberOfFrogs(String croakOfFrogs) {
        int frogs = 0, n = croakOfFrogs.length();
        int[] dp = new int[n]; // dp[i]表示执行字母s[i]的线程数量
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = croakOfFrogs.charAt(i);
            int idx = "croak".indexOf(c);
            dp[idx]++;
            if (idx == 0) {
                frogs++;
                res = Math.max(res, frogs);
            } else {
                if (--dp[idx - 1] < 0) return -1;
                if (idx == 4) {
                    frogs--;
                }
            }
        }
        return frogs == 0 ? res : -1;// for case "croakcroa"
    }
}
