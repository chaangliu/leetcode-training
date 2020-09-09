package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * <p>
 * The same repeated number may be chosen from C unlimited number of times.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 * [7],
 * [2, 2, 3]
 * ]
 * <p>
 * Created by DrunkPiano on 2016/12/30.
 */

public class CombinationSum {
    /**
     * 题意：给你一个集合（不含重复数字），让你求出里面的元素加起来等于target的方案数，同一个数字可以重复用。
     * 解法：backtrack，注意要先排序，这样可以在target<0的时候剪枝。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> item, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break; // 注意这里是break而不是continue哦，因为数组已经排序了。
            item.add(candidates[i]);
            dfs(res, item, candidates, target - candidates[i], i); // 因为可以重复用，所以这里是i不是i+1
            item.remove(item.size() - 1);
        }
    }

    /**
     * 同样的，类似combinations等题目，lc给出了不用for循环的解法，选与不选当前数字。其实反过来看，上面的用for循环的解法其实也是选与不选，不选就是先把刚才加的删掉，去选下一个数字。
     * https://leetcode-cn.com/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
     */
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> combine = new ArrayList<Integer>();
            dfs(candidates, target, ans, combine, 0);
            return ans;
        }

        public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
            if (idx == candidates.length) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<Integer>(combine));
                return;
            }
            // 直接跳过
            dfs(candidates, target, ans, combine, idx + 1);
            // 选择当前数
            if (target - candidates[idx] >= 0) {
                combine.add(candidates[idx]);
                dfs(candidates, target - candidates[idx], ans, combine, idx);
                combine.remove(combine.size() - 1);
            }
        }
    }
}
