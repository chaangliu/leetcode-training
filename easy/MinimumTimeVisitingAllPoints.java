package easy;

/**
 * On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time in seconds to visit all points.
 * You can move according to the next rules:
 * In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
 * You have to visit the points in the same order as they appear in the array.
 * 20191124
 */
public class MinimumTimeVisitingAllPoints {
    /**
     * 周赛第一题；
     * 题意：给你一系列二维坐标，要用直线按顺序把他们连起来，每个横向，纵向，对角线方向的用时都是1s。问最短需要多少秒。
     * 这题依旧是个找规律题，但是明显我找的规律很复杂，险些翻车，花了38分钟。。
     * 下面贴上我的代码和讨论区的代码。
     */
    public int minTimeToVisitAllPoints(int[][] p) {
        int res = 0;
        for (int i = 1; i < p.length; i++) {
            boolean xAlign = true;
            int min = Math.abs(p[i][0] - p[i - 1][0]), max = Math.abs(p[i][1] - p[i - 1][1]);//min就是对角线长度
            if (min > max) {
                int tmp = min;
                min = max;
                max = tmp;
                xAlign = false;
            }
            if (xAlign) {
                int y1 = p[i - 1][1] + min, y2 = p[i - 1][1] - min;
                int y = Math.abs(y1 - p[i][1]) < Math.abs(y2 - p[i][1]) ? y1 : y2;
                int dy = Math.abs(y - p[i][1]);
                res += dy + min;
            } else {
                int y1 = p[i - 1][0] + min, y2 = p[i - 1][0] - min;
                int y = Math.abs(y1 - p[i][0]) < Math.abs(y2 - p[i][0]) ? y1 : y2;
                int dy = Math.abs(y - p[i][0]);
                res += dy + min;
            }
        }
        return res;
    }

    public int minTimeToVisitAllPoints_(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int[] cur = points[i], prev = points[i - 1];
            ans += Math.max(Math.abs(cur[0] - prev[0]), Math.abs(cur[1] - prev[1]));
        }
        return ans;
    }
}
