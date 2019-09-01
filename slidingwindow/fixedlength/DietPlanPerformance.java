package slidingwindow.fixedlength;

/**
 * A dieter consumes calories[i] calories on the i-th day.  For every consecutive sequence of k days, they look at T, the total calories consumed during that sequence of k days:
 * <p>
 * If T < lower, they performed poorly on their diet and lose 1 point;
 * If T > upper, they performed well on their diet and gain 1 point;
 * Otherwise, they performed normally and there is no change in points.
 * Return the total number of points the dieter has after all calories.length days.
 * <p>
 * Note that: The total points could be negative.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
 * Output: 0
 * Explaination: calories[0], calories[1] < lower and calories[3], calories[4] > upper, total points = 0.
 * Example 2:
 * <p>
 * Input: calories = [3,2], k = 2, lower = 0, upper = 1
 * Output: 1
 * Explaination: calories[0] + calories[1] > upper, total points = 1.
 * Example 3:
 * <p>
 * Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
 * Output: 0
 * Explaination: calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, total points = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= calories.length <= 10^5
 * 0 <= calories[i] <= 20000
 * 0 <= lower <= upper
 * 20190901
 */
public class DietPlanPerformance {
    class Solution {
        public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
            int sum = 0, res = 0;
            for (int i = 0; i < k; i++) {
                sum += calories[i];
            }
            System.out.println(sum);
            if (sum < lower) res--;
            else if (sum > upper) res++;
            for (int i = 1; i + k - 1 < calories.length; i++) {
                sum -= calories[i - 1];
                sum += calories[i + k - 1];
                System.out.println(sum);
                if (sum < lower) res--;
                else if (sum > upper) res++;
            }
            return res;
        }
    }
}
