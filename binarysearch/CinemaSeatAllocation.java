package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 * <p>
 * Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i]=[3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 * <p>
 * Return the maximum number of four-person families you can allocate on the cinema seats. A four-person family occupies fours seats in one row, that are next to each other. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be next to each other, however, It is permissible for the four-person family to be separated by an aisle, but in that case, exactly two people have to sit on each side of the aisle.
 * Example 1:
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four families, where seats mark with blue are already reserved and contiguous seats mark with orange are for one family.
 * Example 2:
 * <p>
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 * Example 3:
 * <p>
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * All reservedSeats[i] are distinct.
 * 202020322
 */
public class CinemaSeatAllocation {
    /**
     * 题意：给你一些电影院座位的预订信息，有些已经被预订了，问最多能坐几个一家四口。
     * 解法：直接linear scan的话很容易WA/超时。可以
     * 对于每一行被占的位置，先排序再做binary search，搜索2，4，8这几个位置。没有人占领的行数就+2.
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int[] arr : reservedSeats) {
            map.putIfAbsent(arr[0], new ArrayList<>());
            map.get(arr[0]).add(arr[1]);
        }

        int cnt = 0, rows = 0;
        for (ArrayList<Integer> item : map.values()) {
            rows++;
            Collections.sort(item);
            for (int i = 2; i <= 6; ) {
                int lb = lower_bound(item, i);
                if (lb == item.size() || item.get(lb) - i >= 4) { // item.get(lb) - i => 接下来的4个seats都没人占领
                    cnt++;
                    i += 4;
                } else {
                    i += 2;
                }
            }
        }
        cnt += ((n - rows) * 2); //没人占的rows都加可以坐两家人
        return cnt;
    }

    private int lower_bound(List<Integer> A, int target) {
        int lo = 0, hi = A.size();
        while (lo < hi) {// lower_bound = 第一个>=target的位置
            int mid = lo + (hi - lo) / 2;
            if (A.get(mid) >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
