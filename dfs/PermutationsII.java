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
    /**
     * 题意：求nums数组的所有排列组合。有重复元素，不能含有重复解。
     * 解法：大部分和I一样，就是要注意，排序之后的判断，注意!used[i - 1]不能省略，否则在aab下一层的第二个a处就直接continue了，一个解都得不到。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums); //!!!要先sort！！！
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;// 意思是不用从同一个初始入口进入dfs，注意!used[i - 1]不能省略
            if (!used[i]) {
                used[i] = true;
                item.add(nums[i]);
                backtrack(nums, res, item, used);
                item.remove(item.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 自创写法，记录上次入口元素
     */
    public List<List<Integer>> permuteUnique_(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 这种写法也需要sort
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> item, int[] A, boolean[] visited) {
        if (item.size() == A.length) {
            res.add(new ArrayList<>(item));
            return;
        }
        Integer prev = null;
        for (int i = 0; i < A.length; i++) {
            if (visited[i] || prev != null && prev == A[i]) continue;
            item.add(A[i]);
            visited[i] = true;
            dfs(res, item, A, visited);
            prev = item.remove(item.size() - 1);
            visited[i] = false;
        }
    }
}
