package array;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to closest person.
 * Example 1:
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Note:
 * 1 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 * 20191210
 */
public class MaximizeDistancetoClosestPerson {
    /**
     * 题意：一排座位，1代表有人0代表没人，让你挑一个位置，能够离人最远。
     * 这题虽然是easy，但我觉得非常适合面试。。因为有很多corner cases。
     * 最容易注意到的是eaxample2中的开头结尾是0.
     * 但是还有一些情况，比如[0,1,1,1,0,0,1,0,0]，这种优先坐在结尾。
     * 还有[0,0,1,0,0,0,1,0]，不能单纯选最长的。
     * res = max(distance at the beginning, distance in the middle / 2, distance at the end).
     * 我的做法，三次遍历。
     * 我觉得如果能做到one pass才能strong hire..
     */
    public int maxDistToClosest(int[] seats) {
        int cur = 0, max = 0;
        int leading = 0, trailing = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                cur++;
                if (cur > max) {
                    max = cur;
                }
            } else {
                cur = 0;
            }
        }
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) leading++;
            else break;
        }
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 0) trailing++;
            else break;
        }
        return Math.max(Math.max(leading, trailing), (max + (2 - 1)) / 2);
    }

    /**
     * lee的做法, one pass，真滴巧妙
     * last代表最后一个坐了人的座位的index，利用(i - last) / 2  来搞定leading和trailing zeros
     */
    public int maxDistToClosest__(int[] seats) {
        int res = 0, n = seats.length, last = -1;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                res = last < 0 ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        res = Math.max(res, n - last - 1);//防止结尾是0
        return res;
    }
}