package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 996. Number of Squareful Arrays
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
 *
 * Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
 *
 *
 *
 * Example 1:
 *
 * Input: [1,17,8]
 * Output: 2
 * Explanation:
 * [1,8,17] and [17,8,1] are the valid permutations.
 * Example 2:
 *
 * Input: [2,2,2]
 * Output: 1
 *
 * 20190228
 */
public class NumberOfSquareArrays {

    //我的解法，模仿permutationsII那题。
    //可以优化的地方是不用每次都对比整个已有的list，只要比较队尾和当前位是否是perfect square就行了

    //另外，这题可以用邻接表和dp来做
    int res = 0;

    public int numSquarefulPerms(int[] A) {
        if (A == null || A.length == 0) return 0;
        Arrays.sort(A);
        permute(A, 0, new ArrayList<Integer>(), new boolean[A.length]);
        return res;
    }

    private void permute(int[] A, int idx, List<Integer> item, boolean[] visited) {
        if (item.size() >= 2) {
            if (!checkValid(item)) return;
        }
        if (idx == A.length) {
            res++;
        } else {
            for (int i = 0; i < A.length; i++) {
                if (i > 0 && A[i] == A[i - 1] && !visited[i - 1]) continue;
                if (!visited[i]) {
                    item.add(A[i]);
                    visited[i] = true;
                    permute(A, idx + 1, item, visited);
                    visited[i] = false;
                    item.remove(item.size() - 1);
                }
            }
        }
    }

    private boolean checkValid(List<Integer> list) {
        if (list.size() <= 1) return false;
        for (int i = 1; i < list.size(); i++) {
            if (!isPerfectSquare(list.get(i) + list.get(i - 1))) return false;
        }
        return true;
    }

    private boolean isPerfectSquare(int num) {
        int sq = (int) Math.sqrt(num);
        return sq * sq == num;
    }
}
