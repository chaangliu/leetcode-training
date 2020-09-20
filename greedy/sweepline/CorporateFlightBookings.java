package greedy.sweepline;

import java.util.HashMap;
import java.util.Map;

/**
 * There are n flights, and they are labeled from 1 to n.
 * <p>
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * <p>
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 * 20190707
 */
public class CorporateFlightBookings {
    /**
     * 这题一开始写了两种方式都是TLE。后来纸上画画发现可以用类似之前搭车那题的思路O(n)做。据说这种思路叫贪心。
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < bookings.length; i++) {
            int l = bookings[i][0];
            int r = bookings[i][1];
            int count = bookings[i][2];
            if (!map.containsKey(l)) map.put(l, 0);
            if (!map.containsKey(r + 1)) map.put(r + 1, 0);
            map.put(l, map.get(l) + count);
            map.put(r + 1, map.get(r + 1) - count);
        }
        int sum = 0;
        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(i))
                sum += map.get(i);
            res[i - 1] = sum;
        }
        return res;
    }
}
