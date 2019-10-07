package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. For example, 321 is a Stepping Number while 421 is not.
 * Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.
 * Example 1:
 * Input: low = 0, high = 21
 * Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
 * Constraints:
 * 0 <= low <= high <= 2 * 10^9
 * 20191007
 */
public class SteppingNumbers {
    /**
     * 题意：在[low,high]内找到所有满足各位等差+-1的数字，比如321，123。
     * 看到数据范围很大，肯定不能一个个判断。然后我没思路了。看了答案，有BFS和DFS；
     * Approach1, bfs
     * BFS跟open the lock思路有点像（蔓延找可能组合这种题，对于数字的题目可能不太容易看出来），
     * 然后我疑惑是否应该开始时把low加进去，看了答案，是把0~9加进去然后根据规律去BFS，而且这个BFS不需要层序，因为没有让求次数。
     * 退出的判断，观察到只要第一个出现>high的情况后面就会都>high了。
     * 这个时间复杂度整体很小，几乎跟res长度一致。
     */
    //0, 1, 2,3,4..
    //[0-1,01], [10,12], [21, 23], [32, 34], [43,45],..98, 210, 123,
    //[10-1,123], [210,234], [321,345]
    //规律：始终在已有数字基础上 * 10 + 倒数第1位+1或-1
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i <= 9; i++) q.offer(i);
        while (!q.isEmpty()) {
            int num = q.poll();
            if (num >= low && num <= high) res.add(num);
            if (num > high) break;
            if (num == 0) continue;
            int mod = num % 10;
            if (mod != 0) q.offer(num * 10 + mod - 1);
            if (mod != 9) q.offer(num * 10 + mod + 1);
        }
        return res;
    }


    /**
     * Approach2, dfs
     */
    public List<Integer> countSteppingNumbers_dfs(int low, int high) {
        List<Integer> list = new ArrayList();
        for (long i = 0; i <= 9; i++) {
            dfs(low, high, i, list);
        }
        Collections.sort(list);
        return list;
    }

    private void dfs(int low, int high, long cur, List<Integer> list) {
        if (cur >= low && cur <= high) list.add((int) cur);
        if (cur == 0 || cur > high) return;
        long last = cur % 10, inc = cur * 10 + last + 1, dec = cur * 10 + last - 1;
        if (last == 0) dfs(low, high, inc, list);
        else if (last == 9) dfs(low, high, dec, list);
        else {
            dfs(low, high, inc, list);
            dfs(low, high, dec, list);
        }
    }
}
