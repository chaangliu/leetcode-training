package math;

import java.math.BigInteger;

/**
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
 * <p>
 * (Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)
 * <p>
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: 12
 * Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
 * Example 2:
 * <p>
 * Input: n = 100
 * Output: 682289015
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 20190901
 */
public class PrimeArrangements {
    long MOD = 1000000007L;

    /**
     * 这题我想到计算出质数个数p，结果就等于p! * (n - p)!
     * 但是分别计算出p! 和 (n - p)!之后已经得到了一个非常大的数，【大数相乘】超出了long的范围。
     * 这里有两种解决方案，1是用Java.math.BigInteger，这也是ACM选手常常提到的Java的优势，拥有解决大数相乘的快捷方式；这个类的取值范围没有上限，取决于你的电脑内存；
     * 二是不用分别把两者都计算出来，而是在原来的基础上累乘，每次乘完了就MOD一次，确保不溢出。
     */
    public int numPrimeArrangements(int n) {
        int count = 0;//统计质数的总数
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) count++;
        }
        BigInteger res1 = factorial(count);
        BigInteger res2 = factorial(n - count);
        return res1.multiply(res2).mod(BigInteger.valueOf(MOD)).intValue();
    }

    BigInteger factorial(int n) {
        BigInteger fac = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac.mod(BigInteger.valueOf(MOD));
    }

    /**
     * 在原来基础上乘
     */
    public int numPrimeArrangements_(int n) {
        int cnt = 1; // # of primes.
        outer:
        for (int i = 3; i <= n; i += 2) {
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    continue outer;
                }
            }
            ++cnt;
        }
        long ans = 1;
        for (int i = 1; i <= cnt; ++i) { // (# of primes)!
            ans = ans * i % 1_000_000_007;
        }
        for (int i = 1; i <= n - cnt; ++i) { // (# of non-primes)!
            ans = ans * i % 1_000_000_007;
        }
        return (int) ans;
    }

    /**
     * --------------计算质数(prime number)的三种方法--------------
     * https://blog.csdn.net/afei__/article/details/80638460
     */
    public static boolean isPrime_1(int n) {
        if (n <= 3) {
            return n > 1;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 假如n是合数，必然存在非1的两个约数p1和p2，其中p1<=sqrt(n)，p2>=sqrt(n)
     */
    public static boolean isPrime_2(int n) {
        if (n <= 3) {
            return n > 1;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 质数还有一个特点，它的必要条件是：总是等于 6x-1 或者 6x+1，其中 x 是大于等于1的自然数，因为6x + 2, 6x + 3, 6x + 4都不是质数
     * 但这不是充分条件，比如25 = 6 * 4 + 1，但是是合数。
     * 所以要进一步判断，初始位置5，步长为6（只要判断有没有质因子）。
     */
    boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
