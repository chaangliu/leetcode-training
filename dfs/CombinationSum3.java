package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * <p>
 * Note:
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * https://leetcode.com/problems/combination-sum-iii/description/
 *
 * Created by liu chang on Sept 23, 2018.
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k < 1 || n < 1) return res;
        backtrack(res, k, n, new ArrayList<Integer>(), 1);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int k, int remain, List<Integer> item, int start) {
        if (remain < 0 || item.size() > k) return;
        if (remain == 0 && item.size() == k) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i < 10; i++) {
            item.add(i);
            backtrack(res, k, remain - i, item, i + 1);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String args[]) {
        List<List<Integer>> res = new CombinationSum3().combinationSum3(3, 9);
        System.out.println();
    }
}
