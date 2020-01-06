package bitmanipulation;

/**
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.
 * Example 1:
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * Example 2:
 * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * Output: [8,0,4,4]
 * Constraints:
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 * 20200105
 */
public class XORQueriesofaSubarray {
    /**
     * 题意：对于一个数组，求出[i,j]内的数组的XOR的结果。
     * 解法：很容易想到prefix sum，只不过减法变成了XOR。
     * a ^ b ^ c，再 ^ a ，就等于b ^ c。 0 ^ X = X。
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefix = new int[n];
        int[] res = new int[queries.length];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = arr[i] ^ prefix[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int s = q[0], e = q[1];
            res[i] = prefix[e] ^ (s == 0 ? 0 : prefix[s - 1]);//[0,s), 注意s是开区间，这里我纠缠了一会儿
        }
        return res;
    }

    /**
     * lee的代码, inplace替换
     */
    public int[] xorQueries_(int[] A, int[][] queries) {
        int[] res = new int[queries.length], q;
        for (int i = 1; i < A.length; ++i)
            A[i] ^= A[i - 1];
        for (int i = 0; i < queries.length; ++i) {
            q = queries[i];
            res[i] = q[0] > 0 ? A[q[0] - 1] ^ A[q[1]] : A[q[1]];
        }
        return res;
    }
}
