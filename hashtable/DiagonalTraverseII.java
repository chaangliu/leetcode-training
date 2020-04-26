package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 * Example 1:
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * Example 3:
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 * Example 4:
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * There at most 10^5 elements in nums.
 */
public class DiagonalTraverseII {
    /**
     * 题意：给你一个二维数组，里面每个字数组的长度不尽相同，让你从左下角往右上角沿着对角线打印结果。
     * 解法：我的思路超时了：先统计最大的列数有多少，然后对于每一行去遍历。
     * 这么写很反常识，花了好多时间，结果最后还是超时了，因为如果有一个item非常长，其他item长度都是1，那你需要遍历O(mn)，而不是O(m*1)的时间。
     * 正确的解法是找规律，你会发现每条Diagonal上的i,j加起来都相同，所以可以归类。那么怎么保证顺序？技巧是从m-1行开始遍历。
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = 0, i = 0;
        int rows = nums.size(), maxCols = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int r = rows - 1; r >= 0; r--) { // The values from the bottom rows are the starting values of the diagonals.
            for (int c = 0; c < nums.get(r).size(); c++) {
                map.putIfAbsent(r + c, new ArrayList<>());
                map.get(r + c).add(nums.get(r).get(c));
                n++;
            }
            maxCols = Math.max(maxCols, nums.get(r).size());
        }
        int[] ans = new int[n];
        for (int key = 0; key <= rows + maxCols; key++) {
            List<Integer> value = map.get(key);
            if (value == null) continue;
            for (int v : value) ans[i++] = v;
        }
        return ans;
    }

    /**
     * 超时做法
     */
    public int[] findDiagonalOrder__TLE(List<List<Integer>> nums) {
        int n = nums.size(), maxJ = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maxJ = Math.max(maxJ, nums.get(i).size());
            for (int j = 0; j <= i; j++) {
                if (i - j >= 0 && nums.get(i - j).size() <= j) continue;
                res.add(nums.get(i - j).get(j));
            }
        }
        for (int j = 1; j < maxJ; j++) {
            int k = j;
            for (int i = n - 1; i >= 0; i--) {
                if (nums.get(i).size() > k) {
                    res.add(nums.get(i).get(k));
                }
                k++;
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) result[i] = res.get(i);
        return result;
    }
}
