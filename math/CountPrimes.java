package math;

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
     * 解法：直接计算每个数是否是质数肯定效率很低；
     * 这里可以反推，从2开始，一开始假设所有数都是质数，然后从对于每个i * j < n，都把i*j标记成合数。
     * 也就是每个质数，由2开始向后fill合数，所以没有被涉及到的数都是质数。
     * 2(false) 4,6,8..(true)
     * 3(false) 9,12..(true)
     * 4(true)
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                //for (int j = 2 ; i * j < n && i * j>= 0; j ++){
                //优化：j = i。但这里要写成 i <= (n-1)/j,否则 i * j 可能超出int范围，但是j = 2 那种写法没事，因为之前已经覆盖到?
                for (int j = i; i <= (n - 1) / j; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }
}
