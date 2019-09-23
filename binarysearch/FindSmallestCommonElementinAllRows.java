package binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 * <p>
 * If there is no common element, return -1.
 * Example 1:
 * <p>
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 * Constraints:
 * 1 <= mat.length, mat[i].length <= 500
 * 1 <= mat[i][j] <= 10^4
 * mat[i] is sorted in increasing order.
 * 20190923
 */
public class FindSmallestCommonElementinAllRows {
    /**
     [1,2,3,4,5],
     [2,4,5,8,10],
     [3,5,7,9,11],
     [1,3,5,7,9]]

     [5],
     [5,8,10],
     [5,7,9,11],
     [5,7,9]]
     **/
    /**
     * 这题我看到每行是有序的，仍然没条件反射想到二分，很可惜啊。
     * 好的解法，二分搜索。对于第一行中出现的每个数在后面几行中二分搜索，复杂度O(nlogm)
     **/
    public int smallestCommonElement(int[][] mat) {
        int row = mat.length;
        for (int num : mat[0]) {
            boolean found = true;
            for (int i = 1; i < row; i++) {
                int index = Arrays.binarySearch(mat[i], num);
                if (index < 0) {//-insertionPoint - 1, insertionPoint可以理解为c++中的upper_bound返回的数
                    found = false;
                    break;
                }
            }
            if (found) return num;
        }
        return -1;
    }

    /**
     * 我的解法，TLE，最差时间O(mn)
     * map维护每行下次遍历的开始index
     **/
    public int smallestCommonElement_TLE(int[][] mat) {
        Map<Integer, Integer> idx = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int min = 10000;
        int rightMost = 0;
        while (rightMost < mat[0].length) {
            for (int i = 0; i < mat.length; i++) {
                int start = idx.getOrDefault(i, 0);
                freq.put(mat[i][start], freq.getOrDefault(mat[i][start], 0) + 1);
                if (mat[i][start] < min) {
                    min = mat[i][start];
                }
            }
            System.out.println("min is " + min + "freq.get(min) is " + freq.get(min));
            if (freq.get(min) == mat.length) {
                return min;
            }
            for (int i = 0; i < mat.length; i++) {
                int start = idx.getOrDefault(i, 0);
                if (mat[i][start] == min) {
                    idx.put(i, start + 1);
                    rightMost = Math.max(rightMost, start + 1);
                    if (rightMost == mat[0].length) return -1;
                }
            }
            System.out.println("rightMost is " + rightMost);
            freq.clear();
            min = 10000;
        }
        return -1;
    }
}
