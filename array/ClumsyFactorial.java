package array;

/**
 * 1006. Clumsy Factorial
 * Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.  For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.
 * <p>
 * We instead make a clumsy factorial: using the integers in decreasing order, we swap out the multiply operations for a fixed rotation of operations: multiply (*), divide (/), add (+) and subtract (-) in this order.
 * <p>
 * For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.  However, these operations are still applied using the usual order of operations of arithmetic: we do all multiplication and division steps before any addition or subtraction steps, and multiplication and division steps are processed left to right.
 * <p>
 * Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.  This guarantees the result is an integer.
 * <p>
 * Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 7
 * Explanation: 7 = 4 * 3 / 2 + 1
 * Example 2:
 * <p>
 * Input: 10
 * Output: 12
 * Explanation: 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 * <p>
 * 20190310contest
 */
public class ClumsyFactorial {

    //4个一组操作
    //(用时:35min)
    public int clumsy(int N) {
        if (N <= 0) return 0;
        int res = 999;
        boolean first = true;
        int stashedSum = 0;
        int round = N / 4;
        for (int i = 0; i < round; i++) {
            int muldivide = N * (N - 1) / (N - 2);
            stashedSum += (N - 3);
            if (first) {
                res = muldivide;
                first = false;
            } else {
                res -= muldivide;
            }
            N -= 4;
        }
        int remain;
        if (N == 3) {
            remain = N * (N - 1) / (N - 2);
        } else if (N == 2) {
            remain = N * (N - 1);
        } else {
            remain = N;
        }
        return res == 999 ? remain : res + stashedSum - remain;
    }
}
