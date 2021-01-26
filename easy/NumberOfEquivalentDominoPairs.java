package easy;

import java.util.HashMap;

/**
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 * <p>
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 * Example 1:
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 * Constraints:
 * <p>
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 * 20190727 IN BUSAN
 */
public class NumberOfEquivalentDominoPairs {
    /**
     * 题意：一些二元组，如果[a,b][c,d]满足a=c b=d或者a=d b=c就算是一对。问一共有几对。
     * 上周contest第一题，n^2会tle,我写了o(n).我看崔神用了最简单的方案，统一先把小的换前面，o(nlogn)。
     */
    public int numEquivDominoPairs__TLE(int[][] dominoes) {
        int res = 0;
        for (int i = 1; i < dominoes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1]
                        || dominoes[i][0] == dominoes[j][1] && dominoes[i][1] == dominoes[j][0])
                    res++;
            }
        }
        return res;
    }

    // leetcode solutions
    public int numEquivDominoPairs____(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    // [[1,2],[2,1],[3,4],[5,6]]
    // [[1,1],[2,2],[1,1],[1,2],[1,2],[1,1]]
    public int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (int[] pair : dominoes) {
            String key1 = pair[0] + "#" + pair[1];
            String key2 = pair[1] + "#" + pair[0];
            if (!map.containsKey(key1)) {
                map.put(key1, 1);
                map.put(key2, 1);
            } else {//else
                res += map.get(key1);
                map.put(key1, map.get(key1) + 1);
                if (!key2.equals(key1)) map.put(key2, map.get(key2) + 1);
            }
        }
        return res;
    }
}
