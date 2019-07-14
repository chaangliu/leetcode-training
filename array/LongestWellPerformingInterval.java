package array;

import java.util.HashMap;
import java.util.Map;

/**
 * We are given hours, a list of the number of hours worked per day for a given employee.
 * <p>
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 * <p>
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 * <p>
 * Return the length of the longest well-performing interval.
 * Example 1:
 * <p>
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 * Constraints:
 * <p>
 * 1 <= hours.length <= 10000
 * 0 <= hours[i] <= 16
 * 20190714
 */
public class LongestWellPerformingInterval {
    /**
     * O(n)做法：
     * 把所有>8的数字看成1，<=8的数字看成0，那么这题可以转化为求最长1的数量大于0的数量的的连续子序列。
     */
    public int longestWPI(int[] hours) {
        int res = 0, score = 0, n = hours.length;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            score += hours[i] > 8 ? 1 : -1;//score代表从开始到现在的合格天数减去不合格天数
            if (score > 0) {
                res = i + 1;
            } else {
                seen.putIfAbsent(score, i);//对于score:..-2,-3,-4,-3,-2这样的，后面的index无需放入，这样可以保证最长
                if (seen.containsKey(score - 1))//对于第二个-3，从-4开始的那天开始这段时间是合格的
                    res = Math.max(res, i - seen.get(score - 1));
            }
        }
        return res;
    }

    /**
     * O(n2)做法, n<=10000，所以能通过
     * 对于每段[i,j]，[0,j]合格的天数-[0,i]合格的天数 = [i,j]期间合格的天数; 不合格天数就是i - j - 合格天数；如果合格天数>不合格天数，就更新max的值。
     */
    public int longestWPI_ON2(int[] hours) {
        int n = hours.length;
        int[] memo = new int[n + 1];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (hours[i] > 8) {
                count++;
            }
            memo[i + 1] = count;
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (memo[i] - memo[j] > (i - j) - (memo[i] - memo[j])) {
                    max = Math.max(i - j, max);
                }
            }
        }
        return max;
    }
}
