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
    /**
     * 题意：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * 解法：DFS。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, k, n);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> item, int start, int k, int n) {
        if (item.size() == k) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = start; i <= n; i++) {
            item.add(i);
            dfs(res, item, i + 1, k, n); // 已犯错误：这儿写成了start + 1，那样会造成下一次递归添加的数字比当前数字小
            item.remove(item.size() - 1);
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

    /**
     * leetcode official
     * 看到另一种方案，不需要for循环，写两个dfs代表选与不选，很巧妙; 和IncreasingSubsequences一样。
     * 这儿我还想了一会儿，比如1,2,3,4，没有for循环的话，是怎么从1跳到3的？模拟一下就知道，从1跳到2，然后2又面临选与不选，如果每层都不选，就可以选到[1,3],[1,4]。。妙啊
     * https://leetcode-cn.com/problems/combinations/solution/zu-he-by-leetcode-solution/
     */
    public List<List<Integer>> combine_(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }
}
