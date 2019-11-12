package dp;

import java.util.HashSet;

/**
 * We have an array A of non-negative integers.
 * For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].
 * Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)
 * Example 1:
 * Input: [0]
 * Output: 1
 * Explanation:
 * There is only one possible result: 0.
 * Example 2:
 * Input: [1,1,2]
 * Output: 3
 * Explanation:
 * The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
 * These yield the results 1, 1, 2, 1, 3, 3.
 * There are 3 unique values, so the answer is 3.
 * Example 3:
 * Input: [1,2,4]
 * Output: 6
 * Explanation:
 * The possible results are 1, 2, 3, 4, 6, and 7.
 * Note:
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9
 * 20191112
 */
public class BitwiseORsofSubarrays {
    /**
     * 题意：给你一个正整数数组，对于它的每个连续子数组中的所有数字做bitwise OR,问结果一共有多少种。
     * brute force怎么做？求出所有的subarray,对每个做bitwise OR,加入到Set里。时间O(n^2)
     * 数据范围是5000，看起来能用O(n^2)? =>试了一下，超时。
     * 所以看一下标准答案，滚动set。
     * 用一个set:prev存储已经计算出来的截止到A[i]的种类数，另一个set:cur保存对于prev里面的每个值进行bitwise OR的结果(因为存在重复，所以prev的size会很小)，执行完一次就把prev加入到结果集。
     * 时间：O(kN)
     * lee证明说，k最大=30，因为10^9最大只有30位。
     * 根据按位或操作的性质，如果把 result(k, j) 和 result(k - 1, j) 都用二进制表示，那么后者将前者二进制表示中的若干个 0 变成了 1。最多只有30个0变成1 => cur Set的size最大就是30。
     * 链接：https://leetcode-cn.com/problems/bitwise-ors-of-subarrays/solution/zi-shu-zu-an-wei-huo-cao-zuo-by-leetcode/
     **/
    public int subarrayBitwiseORs(int[] A) {
        HashSet<Integer> res = new HashSet<>(), prev = new HashSet<>(), cur;
        for (int a : A) {
            cur = new HashSet<>();//注意这里要每次重新初始化，不然会改变prev的值
            cur.add(a);
            for (Integer b : prev) {
                cur.add(a | b);
            }
            res.addAll(cur);
            prev = cur;
        }
        return res.size();
    }
}
