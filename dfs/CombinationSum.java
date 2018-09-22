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
    //############# 2018-09-22 Review #############
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);//要点0. 要先sort
        backtrack(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }

    //#my version:
    // (我觉得下次还是不要用有返回值的backtrack比较清晰)
    private List<List<Integer>> backtracking(int[] candidates, int target, List<List<Integer>> res, List<Integer> item, int start) {
        for (int i = start; i < candidates.length; i++) {//要点1. 重复利用一个数，不代表可以用之前的数
            if (target < candidates[i]) return res;
            item.add(candidates[i]);//要点2. 这次写的时候判断了candidates[i] == target，导致无论candidates[i] == target是否成立，都要先add candidate。写成target == 0比较好。
            if (candidates[i] == target) {
                res.add(new ArrayList<Integer>(item));
                item.remove(item.size() - 1);//要点3. candidates[i] == target，要额外add和remove
                return res;
            }
            backtracking(candidates, target - candidates[i], res, item, i);
            item.remove(item.size() - 1);
        }
        return res;
    }

    //good version:
    private void backtrack(int[] nums, int remain, List<List<Integer>> list, List<Integer> tempList, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            //加上下面这句对结果没有影响，但会减少很多次循环，因为同一个数字可以复用（每次从i开始），所以重复数字就没有意义了
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            tempList.add(nums[i]);
            backtrack(nums, remain - nums[i], list, tempList, i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }

//	List<List<Integer>> result = new ArrayList<>();
//
//	public List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//		if (candidates == null || candidates.length == 0) return result;
//		dfs(target, new ArrayList<Integer>(), candidates, 0);
//		return result;
//	}
//
//	private void dfs(int target, List<Integer> item, int[] candidates, int start) {
//
//		if (target < 0) return;
//		if (target == 0) {
//			result.add(new ArrayList<>(item));
//			return;
//		}
//		for (int i = start; i < candidates.length; i++) {
//			//加上下面这句对结果没有影响，但会减少很多次循环，因为同一个数字可以复用（每次从i开始），所以重复数字就没有意义了
//			if (i > 0 && candidates[i] == candidates[i - 1]) continue;
//			item.add(candidates[i]);
//			dfs(target - candidates[i], item, candidates, i);
//			item.remove(item.size() - 1);
//		}
//	}
}
