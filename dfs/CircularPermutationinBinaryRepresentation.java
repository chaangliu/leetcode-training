package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 * <p>
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 * Example 1:
 * Input: n = 2, start = 3
 * Output: [3,2,0,1]
 * Explanation: The binary representation of the permutation is (11,10,00,01).
 * All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
 * Example 2:
 * <p>
 * Input: n = 3, start = 2
 * Output: [2,6,7,5,4,0,1,3]
 * Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
 * Constraints:
 * 1 <= n <= 16
 * 0 <= start < 2 ^ n
 * 20191028
 */
public class CircularPermutationinBinaryRepresentation {

    /**
     * 题意：给你一个数字start和一个数字n，从start开始按顺序生成2^n个数字，使得相邻数字之间只有1bit的diff，并且首尾两个数字diff也是1.
     * 周赛第二题。我拿到之后第一感觉是BFS？因为想到了OPEN THE LOCK那题，但看了一下发现不行因为LOCK只有4位。
     * DFS?我看到n有16位，也没敢操作，但事实证明DFS可以过的。2^16 < 10^6，记住16这个量级的dfs是可以接受的。（或者说，应该有能快速尝试DFS的能力）
     * 这题的标准做法是利用Gray Code生成的迭代方法，技巧性比较强，这里我不列出来了，可以去讨论区或者下面链接查看https://www.geeksforgeeks.org/given-a-number-n-generate-bit-patterns-from-0-to-2n-1-so-that-successive-patterns-differ-by-one-bit/
     * 我觉得最通用的方法还是DFS，而且这题就是89. Gray Code的原题，那题可以直接调用这题然后把输入参数n赋予0就行了。
     * 先贴一个我写的DFS(backtracking)代码，模仿自https://leetcode.com/problems/circular-permutation-in-binary-representation/discuss/414145/Java-DFS-%2B-Bit
     **/
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(start);
        res.add(start);
        dfs(n, start, start, visited, res, (int) Math.pow(2, n));
        return res;
    }

    private boolean dfs(int n, int start, int cur, HashSet<Integer> visited, List<Integer> res, int total) {
        if (res.size() == total) {
            int x = start ^ res.get(res.size() - 1);//如何判断首尾两个数字是不是differ by 1 bit也是一个技巧，这里用的方法是判断start^end得到得到的数是否只有1bit是1
            return (x & (x - 1)) == 0;
        }
        for (int i = 0; i < n; i++) {
            cur = cur ^ (1 << i);//把当前数的第i位flip一下
            if (visited.contains(cur)) {
                cur = cur ^ (1 << i);//flip回来
                continue;
            }
            visited.add(cur);
            res.add(cur);
            if (dfs(n, start, cur, visited, res, total)) {
                return true;
            }
            visited.remove(cur);
            res.remove(res.size() - 1);
        }
        return false;
    }

    /**
     * 讨论区的DFS，每次取res的最后一位数字
     */
    public List<Integer> circularPermutation_(int n, int start) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        res.add(start);
        set.add(start);
        dfs(n, set, res, start);
        return res;
    }

    boolean dfs(int n, Set<Integer> set, List<Integer> res, int start) {
        if (set.size() == (int) Math.pow(2, n)) {
            int x = res.get(res.size() - 1) ^ start;
            return (x & (x - 1)) == 0;
        }
        int last = res.get(res.size() - 1);
        for (int i = 0; i < 16; i++) {
            int next = (last ^ (1 << i));
            if (next <= Math.pow(2, n) - 1 && !set.contains(next)) {
                set.add(next);
                res.add(next);
                if (dfs(n, set, res, start)) {
                    return true;
                }
                set.remove(next);
                res.remove(res.size() - 1);
            }
        }
        return false;
    }


    /**
     * 讨论区的另一种做法，无序BACKTRACK，只要找到2^n个数字就一定正确（上面的DFS代码也是如此），这也许是GrayCode格雷码的性质吧，虽然不知道怎么证明。。
     */
    List<Integer> list;

    public List<Integer> circularPermutation__(int n, int start) {
        list = new LinkedList<>();
        boolean[] visited = new boolean[1 << n];
        dfs(n, start, visited);
        return list;
    }

    private void dfs(int n, int start, boolean[] visited) {
        if (visited[start]) {
            return;
        }
        list.add(start);
        visited[start] = true;
        for (int i = 0; i < n; i++) {
            // flip ith bit
            int bit = 1 << i;
            int neighbor = start ^ bit;
            dfs(n, neighbor, visited);
        }
    }
}
