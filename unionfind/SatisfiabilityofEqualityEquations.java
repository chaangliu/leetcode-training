package unionfind;

/**
 * Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
 * Example 1:
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
 * Example 2:
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * Example 3:
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 * Example 4:
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 * Example 5:
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 * Note:
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] and equations[i][3] are lowercase letters
 * equations[i][1] is either '=' or '!'
 * equations[i][2] is '='
 * 20200610
 */
public class SatisfiabilityofEqualityEquations {
    /**
     * 题意：给你一些等式和不等式，两端是单个英文字母，问这些等式不等式有没有相悖。
     * 解法：Union Find，先把等式两端的字母union起来，然后把去检查不等式两端的root是否相同，如果不同，那么就有悖了。
     */
    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; i++) rootOf[i] = i;
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                if (find(s.charAt(0) - 'a') == find(s.charAt(3) - 'a')) return false;
            }
        }
        return true;
    }

    int[] rootOf = new int[26];

    private int find(int i) {
        if (rootOf[i] == i) return i;
        return find(rootOf[i]);
    }

    private boolean union(int x, int y) {
        int ra = find(x), rb = find(y);
        if (ra == rb) return false;
        rootOf[ra] = rb;
        return true;
    }
}
