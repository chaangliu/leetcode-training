package easy;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
 * Example 1:
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * Example 2:
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 * Constraints:
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 * 20191020
 */
public class CheckIfItIsaStraightLine {
    /**
     * 题意：判断所有的点是否在一条直线上。
     * 解法，这题技巧是需要把求斜率转换一下，除法变乘法，这样可以避免divided by zero，以及无需使用double类型。
     */
    public boolean checkStraightLine(int[][] co) {
        int p = co[0][0], q = co[0][1], u = co[1][0], v = co[1][1];
        for (int[] c : co) {
            if ((c[0] - p) * (c[1] - v) != (c[0] - u) * (c[1] - q))
                return false;
        }
        return true;
    }

    public boolean checkStraightLine_SLOW(int[][] coordinates) {
        if (coordinates[1][1] - coordinates[0][1] == 0) {
            for (int[] c : coordinates) {
                if (c[1] != coordinates[0][1]) return false;
            }
            return true;
        }
        double stair = (coordinates[1][1] - coordinates[0][1]) * 1.0 / (coordinates[1][0] - coordinates[0][0]);
        for (int i = 1; i < coordinates.length; i++) {
            int[] b = coordinates[i], a = coordinates[i - 1];
            if ((b[0] - a[0]) == 0) return false;
            if ((b[1] - a[1]) / (b[0] - a[0]) != stair) return false;
        }
        return true;
    }
}
