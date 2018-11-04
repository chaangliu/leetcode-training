package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Write a class RecentCounter to count recent requests.
 * <p>
 * It has only one method: ping(int t), where t represents some time in milliseconds.
 * <p>
 * Return the number of pings that have been made from 3000 milliseconds ago until now.
 * <p>
 * Any ping with time in [t - 3000, t] will count, including the current ping.
 * <p>
 * It is guaranteed that every call to ping uses a strictly larger value of t than before.
 * <p>
 * Example 1:
 * <p>
 * Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
 * Output: [null,1,2,3,3]
 * <p>
 * <p>
 * Note:
 * <p>
 * Each test case will have at most 10000 calls to ping.
 * Each test case will call ping with strictly increasing values of t.
 * Each call to ping will have 1 <= t <= 10^9.
 */
public class RecentCounter {
    private List<Integer> mRecords;

    // brute force
//    public RecentCounter() {
//        if (mRecords == null)
//            mRecords = new ArrayList<>();
//    }
//
//    public int ping(int t) {
//        mRecords.add(t);
//        //这里调试了好几次..
//        if (mRecords.get(mRecords.size() - 1) <= 3001 || mRecords.get(0) >= t - 3000) {
//            return mRecords.size();
//        }
//        for (int i = 0; i < mRecords.size() - 1; i++) {
//            if (mRecords.get(i) == t - 3000) {
//                return mRecords.size() - i + 1;
//            }
//            if (mRecords.get(i) < t - 3000 && mRecords.get(i + 1) >= t - 3000) {
//                return mRecords.size() - i - 1;
//            }
//        }
//        return 1;
//    }

//    ------------

    //看到一种TreeMap解法。时间O(logN),TreeMap是红黑树实现的，treemap中的tailmap是返回大于某个key的子map
    TreeMap<Integer, Integer> tm;

    public RecentCounter() {
        tm = new TreeMap<>();
    }

    public int ping(int t) {
        tm.put(t, 1 + tm.size());
        return tm.tailMap(t - 3000).size();
    }

//    Or similarly use TreeSet instead.

//    TreeSet<Integer> ts;
//
//    public RecentCounter() {
//        ts = new TreeSet<>();
//    }
//
//    public int ping(int t) {
//        ts.add(t);
//        return ts.tailSet(t - 3000).size();
//    }


    public static void main(String args[]) {
        List<Integer> res = new ArrayList<>();
        RecentCounter counter = new RecentCounter();
        res.add(counter.ping(1178));
        res.add(counter.ping(1483));
        res.add(counter.ping(1563));
        res.add(counter.ping(4054));
        res.add(counter.ping(4152));
        new RecentCounter().ping(3002);
    }

    /**
     * 二分难点在于如果找不到这个数怎么处理
     */
//    private int binarySearch(int t) {
//        int lo = 0, hi = mRecords.size() - 1, mid = lo + (hi - lo) / 2;
//        while (lo <= hi) {
//            if (mRecords.get(mid) == t) {
//                return mid;
//            }
//            if (mRecords.get(mid) < t) {
//                lo = mid + 1;
//            } else {
//                hi = mid -;
//            }
//        }
//        return -1;
//    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */