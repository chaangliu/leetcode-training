package math;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.
 * <p>
 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
 * <p>
 * Given two (axis-aligned) rectangles, return whether they overlap.
 * <p>
 * Example 1:
 * <p>
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * Example 2:
 * <p>
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * Notes:
 * <p>
 * Both rectangles rec1 and rec2 are lists of 4 integers.
 * All coordinates in rectangles will be between -10^9 and 10^9.
 * 20191206
 */
public class RectangleOverlap {
    /**
     * 题意：判断两个长方形是否重叠。两个长方形的边/点相同不算重叠。
     * 我的想法：把rect1的四个顶点确定下来，判断这4个点是否有一个在rec2内。如果都不在，返回false
     * 但是这个想法是必要不充分的，比如两个底边重叠却不一样高的长方形就不行。
     * 下面是lee的1 line做法，他是从1D扩展到2D的，很秀。
     * 重叠的充要条件：
     * x1 < x2'
     * x1'< x2
     * y1 < y2'
     * y1'< y2
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}
