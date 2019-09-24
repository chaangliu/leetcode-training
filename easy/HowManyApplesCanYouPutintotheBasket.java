package easy;

import java.util.Arrays;

/**
 * You have some apples, where arr[i] is the weight of the i-th apple.  You also have a basket that can carry up to 5000 units of weight.
 * <p>
 * Return the maximum number of apples you can put in the basket.
 * Example 1:
 * <p>
 * Input: arr = [100,200,150,1000]
 * Output: 4
 * Explanation: All 4 apples can be carried by the basket since their sum of weights is 1450.
 * Example 2:
 * <p>
 * Input: arr = [900,950,800,1000,700,800]
 * Output: 5
 * Explanation: The sum of weights of the 6 apples exceeds 5000 so we choose any 5 of them.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^3
 * 1 <= arr[i] <= 10^3
 * 20190922
 */
public class HowManyApplesCanYouPutintotheBasket {
    /**
     * 这题不是背包问题..贪心就行了（如果标记成medium估计我就要被误导..）
     */
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int w = 0, res = 0;
        for (int num : arr) {
            if (w + num <= 5000) {
                res++;
                w += num;
            }
        }
        return res;
    }
}
