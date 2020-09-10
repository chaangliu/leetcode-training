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

    /**
     * 题意：求combination sum，要求1. 同一个数字只能用一次，2. 不能出现相同的组合
     * 解法：为了达到上述两个要求，这题需要在combination sum 1的基础上有两个改动，
     * 第一就是dfs的下一个start变成了i + 1，
     * 第二是有点难度的，就是如何避免重复从同一个入口进入。这里有三种方法；
     * 1. dfs之前 if (i > start && candidates[i] == candidates[i - 1]) continue;
     * 2. dfs之后记录remove的数字，然后dfs之前判断刚才remove的数字是否和这次进入的相同
     * 3. dfs之后(之前也行，用i > start)用while跳过相同的数字
     */

    /**
     * 第一种
     */
    public List<List<Integer>> combinationSum2___(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs___(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void dfs___(List<List<Integer>> res, List<Integer> item, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            if (i > start && candidates[i] == candidates[i - 1])
                continue; // 考虑[1, 1, 2]，3; 找到item[1, 2], 然后把2remove；然后从第二个1进入，这时候 start是0，i是1，跳过。
            item.add(candidates[i]);
            dfs___(res, item, candidates, target - candidates[i], i + 1);
            item.remove(item.size() - 1);
        }
    }


    /**
     * 第二种
     */
    public List<List<Integer>> combinationSum2_(int[] candidates, int target) {
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
        Integer removed = null;
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            if (removed != null && candidates[i] == removed) continue; // 如果现在又要从刚才移除的入口进来，就会重复，跳过即可
            item.add(candidates[i]);
            dfs(res, item, candidates, target - candidates[i], i + 1);
            removed = item.remove(item.size() - 1); // 记录刚才移除的数字
        }
    }

    /**
     * 第三种
     */
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
                i++;
            }
        }
    }

    public static void main(String[] args) {
        new CombinationSum2().combinationSum2___(new int[]{1, 1, 2}, 3);
    }
}
