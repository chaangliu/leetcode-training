package math;

/**
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive integers which are divisible by a or b or c.
 * Example 1:
 * <p>
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * Example 2:
 * <p>
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 * Example 3:
 * <p>
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 * Example 4:
 * <p>
 * Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
 * Output: 1999999984
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * It's guaranteed that the result will be in range [1, 2 * 10^9]
 * 20190925
 */
public class UglyNumberIII {
    /**
     * 题意是求第n个能被a or b or c整除的数。
     * 解法：
     * 容斥原理可以一个数字里有多少个不重叠的能被a,b,c整除的数字。
     * a U b U c = a + b + c - ab - ac - bc + abc
     * 利用这个原理我们，进一步可以利用二分搜索哪一个数字里含有n个不重叠的能被a,b,c整除的数字。
     * <p>
     * -------
     * <p>
     * 对了，这个题解里提到的二分搜索的模板我觉得很棒，跟我现在习惯用的一样，也就是花花讲解的，lower_bound,upper_bound的那种思路：https://leetcode.com/problems/ugly-number-iii/discuss/387539/cpp-Binary-Search-with-picture-and-Binary-Search-Template
     * I used to solve the Binary Search problems with this template:
     * <p>
     * while(lo < hi) {
     * int mid = lo + (hi - lo) / 2;
     * if(Special condition passed)(optional):
     * return mid;
     * if(condition passed)
     * hi = mid;
     * else
     * lo = mid + 1;
     * }
     * return lo;
     * The key idea is that the range of searching is monotonic, i.e., If F(a) == true, then for every b > a, F(b) = true. So our goal is to find the leftmost point a that F(a) == true, which can be solved by binary search. In order to find the leftmost point which satisfies the condition, do not break the loop immediately when u find a valid point (There may exist some valid points on the left side). Instead, what u can do is to narrow the searching range, and the lo point will be the answer. In some cases there's no answer and lo will return as the initial hi value, check that case too.
     * Most of binary search problems can be solved by the template above.
     * Try 875
     * 1011
     * 668
     * 719
     * Hope it helps.
     **/
    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 1, r = 2000000000;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (formula(mid, a, b, c) >= n) r = mid;//向lower_bound靠拢
            else l = mid + 1;
        }
        return (int) l;
    }

    /**
     * 求1~n中有多少个不重叠的能被a or b or c整除的数字。
     **/
    private long formula(long n, long a, long b, long c) {
        long ab = lcm(a, b), bc = lcm(b, c), ac = lcm(a, c), abc = lcm(a, bc);
        return n / a + n / b + n / c - n / ab - n / bc - n / ac + n / abc;// n / lcm(a,b)，就是1~n中有几个能被a,b的公倍数整除的数字
    }

    /**
     * greatest common divisor，辗转相除法求最大公约数, c++有stl
     **/
    private long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    /**
     * least common multiple
     **/
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
