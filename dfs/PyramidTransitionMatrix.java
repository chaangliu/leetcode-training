package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.
 * We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 * Example 1:
 * Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 * A
 * / \
 * G   E
 * / \ / \
 * B   C   D
 * We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.
 * Example 2:
 * Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 * Note:
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 */
public class PyramidTransitionMatrix {
    /**
     * 题意：给你一系列颜色的底层砖头，问能不能摆成一个金字塔，要求按照Allowed数组来摆放
     * 思路：因为example指出一种底座可能有多种top，比如ABC,ABD；所以摆放了C就会继续改变上层建筑，容易想到用DFS来尝试每种摆法。
     * 我的代码：
     **/
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<Character>> tops = new HashMap<>();
        for (String s : allowed) {
            String b = s.substring(0, 2);
            tops.putIfAbsent(b, new ArrayList<>());
            tops.get(b).add(s.charAt(2));
        }
        return dfs(0, new StringBuilder(), bottom, tops);
    }

    /**
     * dfs返回是否能组成金字塔
     * 一开始我想用一个loop来循环bottom数组，但是发现实际上循环应该用来处理多种dfs路径，这里只要用那种递进式index-dfs就行了，写起来真的清晰又舒服
     */
    private boolean dfs(int start, StringBuilder upper, String bottom, Map<String, List<Character>> allowed) {
        if (bottom.length() == 1) return true;
        //for(int i = start ; i < bottom.length() - 1; i ++){
        if (start >= bottom.length() - 1) {
            return dfs(0, new StringBuilder(), upper.toString(), allowed);
        }
        String b = "" + bottom.charAt(start) + bottom.charAt(start + 1);
        if (!allowed.containsKey(b)) return false;
        for (char c : allowed.get(b)) {
            upper.append(c);
            if (dfs(start + 1, upper, bottom, allowed)) return true;
            upper.deleteCharAt(upper.length() - 1);//backtracking
        }
        return false;
    }


    /**
     * 讨论区的代码
     */
    public boolean pyramidTransition_(String bottom, List<String> allowed) {
        Map<String, Set<Character>> m = new HashMap<>();
        for (String s : allowed) {
            String pre = s.substring(0, 2);
            m.putIfAbsent(pre, new HashSet<>());
            m.get(pre).add(s.charAt(2));
        }
        return dfs(bottom, "", m, 1);
    }

    boolean dfs(String row, String nextRow, Map<String, Set<Character>> allowed, int i) {
        if (row.length() == 1) return true;
        if (nextRow.length() + 1 == row.length())
            return dfs(nextRow, "", allowed, 1);
        for (Character c : allowed.getOrDefault(row.substring(i - 1, i + 1), new HashSet<>()))
            if (dfs(row, nextRow + c, allowed, i + 1))
                return true;
        return false;
    }
}
