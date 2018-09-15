package dfs;

import java.util.ArrayList;
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


	List<List<Integer>> result = new ArrayList<>();

	public List<List<Integer>> combinationSum(int[] candidates, int target) {

		if (candidates == null || candidates.length == 0) return result;
		dfs(target, new ArrayList<Integer>(), candidates, 0);
		return result;
	}

	private void dfs(int target, List<Integer> item, int[] candidates, int start) {

		if (target < 0) return;
		if (target == 0) {
			result.add(new ArrayList<>(item));
			return;
		}
		for (int i = start; i < candidates.length; i++) {
			//加上下面这句对结果没有影响，但会减少很多次循环，因为同一个数字可以复用（每次从i开始），所以重复数字就没有意义了
			if (i > 0 && candidates[i] == candidates[i - 1]) continue;
			item.add(candidates[i]);
			dfs(target - candidates[i], item, candidates, i);
			item.remove(item.size() - 1);
		}
	}


	public static void main(String args[]) {
		CombinationSum combinationSum = new CombinationSum();
		int[] candidates = new int[]{8, 2, 3, 6};
		combinationSum.combinationSum(candidates, 7);
		System.out.println(combinationSum.result);

	}


//    private List<List<Integer>> result = new ArrayList<>();
//
//    private List<List<Integer>> combinationSum(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        dfs(candidates, 0, new ArrayList<Integer>(), result, target);
//        return result;
//    }
//
//    private void dfs(int[] candidates, int start, List<Integer> cell, List<List<Integer>> result, int target) {
//        if (target == 0) {
//            result.add(new ArrayList<>(cell));
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            //加上下面这句对结果没有影响，但会减少很多次循环，因为同一个数字可以复用（每次从i开始），所以有重复数字就没必要了
//            if (i > 0 && candidates[i] == candidates[i - 1]) continue;
//            if (target > 0) {
//                cell.add(candidates[i]);
//                dfs(candidates, i+1, cell, result, target - candidates[i]);
//                cell.remove(cell.size() - 1);
//            } else return;
//        }
//    }


}
