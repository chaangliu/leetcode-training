package other;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Buildings  Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 * 20190817
 */
public class TheSkylineProblem {
    /**
     * 看了个discussion里最简单的Line Sweep扫描线法
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> idx = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int[] b : buildings) {
            idx.offer(new int[]{b[0], -b[2]});//写成负数，一来区分左右，而来在用第二个queue比较的时候，同index可以先出队起点
            idx.offer(new int[]{b[1], b[2]});
        }
        PriorityQueue<Integer> idx2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        idx2.offer(0);//最后一个位置
        int prev = 0;
        while (!idx.isEmpty()) {
            int[] pair = idx.poll();
            if (pair[1] < 0) {
                idx2.offer(-pair[1]);
            } else {
                idx2.remove(pair[1]);//解除高度限制. 这个remove这个api还是第一次用，logn
            }
            int cur = idx2.peek();
            if (cur != prev) {//如果没有被上次最高的区间覆盖
                List<Integer> item = new ArrayList<>();
                item.add(pair[0]);
                item.add(cur);
                res.add(item);
                prev = cur;
            }
        }
        return res;
    }
}
