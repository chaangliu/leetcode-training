package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归、字典序
 * Created by DrunkPiano on 2016/12/14 .
 * <p>
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
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
 */

public class Permutations {
    public static void main(String args[]) {
        int[] a = {1, 2, 3};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(a));
    }

    //############# 2016/12/14 Original #############

//    public List<List<Integer>> permute(int[] num) {
//        if (num.length == 0) return null;
//        List<Integer> cell = new ArrayList<>();
//        Arrays.sort(num);
//        List<List<Integer>> result = new ArrayList<>();
//        return backtracking(num, cell, result, new boolean[num.length]);
//
//    }
//
//    public List<List<Integer>> backtracking(int[] nums, List<Integer> cell, List<List<Integer>> result, boolean[] used) {
//        if (cell.size() == nums.length) {
//            result.add(new ArrayList<>(cell));
//            return null;
//        }
//        for (int i = 0; i < nums.length; i++) {
////            if (!used[i] || i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue;
//            if (!used[i]) {
//                cell.add(nums[i]);
//                used[i] = true;
//                backtracking(nums, cell, result, used);
//                cell.remove(cell.size() - 1);
//                used[i] = false;
//            }
//        }
//        return result;
//    }

    //############# 2018-09-19 Review #############
    public List<List<Integer>> permute(int[] nums) {
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
        //注意for循环外面没有内容了
        for (int i = 0; i < nums.length; i++) {
            if (item.size() == nums.length) {
                //注意这里需要创建一个新的对象，item里的东西赋值给它
                res.add(new ArrayList<Integer>(item));
                return;
            } else {
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
    }

    //backtracking第二种写法
    private void backtracking2(int[] nums, List<List<Integer>> res, ArrayList<Integer> item) {
        for (int i = 0; i < nums.length; i++) {
            if (item.size() == nums.length) {
                //注意这里需要创建一个新的对象，item里的东西赋值给它
                res.add(new ArrayList<Integer>(item));
                return;
            } else {
                if (!item.contains(nums[i])) {
                    item.add(nums[i]);
                    backtracking2(nums, res, item);
                    item.remove(item.size() - 1);
                }
            }
        }
    }
}
