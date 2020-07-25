package math;

import java.util.HashMap;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * <p>
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * <p>
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 * <p>
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * Note:
 * <p>
 * 1 <= N <= 1000
 * <p>
 * 20190414
 */
public class DivisorGame {
    /**
     * 这种题如果不看它的标签是EASY，你不知道它的深浅。
     * 如果是Easy通常就是找规律，然后证明它。
     */
    public boolean divisorGame(int N) {
        //如果N是Alice赢，那么N + 1一定是Alice输，因为Bob可以直接让N - 1
        return (N & 1) == 0;
    }

    /**
     * dfs with memo， minmax
     */
    public boolean divisorGame_(int N) {
        HashMap<Integer, Boolean> memo = new HashMap<>();
        memo.put(1, false);
        return canWin(N, memo);
    }

    private boolean canWin(int N, HashMap<Integer, Boolean> memo) {
        if (memo.containsKey(N)) return memo.get(N);
        boolean res = false;
        for (int i = 1; i <= (int) Math.sqrt(N); i++) {
            if (N % i != 0) continue;
            if (!canWin(N - i, memo)) {
                res = true;
                break;
            }
        }
        memo.put(N, res);
        return res;
    }
}
