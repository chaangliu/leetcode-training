package dfs;

import java.util.ArrayList;
import java.util.List;

//Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//        Note: The solution set must not contain duplicate subsets.
//
//        Example:
//
//        Input: nums = [1,2,3]
//        Output:
//        [
//        [3],
//        [1],
//        [2],
//        [1,2,3],
//        [1,3],
//        [2,3],
//        [1,2],
//        []
//        ]
//https://www.jianshu.com/p/287050afdab4
//https://segmentfault.com/a/1190000006910777

public class Subsets {

    //############# 2018-09-21 Review #############
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> item = new ArrayList<>();
        backtracking(nums, res, item, 0);
        return res;
    }

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> item, int lastIndex) {
        for (int i = lastIndex; i < nums.length; i++) {
            item.add(nums[i]);
            res.add(new ArrayList<Integer>(item));//这一句可以放在for循环外面，这样就不用手动加一个空的ArrayList了
            backtracking(nums, res, item, i + 1);//注意这里不能是lastIndex + 1，而是i + 1
            item.remove(item.size() - 1);
        }
    }


//    public List<List<Integer>> subsets(int[] nums) {
//        //先把输出的东西摆上去。
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> cell = new ArrayList<>();
//        dfs(nums, result, cell, 0);
////        dfs( result, cell, 0,nums);
//        return result;
//    }
//
//    public void dfs(int[] nums, List<List<Integer>> result, List<Integer> cell, int start) {
//        ArrayList newCell = (ArrayList) cell;
//        ArrayList<Integer> cell2 = (ArrayList) newCell.clone();
//        result.add((cell2));
//        String a = "asd";
//        //注意，是从start开始，length结束，想象深度优先遍历的图，走到尽头的节点会换一个节点开始
//        for (int i = start; i < nums.length; i++) {
//            cell.add(nums[i]);
//            //关键：下一次recursion，start的位置是i+1，不要写成start+1！
//            dfs(nums, result, cell, i + 1);
//            //dfs结束的条件是，i = nums.length
//            cell.remove(cell.size() - 1);
//        }
//    }
//    public void dfs(List<List<Integer>> result, List<Integer> list, int start, int[] nums) {
//        //先把当前的子集加上，这里添加的语法我命名为『照相法』
//        result.add(new ArrayList<>(list));
//        //注意这里要从start位置开始循环，否则就是stackoverflow
//        for (int i = start; i < nums.length; i++) {
//            //添加start位置的数字
//            list.add(nums[i]);
//            //开始递归，比如把1加上去之后，就稳住1，找后面比如2.
//            dfs(result, list, i + 1, nums);
//            //backtrack，就是把之前加上的去掉，相当于往回走，比如之前加到1，2，3
//            //就把3去掉，然后再找。
//            list.remove(list.size() - 1);
//        }
//    }

//    public static void main(String args[]) {
//        Subsets subsets = new Subsets();
//        int[] nums = {1, 2, 3};
//        System.out.println(subsets.subsets(nums));
//    }
//
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> cell = new ArrayList<>();
//        dfs(nums, result , cell , 0);
//        return result;
//    }
//
//    public void dfs(int [] nums , List<List<Integer>> result , List<Integer> cell , int start){
//        //add 放在for的外面,new一个
//        result.add(new ArrayList<>(cell));
//        //start指针的作用，是在dfs的时候向着深处走
//        for(int i = start ; i < nums.length ; i ++){
//            cell.add(nums[i]);
//            //for中进行的dfs
//            dfs(nums , result , cell , i+1);
//            //backtracking
//            cell.remove(cell.size()-1);
//        }
//    }


}