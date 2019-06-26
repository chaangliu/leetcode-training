package array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 * <p>
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
 * <p>
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 * Example 1:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * Example 3:
 * <p>
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 * Example 4:
 * <p>
 * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * Output: true
 * Constraints:
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 * 2019/06/22
 */
public class CarPooling {
    /**
     * 给定车子容量和上车人数、上车下车地点，求车子容量能否满足运输。
     * 一开始感觉像course schedule，以为要用图bfs或union find；后来在厕所想到可以转化成求同一时间车上最多有多少人。
     */
    public boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<Counter> queue = new PriorityQueue<>(trips.length * 2, new Comparator<Counter>() {
            @Override
            public int compare(Counter o1, Counter o2) {
                return o1.pos - o2.pos == 0 ? o1.num - o2.num : o1.pos - o2.pos;//先下后上！！
            }
        });
        for (int[] trip : trips) {
            queue.offer(new Counter(trip[1], trip[0]));
            queue.offer(new Counter(trip[2], -1 * trip[0]));
        }
        int cur = 0;
        while (!queue.isEmpty()) {
            cur += queue.poll().num;
            if (cur > capacity) return false;
        }
        return true;
    }

    class Counter {
        int pos;
        int num;

        public Counter(int p, int n) {
            pos = p;
            num = n;
        }
    }
}
