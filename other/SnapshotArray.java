package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 * 20190804
 */
public class SnapshotArray {
    Map<Integer, Integer> item = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> snaps = new HashMap<>();
    int snap_id = 0;

    public SnapshotArray(int length) {

    }

    public void set(int index, int val) {
        item.put(index, val);
    }

    public int snap() {
        snaps.put(snap_id++, item);
        item = new HashMap<>();
        Set<Map.Entry<Integer, Integer>> set = snaps.get(snap_id - 1).entrySet();
        for (Map.Entry<Integer, Integer> entry : set) {
            item.put(entry.getKey(), entry.getValue());
        }
        return snap_id - 1;
    }

    public int get(int index, int snap_id) {
        if (!snaps.containsKey(snap_id) || !snaps.get(snap_id).containsKey(index)) return 0;
        return snaps.get(snap_id).get(index);
    }

    /**
     * ["SnapshotArray","snap","snap","get","set","snap","set"]
     * [[4],[],[],[3,1],[2,4],[],[1,4]]
     * <p>
     * ["SnapshotArray","set","snap","snap","snap","get","snap","snap","get"]
     * [[1],[0,15],[],[],[],[0,2],[],[],[0,0]]
     */
    public static void main(String args[]) {
        SnapshotArray obj = new SnapshotArray(1);
        obj.set(0, 15);
        obj.snap();
        obj.snap();
        obj.snap();
        obj.get(0, 2);
    }

    /**
     * 看到一个从index角度来保存的，snapId做key，但我不知道他用treeMap有什么优势 https://leetcode.com/problems/snapshot-array/discuss/350648/java-o1-snap-ologn-getset-using-treemap/319027
     */
    class SnapshotArray__TREEMAP {

        List<TreeMap<Integer, Integer>> arr;
        int currId = 0;

        public SnapshotArray__TREEMAP(int length) {
            arr = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                arr.add(i, new TreeMap<>());
                arr.get(i).put(0, 0);
            }
        }

        public void set(int index, int val) {
            arr.get(index).put(currId, val);
        }

        public int snap() {
            return currId++;
        }

        public int get(int index, int snap_id) {
            return arr.get(index).floorEntry(snap_id).getValue();
        }
    }

}
