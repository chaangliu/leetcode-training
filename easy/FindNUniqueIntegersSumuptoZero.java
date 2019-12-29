package easy;

/**
 * 5295. Find N Unique Integers Sum up to Zero
 * User Accepted:405
 * User Tried:673
 * Total Accepted:406
 * Total Submissions:716
 * Difficulty:Easy
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 * Example 1:
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * Example 2:
 * Input: n = 3
 * Output: [-1,0,1]
 * Example 3:
 * Input: n = 1
 * Output: [0]
 * Constraints:
 * 1 <= n <= 1000
 * 20191229
 */
public class FindNUniqueIntegersSumuptoZero {
    /**
     * 题意：随意返回和是0的n个数字。
     * 我写了个平衡数组，其实也可以n-1个数加到sum，最后加上一个-sum。
     */
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int j = 0;
        for (int i = n / 2; i > 0; i--) {
            res[j++] = -i;
        }
        for (int i = n / 2; i > 0; i--) {
            res[j++] = i;
        }
        if ((n & 1) == 1) res[j] = 0;
        return res;
    }

    public int[] sumZero_(int n) {
        int arr[] = new int[n];
        int sum = 0;
        for(int i = 0; i < n-1; i++) {
            arr[i] = i+1;
            sum += arr[i];
        }
        arr[n-1] = -sum;
        return arr;
    }
}
