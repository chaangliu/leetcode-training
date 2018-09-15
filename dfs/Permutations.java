package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归、字典序
 * Created by DrunkPiano on 2016/12/14 .
 */

public class Permutations {
    public static void main(String args[]) {
//        int[] a = {1, 1, 2};
        int[] a = {1, 2, 3};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(a));
    }


    public List<List<Integer>> permute(int[] num) {
        if (num.length == 0) return null;
        List<Integer> cell = new ArrayList<>();
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<>();
        return backtracking(num, cell, result, new boolean[num.length]);

    }

    public List<List<Integer>> backtracking(int[] nums, List<Integer> cell, List<List<Integer>> result, boolean[] used) {
        if (cell.size() == nums.length) {
            result.add(new ArrayList<>(cell));
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
//            if (!used[i] || i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue;
            if (!used[i]) {
                cell.add(nums[i]);
                used[i] = true;
                backtracking(nums, cell, result, used);
                cell.remove(cell.size() - 1);
                used[i] = false;
            }
        }
        return result;
    }

//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        // Arrays.sort(nums); // not necessary
//        backtrack(list, new ArrayList<Integer>(), nums);
//        return list;
//    }
//
//    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
//        if(tempList.size() == nums.length){
//            list.add(new ArrayList<>(tempList));
//        } else{
//            for(int i = 0; i < nums.length; i++){
//                if(tempList.contains(nums[i])) continue; // element already exists, skip
//                tempList.add(nums[i]);
//                backtrack(list, tempList, nums);
//                tempList.remove(tempList.size() - 1);
//            }
//        }
//    }


//    public static List<List<Integer>> permute(int[] num) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//
//        int len = num.length;
//        if (len == 0) return ret;
//
//        Arrays.sort(num); //字典序法需先对数组升序排序
//
//        //数组转为list
//        List<Integer> list0 = new ArrayList<Integer>();
//        for (int i = 0; i < len; i++) {
//            list0.add(num[i]);
//        }
//
//        //把原始数组对应的list添加到结果中，不能直接添加list0，因为后面它会一直变化
//        List<Integer> ll = new ArrayList<Integer>();
//        ll.addAll(list0);
//        ret.add(ll);
//
//        //逐次找下一个排列
//        for (int i = 1; i < factorial(len); i++) {
//            ret.add(nextPermutation(list0));
//        }
//        return ret;
//    }
//
//    /***字典序法生成下一个排列***/
//    public static List<Integer> nextPermutation(List<Integer> num) {
//        //找到最后一个正序
//        int i = num.size()-1;
//        while(i > 0 && num.get(i-1) >= num.get(i)){
//            i--;
//        }
//
//        //找到最后一个比num[i-1]大的数
//        int j = i;
//        while(j < num.size() && num.get(j) > num.get(i-1)) {
//            j++;
//        }
//
//        //交换num[i-1]和num[j-1]
//        int tmp = num.get(i - 1);
//        num.set(i - 1, num.get(j - 1));
//        num.set(j - 1, tmp);
//
//        //反转i以后的数
//        reverse(num, i, num.size()-1);
//
//        List<Integer> ret = new ArrayList<Integer>();
//        ret.addAll(num);
//        return ret;
//    }
//
//    public static void reverse(List<Integer> list, int begin, int end) {
//        for(int i = begin, j = end; i < j; i++) {
//            list.add(i, list.remove(j));
//        }
//    }
//
//    public static int factorial(int n) {
//        return (n == 1 || n == 0) ? 1 : factorial(n - 1) * n;
//    }
}
