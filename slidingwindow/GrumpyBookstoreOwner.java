package slidingwindow;

/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 * <p>
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 * <p>
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 * <p>
 * Return the maximum number of customers that can be satisfied throughout the day.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 * <p>
 * 20190526
 */
public class GrumpyBookstoreOwner {


    /**
     * 像是一道非典型的sliding window，窗口size是固定的X。
     * 1,0,1,2,1,1,7,5
     * 0,1,0,1,0,1,0,1
     * WA只有0次和3+次..corner case自己测试一下再submit
     * 思路想明白。。脑子清醒
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int windowMax = 0;
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) windowMax += customers[i];
        }

        int tmp = windowMax;
        for (int i = X; i < customers.length; i++) {
            tmp += grumpy[i] == 1 ? customers[i] : 0;
            tmp -= grumpy[i - X] == 1 ? customers[i - X] : 0;//customers[i - X], not customers[i]
            windowMax = Math.max(tmp, windowMax);
        }

        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            res += customers[i];
            res -= grumpy[i] == 1 ? customers[i] : 0;
        }
        return res + windowMax;
    }
}
