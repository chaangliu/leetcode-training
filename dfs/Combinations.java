package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * Created by DrunkPiano on 2017/2/14.
 */

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cell = new ArrayList<>();
        dfs(1, n, k, result, cell);
        return result;
    }

    public void dfs(int start, int n, int k, List<List<Integer>> result, List<Integer> cell) {
//        if (cell.size() == k && !result.contains(cell)) {
        if (cell.size() == k) {
            result.add(new ArrayList<Integer>(cell));
            return; //return作用相当于else
        }
        for (int i = start; i <= n; i++) {
            cell.add(i);
            dfs(i + 1, n, k, result, cell);
            cell.remove(cell.size() - 1);
        }
    }

    /**
     * 没有k限制的情况
     * 例子，求一个string的所有subsequence
     */
    public ArrayList<String> combine_noK(String str) {
        ArrayList<String> result = new ArrayList<>();
        dfs(0, str, result, "");
        return result;
    }

    public void dfs(int start, String str, ArrayList<String> set, String s) {
        set.add(s);
        for (int i = start; i < str.length(); i++) {
            dfs(i + 1, str, set, s + str.charAt(i));
        }
    }

    public static void main(String args[]) {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine_noK("abcde"));
    }
}
