package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 * <p>
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 * <p>
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= costs.length <= 100
 * It is guaranteed that costs.length is even.
 * 1 <= costs[i][0], costs[i][1] <= 1000
 * <p>
 * 20190421 contest
 */
public class TwoCityScheduling {
    /**
     * 这题思路简单，但是我犯了好几个低级错误；导致WA了N次，一度怀疑是不是自己想错了.
     * 我决定一定要早点睡觉，晚睡不仅头脑不清醒而且会心情很差
     */
    public int twoCitySchedCost(int[][] costs) {
        int a = 0, b = 0;
        int res = 0;
        List<int[]> al = new ArrayList<>(), bl = new ArrayList<>();
        for (int[] cost : costs) {
            if (cost[0] < cost[1]) {
                a++;
                res += cost[0];
                al.add(cost);
            } else {
                b++;
                res += cost[1];
                bl.add(cost);
            }
        }
        if (a > b) {
            Collections.sort(al, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return (o1[1] - o1[0]) - (o2[1] - o2[0]);//错误1，o2 o1写错了
                }
            });
            for (int i = 0; i < (a - b) / 2; i++) {//错误2: a-b写成了b-1，并且忘记除以2
                res += al.get(i)[1] - al.get(i)[0];
            }
        } else if (a < b) {
            Collections.sort(bl, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return (o1[0] - o1[1]) - (o2[0] - o2[1]);
                }
            });
            for (int i = 0; i < (b - a) / 2; i++) {
                res += bl.get(i)[0] - bl.get(i)[1];
            }
        }
        return res;
    }
}
