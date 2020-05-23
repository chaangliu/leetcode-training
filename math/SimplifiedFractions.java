package math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n. The fractions can be in any order.
 * Example 1:
 * Input: n = 2
 * Output: ["1/2"]
 * Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: ["1/2","1/3","2/3"]
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: ["1/2","1/3","1/4","2/3","3/4"]
 * Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
 * Example 4:
 * <p>
 * Input: n = 1
 * Output: []
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 2020/05
 */
public class SimplifiedFractions {
    /**
     * 题意：给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
     * 解法：关键就是要判断两个数是否是可以约分；这个可以用gcd是否==1来判断。
     */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int denominator = 2; denominator <= n; ++denominator) { // 分母
            for (int numerator = 1; numerator < denominator; ++numerator) { // 分子
                if (gcd(numerator, denominator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    private int gcd(int x, int y) { // 题目中x肯定小于y，辗转相除法
        return x == 0 ? y : gcd(y % x, x);
    }
}
