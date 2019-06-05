package dp;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
 * <p>
 * A student attendance record is a string that only contains the following three characters:
 * <p>
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * <p>
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 * Note: The value of n won't exceed 100,000.
 * 20190605
 */
public class StudentAttendanceRecordII {
    /**
     * 这个SOLUTION来自DISCUSS，涉及大量推倒，属实是一道逻辑很强的代数题。
     * T代表长度为n时总共有多少种可能被reward的情况，那么有：
     * T(n) = P(n) + L(n) + A(n)，P/L/A分别代表以P/L/A结尾的个数。
     * A(n)比较难求，最后涉及一个略复杂的推倒。
     */
    public int checkRecord(int n) {
        if (n == 1) return 3;
        int m = (int) 1e9 + 7;
        int A[] = new int[n];
        int P[] = new int[n];
        int L[] = new int[n];
        P[0] = 1;
        L[0] = 1;
        A[0] = 1;
        L[1] = 3;
        A[1] = 2;
        A[2] = 4;
        for (int i = 1; i < n; i++) {
            A[i - 1] %= m;
            P[i - 1] %= m;
            L[i - 1] %= m;
            // 最终方程：
            // P(n) = A(n - 1) + P(n - 1) + L(n - 1), n ≥ 2.
            // L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2), n ≥ 3.
            // A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.
            P[i] = ((A[i - 1] + P[i - 1]) % m + L[i - 1]) % m;
            if (i > 1) L[i] = ((A[i - 1] + P[i - 1]) % m + (A[i - 2] + P[i - 2]) % m) % m;
            if (i > 2) A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;
        }
        return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;
    }
}
