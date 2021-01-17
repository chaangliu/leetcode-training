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
     * 解法，这题技巧是需要把求斜率转换一下，对于每个点，以前两个点为基准计算斜率，除法变乘法，这样可以避免divided by zero，以及无需使用double类型。
     */
    public boolean checkStraightLine(int[][] co) {
        int x0 = co[0][0], y0 = co[0][1], x1 = co[1][0], y1 = co[1][1];
        for (int[] c : co) {
            if ((c[0] - x0) * (c[1] - y1) != (c[0] - x1) * (c[1] - y0))
                return false;
        }
        return true;
    }

    /**
     * 用除法，要注意*1.0d
     */
    public boolean checkStraightLine__(int[][] coordinates) {
        int n = coordinates.length;
        if (coordinates[1][0] - coordinates[0][0] == 0) {
            for (int i = 2; i < n; i++)
                if (coordinates[i][0] - coordinates[0][0] != 0) return false;
            return true;
        }
        double k = (coordinates[1][1] - coordinates[0][1]) * 1.0d / (coordinates[1][0] - coordinates[0][0]);
        for (int i = 2; i < n; i++) {
            if ((coordinates[i][1] - coordinates[0][1]) * 1.0d / (coordinates[i][0] - coordinates[0][0]) != k)
                return false;
        }
        return true;
    }
}
