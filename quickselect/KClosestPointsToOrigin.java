package quickselect;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * <p>
 * 20190219
 */
public class KClosestPointsToOrigin {

    //这题其实就是个求k小元素的经典题

    /**
     * approach1.排序，O(n*logn)
     */
    public int[][] kClosest__SLOW(int[][] points, int K) {
        //Array就用Arrays.sort，Collection就用Collections.sort
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] * o1[0] + o1[1] * o1[1] - o2[0] * o2[0] - o2[1] * o2[1];
            }
        });
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[i];
        }
        return res;
    }

    /**
     * approach2.quick select，O(n)
     */
    public int[][] kClosest(int[][] points, int K) {
        helper(points, K, 0, points.length - 1);
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[i];
        }
        return res;
    }

    private void helper(int[][] points, int k, int low, int high) {
        int slot = partition(points, low, high);
        if (slot + 1 == k) {
            //这时候前K个数已经满足最小了，但不一定有序，这是题目允许的
            return;
        } else if (slot + 1 < k) {
            helper(points, k, slot + 1, high);
        } else {
            helper(points, k, low, slot - 1);
        }
    }

    private int partition(int[][] points, int low, int high) {
        int pivot = dist(points[high]);
        int slot = low;
        for (int i = low; i < high; i++) {//已犯错误，这里手快就写成i = 0了
            if (dist(points[i]) < pivot) {
                swap(points, i, slot++);
            }
        }
        swap(points, slot, high);
        return slot;
    }

    private void swap(int[][] arr, int a, int b) {
        int[] tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private int dist(int[] cord) {
        return cord[0] * cord[0] + cord[1] * cord[1];
    }

    /**
     * approach3. maxheap，思路跟KthLargestElementInAnArray一样，O(nlogK)
     */
    public int[][] kClosest_heap(int[][] points, int K) {
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] b, int[] a) {
                return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
            }
        });

        for (int[] p : points) {
            if (q.size() >= K) {
                int[] a = q.peek(), b = p;
                if (a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1] > 0) {
                    q.poll();
                    q.offer(b);
                }
            } else {
                q.offer(p);
            }
            // System.out.println("offering " + p[0] + ", " + p[1]);
        }
        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; i--) res[i] = q.poll();
        return res;
    }
}
