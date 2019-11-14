package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
 * For convenience, we'll call the person with label x, simply "person x".
 * We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
 * Also, we'll say quiet[x] = q if person x has quietness q.
 * Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
 * Example 1:
 * Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * Output: [5,5,2,5,4,5,6,7]
 * Explanation:
 * answer[0] = 5.
 * Person 5 has more money than 3, which has more money than 1, which has more money than 0.
 * The only person who is quieter (has lower quiet[x]) is person 7, but
 * it isn't clear if they have more money than person 0.
 * answer[7] = 7.
 * Among all people that definitely have equal to or more money than person 7
 * (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
 * is person 7.
 * The other answers can be filled out with similar reasoning.
 * Note:
 * 1 <= quiet.length = N <= 500
 * 0 <= quiet[i] < N, all quiet[i] are different.
 * 0 <= richer.length <= N * (N-1) / 2
 * 0 <= richer[i][j] < N
 * richer[i][0] != richer[i][1]
 * richer[i]'s are all different.
 * The observations in richer are all logically consistent.
 * 20191114
 */
public class LoudandRich {
    /**
     * 题意：对每个人，求所有比自己rich的人当中quiet值最低的那个人
     * 思路：先把直接比自己rich的人统计出来，然后从每个人开始dfs寻找所有比自己rich的人，这个过程中可以用memo
     * 我的解法(看了lee的解法之后发现我的memo和res重复了..！可以直接用res做memo)：
     **/
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, ArrayList<Integer>> direct = new HashMap<>();
        int N = quiet.length;
        for (int[] r : richer) {
            direct.putIfAbsent(r[1], new ArrayList<>());
            direct.get(r[1]).add(r[0]);
        }
        Integer[] memo = new Integer[N];
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = dfs(i, direct, memo, quiet);
        }
        return res;
    }

    /**
     * dfs返回所有比p富有的人中最quiet的那个人
     **/
    private int dfs(int p, Map<Integer, ArrayList<Integer>> d, Integer[] memo, int[] quiet) {
        int min = p;
        if (!d.containsKey(p)) return p;
        if (memo[p] != null)
            return memo[p];
        for (int r : d.get(p)) {
            if (quiet[r] < quiet[min]) {//已犯错误：把r和quiet[r]混淆了，题目要求的是人，不是quiet值
                min = r;
            }
            int r2 = dfs(r, d, memo, quiet);
            if (quiet[r2] < quiet[min]) {
                min = r2;
            }
        }
        memo[p] = min;
        return min;
    }


    /**
     * lee的解法，很简洁
     */
    HashMap<Integer, List<Integer>> richer2 = new HashMap<>();
    int res[];

    public int[] loudAndRich_LEE(int[][] richer, int[] quiet) {
        int n = quiet.length;
        for (int i = 0; i < n; ++i) richer2.put(i, new ArrayList<Integer>());
        for (int[] v : richer) richer2.get(v[1]).add(v[0]);
        res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) dfs(i, quiet);
        return res;
    }

    int dfs(int i, int[] quiet) {
        if (res[i] >= 0) return res[i];
        res[i] = i;
        for (int j : richer2.get(i)) if (quiet[res[i]] > quiet[dfs(j, quiet)]) res[i] = res[j];
        return res[i];
    }
}
