package easy;

import java.util.Arrays;

/**
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 Example 1:
 Input: A = [34,23,1,24,75,33,54,8], K = 60
 Output: 58
 Explanation:
 We can use 34 and 24 to sum 58 which is less than 60.
 Example 2:
 Input: A = [10,20,30], K = 15
 Output: -1
 Explanation:
 In this case it's not possible to get a pair sum less that 15.
 Note:
 1 <= A.length <= 100
 1 <= A[i] <= 1000
 1 <= K <= 2000
 2019/06/29
 */
public class TwoSumLessThanK {
    /**
     * brute force
     */
    public int twoSumLessThanK(int[] A, int K) {
        int res = -1;
        Arrays.sort(A);
        for(int i = 0 ; i < A.length; i ++){
            if(A[i] >= K) continue;
            for(int j = i + 1; j < A.length ; j ++){
                if(A[i] + A[j]>= K) continue;
                res = Math.max(res, A[i] + A[j]);
            }
        }
        return res;
    }
}
