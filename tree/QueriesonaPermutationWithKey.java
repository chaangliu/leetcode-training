package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to i=queries.length-1) according to the following rules:
 * <p>
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
 * Return an array containing the result for the given queries.
 * Example 1:
 * <p>
 * Input: queries = [3,1,2,1], m = 5
 * Output: [2,1,2,1]
 * Explanation: The queries are processed as follow:
 * For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is 2, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5].
 * For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5].
 * For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is 2, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5].
 * For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5].
 * Therefore, the array containing the result is [2,1,2,1].
 * Example 2:
 * <p>
 * Input: queries = [4,1,2,2], m = 4
 * Output: [3,1,2,0]
 * Example 3:
 * <p>
 * Input: queries = [7,5,5,8,3], m = 8
 * Output: [6,5,0,7,5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m <= 10^3
 * 1 <= queries.length <= m
 * 1 <= queries[i] <= m
 * 20200412
 */
public class QueriesonaPermutationWithKey {
    /**
     * 题意：对于query数组里的每个数字，把它移动到[1...m]中的最前面。
     * 解法：写了个很慢的stack来模拟，其实就是brute force，结果还调试了好久。。其实只要list就行了。好的解法是fenwick tree
     */
    public int[] processQueries(int[] queries, int m) {
        int[] res = new int[queries.length];
        int idx = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = m; i >= 1; i--) s.push(i);
        for (int q : queries) {
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i) == q) {
                    res[idx++] = m - 1 - i;
                    int t = s.remove(i);
                    s.push(t);
                    break;
                }
            }
        }
        return res;
    }

    public int[] processQueries_(int[] queries, int m) {
        LinkedList<Integer> P = new LinkedList<>();
        for (int i = 1; i <= m; i++) P.add(i);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int idx = P.indexOf(q);
            int val = P.get(idx);
            P.remove(idx);
            P.addFirst(val);
            res[i] = idx;
        }
        return res;
    }
}
