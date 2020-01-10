package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * https://leetcode.com/problems/permutations/description/
 * https://www.jianshu.com/p/d7a02c614cfb
 * Created by DrunkPiano on 2016/12/14 .
 * 20200110 review
 */

public class Permutations {

    /**
     * 题意：给你一个数组，让你输出出所有的排列。
     * 解法：经典backtracking
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, new boolean[nums.length], new ArrayList<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, boolean[] visited, List<Integer> cell) {
        if (cell.size() == nums.length) {
            res.add(new ArrayList<>(cell));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            cell.add(nums[i]);
            dfs(res, nums, visited, cell);
            visited[i] = false;
            cell.remove(cell.size() - 1);
        }
    }

    /**
     * 2019-07-11 Review 今天看到一种新颖的解法，不用visited数组，而是用index传入下一层的方式，
     * 从每一层的起始数字开始与后面的数字交换。
     * -----
     * 2018-09-19 Review
     */
    public List<List<Integer>> permute__(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        backtracking(nums, res, used, new ArrayList<Integer>());
        return res;
    }

    //backtracking第一种写法
    private void backtracking(int[] nums, List<List<Integer>> res, boolean[] used, ArrayList<Integer> item) {
        if (item.size() == nums.length) {
            //注意这里需要创建一个新的对象，item里的东西赋值给它
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //利用一个used[]数组
            if (!used[i]) {
                item.add(nums[i]);
                used[i] = true;
                backtracking(nums, res, used, item);
                used[i] = false;//返回上一层递归栈的时候恢复状态
                item.remove(item.size() - 1);//删除最后一次增加的元素
            }
        }
    }

    //backtracking第二种写法
    private void backtracking2(int[] nums, List<List<Integer>> res, ArrayList<Integer> item) {
        if (item.size() == nums.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!item.contains(nums[i])) {
                item.add(nums[i]);
                backtracking2(nums, res, item);
                item.remove(item.size() - 1);
            }
        }
    }
}
