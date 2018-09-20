package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//        Example:
//
//        Input: [1,1,2]
//        Output:
//        [
//        [1,1,2],
//        [1,2,1],
//        [2,1,1]
//        ]
//https://leetcode.com/problems/permutations-ii/description/


public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        //!!!要先sort！！！
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, ArrayList<Integer> item, boolean[] used) {
        if (item.size() == nums.length) {
            res.add(new ArrayList<Integer>(item));
            //返回上一层(doing backtrack)
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //注意continue的条件；画一个n * n的递归栈的图就比较容易理解
            //1 1 2
            //1 1 2
            //1 1 2
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if (!used[i]) {
                used[i] = true;
                item.add(nums[i]);
                backtrack(nums, res, item, used);
                item.remove(item.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String args[]) {
        int[] nums = {3, 3, 0, 3};
        List<List<Integer>> res = new PermutationsII().permuteUnique(nums);
        System.out.println();
    }
}
