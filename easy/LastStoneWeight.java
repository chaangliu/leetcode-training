package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * <p>
 * 20190519
 */
public class LastStoneWeight {
    //1 1 2 4 7 8
    //1 1 1 2 4
    //1 1 1 2
    //1 1 1
    //1

    /**
     * contest 137签到题
     */
    public int lastStoneWeight(int[] stones) {
        List<Integer> list = new ArrayList<>();
        for (int i : stones) list.add(i);
        while (list.size() >= 2) {
            Collections.sort(list);
            int last1 = list.remove(list.size() - 1);
            int last2 = list.remove(list.size() - 1);
            int abs = Math.abs(last1 - last2);
            if (abs != 0) {
                list.add(abs);
            }
        }
        if (list.size() == 1) return list.get(0);
        return 0;
    }
}
