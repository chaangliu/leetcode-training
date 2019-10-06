package easy;

/**
 * There are some chips, and the i-th chip is at position chips[i].
 * <p>
 * You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
 * <p>
 * Move the i-th chip by 2 units to the left or to the right with a cost of 0.
 * Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
 * There can be two or more chips at the same position initially.
 * <p>
 * Return the minimum cost needed to move all the chips to the same position (any position).
 * Example 1:
 * Input: chips = [1,2,3]
 * Output: 1
 * Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
 * Example 2:
 * <p>
 * Input: chips = [2,2,2,3,3]
 * Output: 2
 * Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
 * Constraints:
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 * 20191006
 */
public class PlaywithChips {
    /**
     * 题意：有一个序列保存了筹码(chips)的位置，筹码移动2个位置需要0花费，移动1个位置需要1花费。问把所有筹码移动到一块儿的最小花销。
     * 这题首先题意我看了半天， 然后switch到中文版才发现array中保存的是position，然后就找到策略了，odd位置上的移动到even位置上或者反过来。
     * 是个签到题但是contest很多人题目没看懂。
     */
    public int minCostToMoveChips(int[] chips) {
        int odds = 0, even = 0;
        for (int n : chips) {
            if ((n & 1) == 1) odds++;
            else even++;
        }
        return Math.min(odds, even);
    }
}
