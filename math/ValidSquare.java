package math;

/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * <p>
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 * <p>
 * Example:
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * Note:
 * <p>
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 * <p>
 * 20190406
 */
public class ValidSquare {
    /**
     * 这题记得第一次做的时候很痛苦，WA了七八次；今天bugfree一次通过了；用的是自己用Spen画出来的思路，没有用答案里HashMap的思路。
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int a = dist(p1, p2), b = dist(p1, p3), c = dist(p1, p4);
        int oppsite = 0;
        if (a == b && a + b == c) {
            oppsite = 4;
        } else if (a == c && a + c == b) {
            oppsite = 3;
        } else if (b == c && b + c == a) {
            oppsite = 2;
        }
        if (oppsite != 0) {
            switch (oppsite) {
                case 4:
                    return dist(p4, p2) == dist(p4, p3) && notSame(p2, p3);
                case 3:
                    return dist(p3, p2) == dist(p3, p4) && notSame(p2, p4);
                case 2:
                    return dist(p2, p3) == dist(p2, p4) && notSame(p3, p4);
            }
        }
        return false;
    }

    private int dist(int p1[], int p2[]) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    private boolean notSame(int p1[], int p2[]) {
        return !(p1[0] == p2[0] && p1[1] == p2[1]);
    }
}
