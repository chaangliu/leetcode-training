package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * <p>
 * Each number in C may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * Created by DrunkPiano on 2017/1/2.
 */

public class CombinationSum2 {

    //############# 2018-09-22 Review #############
    //这题有必要再多做几遍啊
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<Integer>(), candidates, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> item, int[] candidates, int remain, int start) {
        if (remain < 0)
            return; //已犯错误: 写成了remain < candidates[i]， 2. start = i + 1的情况，if语句一定要放for的外面，不然target == candidates[len -1 ]的case会漏掉
        if (remain == 0) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && i != start)//已犯错误：这里尤其要注意，i != start(或者i > start)才代表这个数应该跳过，而不是i == start。有一定思维难度，调试才能看出。
                continue;
            item.add(candidates[i]);
            backtrack(res, item, candidates, remain - candidates[i], i + 1);
            item.remove(item.size() - 1);
        }
    }

    //############# 2017/1/2 #############
//    List<List<Integer>> result = new ArrayList<>();
//    int[] mCandidates = {};
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        this.mCandidates = candidates;
//        Arrays.sort(mCandidates);
//        dfs(new ArrayList<Integer>(), target, 0);
//        return result;
//    }
//
//    public void dfs(List<Integer> currentList, int target, int startPoint) {
//        ArrayList<Integer> newList = new ArrayList<>(currentList);
//        if (target == 0) {
//            result.add(newList);
//            return;
//        }
//        for (int i = startPoint; i < mCandidates.length && mCandidates[i] <= target; i++) {
//            currentList.add(mCandidates[i]);
//            dfs(currentList, target - mCandidates[i], i + 1);
//            currentList.remove(Integer.valueOf(mCandidates[i]));
//            while (i < mCandidates.length - 1 && mCandidates[i] == mCandidates[i + 1]) {
////                continue;
//                i++;
//            }
//        }
//    }

//    public static void main(String args[]) {
//        CombinationSum2 combinationSum2 = new CombinationSum2();
//        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
//        combinationSum2.combinationSum2(candidates, 7);
//        System.out.println(combinationSum2.result);
//
//    }
}
