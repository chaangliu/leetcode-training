package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://segmentfault.com/a/1190000006910777
public class Subsets2 {
    public List<List<Integer>> subsets(int[] nums) {
        //先把输出的东西摆上去。
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cell = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, result, cell, 0);
//        dfs( result, cell, 0,nums);
        return result;
    }

    public void dfs(int[] nums, List<List<Integer>> result, List<Integer> cell, int start) {
        result.add(new ArrayList<Integer>(cell));
        //注意，是从start开始，length结束，想象深度优先遍历的图，走到尽头的节点会换一个节点开始
        for (int i = start; i < nums.length; i++) {
            //dfs的条件不满足了(start==nums.length了)，跳出递归，开始执行for，i才会大于start
            if (i > start && nums[i] == nums[i - 1])
                continue;
            //add-->dfs--->remove  backtracking套路
            cell.add(nums[i]);
            //每次执行到dfs，就要往result里加一个子集了，毋庸置疑. 关键：下一次recursion，start的位置是i+1，不要写成start+1！
            dfs(nums, result, cell, i + 1);
            //dfs结束的条件是，i = nums.length
            cell.remove(cell.size() - 1);
        }
    }


    public static void main(String args[]) {
        Subsets2 subsets = new Subsets2();
        int[] nums = {1, 2, 2};
        System.out.println(subsets.subsets(nums));
    }
}