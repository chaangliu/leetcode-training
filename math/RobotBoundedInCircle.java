package math;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * Example 1:
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * Example 2:
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 * Example 3:
 * Input: "GL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 * Note:
 * 1 <= instructions.length <= 100
 * instructions[i] is in {'G', 'L', 'R'}
 * 20191126
 */
public class RobotBoundedInCircle {
    /**
     * 题意：一个无限大的平面上有一个机器人从(0,0)开始重复按照指令无限地走，问你这个机器人的路径是否在一个圈子内，也就是它在循环行走。
     * 解法：这题tag是Math，有个不太容易看出的结论，就是只要机器人执行完一次指令后脸的朝向不是north，那它一定会在1次(face south)或3次(face east/west)指令之后重回老路。
     * 所以，只要判断一次执行完之后的位置(face north && 位置是0,0的话也满足条件)和方向就行了。
     **/
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int j = 0;
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'L') {
                j = (j + 3) % 4;//or : j = j - 1 < 0 ? 4 + (j - 1) % 4 : (j - 1) % 4;
            } else if (instructions.charAt(i) == 'R') {//已犯错误：这边应该是=='R',复制了上边的'L'，检查了10分钟没检查出来..咋办啊
                j = (j + 1) % 4;
            } else {
                x += dirs[j][0];
                y += dirs[j][1];
            }
        }
        return x == 0 && y == 0 || j != 0;
    }
}
