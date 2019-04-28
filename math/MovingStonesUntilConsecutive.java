package math;

import java.util.Arrays;

/**
 * Three stones are on a number line at positions a, b, and c.
 * <p>
 * Each turn, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
 * <p>
 * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
 * <p>
 * When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2, c = 5
 * Output: [1, 2]
 * Explanation: Move stone from 5 to 4 then to 3, or we can move it directly to 3.
 * Example 2:
 * <p>
 * Input: a = 4, b = 3, c = 2
 * Output: [0, 0]
 * Explanation: We cannot make any moves.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= a <= 100
 * 1 <= b <= 100
 * 1 <= c <= 100
 * a != b, b != c, c != a
 * <p>
 * 20190428
 */
public class MovingStonesUntilConsecutive {

    /**
     * contest134签到题，找规律
     */
    public int[] numMovesStones(int a, int b, int c) {

        //a b c不一定递增。。
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        a = arr[0];
        b = arr[1];
        c = arr[2];

        //在纸上画一下得出max就是空格数量，相当于二元组反复颠倒横跳，像一个塔罗牌
        int max = c - b - 1 + b - a - 1;
        //3,5,1 这种情况只要移动一次
        int min = a + 2 == b || b + 2 == c ? 1 : (a + 1 != b ? 1 : 0) + (c - 1 != b ? 1 : 0);
        return new int[]{min, max};
    }
}
