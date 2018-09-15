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
    List<List<Integer>> result = new ArrayList<>();
    int[] mCandidates = {};

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.mCandidates = candidates;
        Arrays.sort(mCandidates);
        dfs(new ArrayList<Integer>(), target, 0);
        return result;
    }

    public void dfs(List<Integer> currentList, int target, int startPoint) {
        ArrayList<Integer> newList = new ArrayList<>(currentList);
        if (target == 0) {
            result.add(newList);
            return;
        }
        for (int i = startPoint; i < mCandidates.length && mCandidates[i] <= target; i++) {
            currentList.add(mCandidates[i]);
            dfs(currentList, target - mCandidates[i], i + 1);
            currentList.remove(Integer.valueOf(mCandidates[i]));
            while (i < mCandidates.length - 1 && mCandidates[i] == mCandidates[i + 1]) {
//                continue;
                i++;
            }
        }
    }

    public static void main(String args[]) {
        CombinationSum2 combinationSum2 = new CombinationSum2();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        combinationSum2.combinationSum2(candidates, 7);
        System.out.println(combinationSum2.result);

    }
}
