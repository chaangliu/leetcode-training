package easy;

/**
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.
 * Return that integer.
 * Example 1:
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * Constraints:
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 * 20191215
 */
public class ElementAppearingMoreThan25percentInSortedArray {
    /**
     * 题意：找出唯一一个出现次数超过25%的数字。
     * 双周赛签到题，我WA了一发，漏了一个case，arr长度是1的情况。
     */
    public int findSpecialInteger(int[] arr) {
        if (arr.length == 1) return arr[0];
        int max = 0, tmp = 1, res = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                tmp++;
                if (tmp > max) {
                    res = arr[i];
                    max = tmp;
                }
            } else {
                tmp = 1;
            }
        }
        return res;
    }
}
