package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
//        Note: The solution set must not contain duplicate subsets.
//
//        Example:
//
//        Input: [1,2,2]
//        Output:
//        [
//        [2],
//        [1],
//        [1,2,2],
//        [2,2],
//        [1,2],
//        []
//        ]

//https://segmentfault.com/a/1190000006910777
public class Subsets2 {

    //############# 2018-09-21 Review #############
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> item = new ArrayList<>();
        backtracking(nums, res, item, 0);
        return res;
    }

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> item, int lastIndex) {
        for (int i = lastIndex; i < nums.length; i++) {
            if (i > lastIndex && nums[i] == nums[i - 1]) continue;
            item.add(nums[i]);
            res.add(new ArrayList<Integer>(item));//这一句可以放在for循环外面，这样就不用手动加一个空的ArrayList了
            backtracking(nums, res, item, i + 1);
            item.remove(item.size() - 1);
        }
    }

//    public List<List<Integer>> subsets(int[] nums) {
//        //先把输出的东西摆上去。
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> cell = new ArrayList<>();
//        Arrays.sort(nums);
//        dfs(nums, result, cell, 0);
////        dfs( result, cell, 0,nums);
//        return result;
//    }
//
//    public void dfs(int[] nums, List<List<Integer>> result, List<Integer> cell, int start) {
//        result.add(new ArrayList<Integer>(cell));
//        //注意，是从start开始，length结束，想象深度优先遍历的图，走到尽头的节点会换一个节点开始
//        for (int i = start; i < nums.length; i++) {
//            //dfs的条件不满足了(start==nums.length了)，跳出递归，开始执行for，i才会大于start
//            if (i > start && nums[i] == nums[i - 1])
//                continue;
//            //add-->dfs--->remove  backtracking套路
//            cell.add(nums[i]);
//            //每次执行到dfs，就要往result里加一个子集了，毋庸置疑. 关键：下一次recursion，start的位置是i+1，不要写成start+1！
//            dfs(nums, result, cell, i + 1);
//            //dfs结束的条件是，i = nums.length
//            cell.remove(cell.size() - 1);
//        }
//    }


    public static void main(String args[]) {
        Subsets2 subsets = new Subsets2();
        int[] nums = {1, 2, 2};
        System.out.println(subsets.subsetsWithDup(nums));
    }
}