package math;

/**
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.
 * <p>
 * Given a list of three points in the plane, return whether these points are a boomerang.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[2,3],[3,2]]
 * Output: true
 * Example 2:
 * <p>
 * Input: [[1,1],[2,2],[3,3]]
 * Output: false
 * <p>
 * <p>
 * Note:
 * <p>
 * points.length == 3
 * points[i].length == 2
 * 0 <= points[i][j] <= 100
 * <p>
 * 20190511
 */
public class ValidBoomerang {
    /**
     * Approach1. 判断AB和AC的斜率。不需要判断BC。另外，用乘法代替除法，防止/0的错误。
     */
    public boolean isBoomerang(int[][] p) {
        return (p[0][0] - p[1][0]) * (p[0][1] - p[2][1]) != (p[0][0] - p[2][0]) * (p[0][1] - p[1][1]);
    }

    /**
     * Approach2. 行列式，三角形面积S=1/2[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)]
     */
}
