package dp.dfswithmemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 * <p>
 * What if we change the game so that players cannot re-use integers?
 * <p>
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 * <p>
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 * <p>
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * <p>
 * Example
 * <p>
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * <p>
 * Output:
 * false
 * <p>
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 * TAG:[MINMAX][DP]
 * 20190919
 */
public class CanIWin {
    /**
     * 题意：两个人从1到maxChoosableInteger轮流选择数字(不放回)，先达到desiredTotal的人赢。问先手的人能否稳赢，能的话返回true。
     * 这题的关键是，找到一种情况我能赢或者下一层递归所有情况对方都不能赢。这个状态是轮换并且互斥的。
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) return false;
        return helper(desiredTotal, 0, maxChoosableInteger, new HashMap<>());
        //return helper1(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
    }

    /**
     * 我有任意一种方案能赢 或者对方无论如何都不能赢，那么返回true，否则返回false
     * 用一个int来标记visited，map保存每种visited样式能否赢
     */
    private boolean helper(int total, int state, int maxChoosable, HashMap<Integer, Boolean> map) {
        //重复子问题出现的情况：比如我和对手分别选了第2和第3个数字这样能保证我稳赢，那么下次再出现这种情形(有可能选择的顺序不一样)直接知道我[可能]赢。
        //比如我选了99，对手选了1，我选了98，这样state是100..0011，代表我可能赢，但不是一定赢，比如我选了1和98就不赢，但是没关系，不用再继续选下去了：从别的岔路走到这个路口的某一个我，已经有一个赢过了。
        if (map.containsKey(state))
            return map.get(state);
        for (int i = 0; i < maxChoosable; i++) {
            if ((state & (1 << i)) == 0) {//不要((state >> i) & 1)
                if (total <= i + 1 || !helper(total - (i + 1), state | 1 << i, maxChoosable, map)) {//state | 1 << i 不用手动回溯了
                    map.put(state, true);
                    return true;
                }
            }
        }
        map.put(state, false);
        return false;
    }

    /**
     * 如果使用array替代visited
     */
    private boolean helper1(int total, int[] state, HashMap<String, Boolean> hashMap) {
        String curr = Arrays.toString(state);
        if (hashMap.containsKey(curr)) return hashMap.get(curr);
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                if (total <= i + 1 || !helper1(total - (i + 1), state, hashMap)) {//我有任意一种方案能赢 或者对方无论如何都不能赢
                    hashMap.put(curr, true);
                    state[i] = 0;
                    return true;
                }
                state[i] = 0;
            }
        }
        hashMap.put(curr, false);
        return false;
    }


    /**
     * -------------------------------------------------------------------------------------------------------------------------------------
     * 【错误】我的代码思路，求对方是否有可能赢，如果是则返回false。但是这个思路有问题，题目要求的并不是所有情况我都要赢，而是有一种情况我能保证赢就可以。
     **/
    public boolean canIWin__WA(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        return helper(new HashSet<>(), maxChoosableInteger, desiredTotal, 0, 0);
    }

    private boolean helper(Set<Integer> visited, int maxChoosableInteger, int desiredTotal, int step, int sum) {
        if (sum >= desiredTotal) {
            return (step & 1) == 1;
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (visited.contains(i)) continue;
            visited.add(i);
            if (!helper(visited, maxChoosableInteger, desiredTotal, step + 1, sum + i)) return false;
            visited.remove(i);
        }
        return true;
    }
}
