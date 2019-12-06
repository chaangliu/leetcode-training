package greedy;

import java.util.HashSet;

/**
 * We are given an array A of N lowercase letter strings, all of the same length.
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
 * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].
 * Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).
 * Return the minimum possible value of D.length.
 * Example 1:
 * <p>
 * Input: ["ca","bb","ac"]
 * Output: 1
 * Explanation:
 * After deleting the first column, A = ["a", "b", "c"].
 * Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
 * We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
 * Example 2:
 * <p>
 * Input: ["xc","yb","za"]
 * Output: 0
 * Explanation:
 * A is already in lexicographic order, so we don't need to delete anything.
 * Note that the rows of A are not necessarily in lexicographic order:
 * ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
 * Example 3:
 * <p>
 * Input: ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation:
 * We have to delete every column.
 * Note:
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 */
public class DeleteColumnstoMakeSortedII {
    /**
     * 题意：给你一个字符串数组，让你从每个string里删除最少的几列，让剩下的string变成字典序的。
     * 思路：似乎没什么套路；思路是贪心思想，最少的列一定是优先删除高位，因为高位有序了就已经是字典序了，低位就无需再管了。
     * 从高到低遍历，如果发现A[i][col] > A[i + 1][col]，说明col这一列肯定要删掉，于是进入col+1列继续;
     * 如果一趟下来发现A[i][col] <= A[i + 1][col]都满足，那么再从中挑选A[i][col] 严格小于 A[i + 1][col]的，下一趟不用对比了。
     * 这个sorted的set是关键点，因为一旦某些pair有序，后续都无需对比。
     **/
    public int minDeletionSize(String[] A) {
        HashSet<Integer> sorted = new HashSet<>();//其中记录的i表示A[i],A[i+1]是有序的
        int res = 0;
        for (int col = 0; col < A[0].length(); col++) {
            if (sorted.size() == A.length - 1) return res;
            boolean broken = false;
            for (int i = 0; i < A.length - 1; i++) {
                if (!sorted.contains(i) && A[i].charAt(col) > A[i + 1].charAt(col)) {
                    res++;//如果发现A[i][col] > A[i + 1][col]，说明col这一列肯定要删掉，于是进入col+1列继续
                    broken = true;
                    break;
                }
            }
            if (!broken) {
                for (int i = 0; i < A.length - 1; i++) {
                    if (A[i].charAt(col) < A[i + 1].charAt(col)) sorted.add(i);//A[i][col] 严格小于 A[i + 1][col]，后续无需对比
                }
            }
        }
        return res;
    }
}
