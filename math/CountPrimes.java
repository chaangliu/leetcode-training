package math;

import java.util.Arrays;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * <p>
 * 20190509
 */
public class CountPrimes {
    /**
     * 题意：计算<n的质数的个数。
     * 解法：埃氏筛。直接计算每个数是否是质数肯定效率很低；
     * 这里可以反推，从2开始，一开始假设所有数都是质数，然后从对于每个i * j < n，都把i*j标记成合数。
     * 也就是每个质数，由2开始向后fill合数，所以没有被涉及到的数都是质数。
     * 优化的方法是j 从i * i开始筛，因为小于i的因子前面都筛过了。
     * 2(false) 4,6,8..(true)
     * 3(false) 9,12..(true)
     * 4(true)
     */
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) { // j += i, not *
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}
