package math;

/**
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 * Example 3:
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 * Example 4:
 * Input: hour = 4, minutes = 50
 * Output: 155
 * Example 5:
 * Input: hour = 12, minutes = 0
 * Output: 0
 * Constraints:
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * 20200425
 */
public class AngleBetweenHandsofaClock {
    /**
     * 题意：给你时针和分针，问两者较小的角度是多少。
     * 解法：math，分别计算距离0点的角度∠就好，要注意精度保留五位小数。
     */
    public double angleClock(int h, int m) {
        double hDeg = h == 12 ? 0 : (h / 12.0f * 360);
        hDeg += m / 60.0f * 30;
        double mDeg = m / 60.0f * 360;
        double deg = Math.round(Math.abs(hDeg - mDeg) * 10000) * 0.0001d;
        return deg <= 180 ? deg : 360f - deg;
    }

    /**
     * 另一个答案，他先计算每个格子的角度
     */
    public double angleClock_(int hour, int minutes) {
        // Degree covered by hour hand (hour area + minutes area)
        double h = (hour % 12 * 30) + ((double) minutes / 60 * 30);
        // Degree covered by minute hand (Each minute = 6 degree)
        double m = minutes * 6;
        // Absolute angle between them
        double angle = Math.abs(m - h);
        // If the angle is obtuse (>180), convert it to acute (0<=x<=180)
        if (angle > 180) angle = 360.0 - angle;
        return angle;
    }
}
