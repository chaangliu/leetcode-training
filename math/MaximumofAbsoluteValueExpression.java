package math;

/**
 * Given two arrays of integers with equal lengths, return the maximum value of:
 * <p>
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * <p>
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 * Example 1:
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 * Example 2:
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 * Constraints:
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 * 20190723
 */
public class MaximumofAbsoluteValueExpression {
    /**
     * 这完全是一道Math题(代数)，借助电脑计算 |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|的最大值；
     * 数据量40000，所以O(n2)必然超时
     * 那么怎么才能把两个点之间的对比简化？
     * 通过代数把绝对值去掉，有8种可能的符号。其中会发现呈现出expression1 - expression1,第一项和第二项的符号一致；其中4种是重复情况
     * 所以只要取每种符号里的最大值 - 最小值，最后再对比一次即可
     * (https://leetcode.com/problems/maximum-of-absolute-value-expression/discuss/340075/c++-beats-100-(both-time-and-memory)-with-algorithm-and-image)
     */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        int[] items1 = new int[len];//++
        int[] items2 = new int[len];//+-
        int[] items3 = new int[len];//-+
        int[] items4 = new int[len];//--
        for (int i = 0; i < len; i++) {
            items1[i] = arr1[i] + arr2[i] + i;
            items2[i] = arr1[i] + arr2[i] - i;
            items3[i] = arr1[i] - arr2[i] + i;
            items4[i] = arr1[i] - arr2[i] - i;
        }
        int res = Integer.MIN_VALUE;
        int[] maxmin = findMaxMin(items1);
        res = Math.max(res, maxmin[0] - maxmin[1]);
        maxmin = findMaxMin(items2);
        res = Math.max(res, maxmin[0] - maxmin[1]);
        maxmin = findMaxMin(items3);
        res = Math.max(res, maxmin[0] - maxmin[1]);
        maxmin = findMaxMin(items4);
        res = Math.max(res, maxmin[0] - maxmin[1]);
        return res;
    }

    private int[] findMaxMin(int[] arr) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] maxmin = {max, min};
        return maxmin;
    }

    /**
     * O(1) SPACE写法
     */
    public static int maxAbsValExpr__O1SPACE(int[] arr1, int[] arr2) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;
        int n = arr1.length;
        for (int i = 0; i < n; i++) {
            // st scenario arr1[i] + arr2[i] + i
            max1 = Integer.max(arr1[i] + arr2[i] + i, max1);
            min1 = Integer.min(arr1[i] + arr2[i] + i, min1);
            // nd scenario arr1[i] + arr2[i] - i
            max2 = Integer.max(arr1[i] + arr2[i] - i, max2);
            min2 = Integer.min(arr1[i] + arr2[i] - i, min2);
            // rd scenario arr1[i] - arr2[i] - i
            max3 = Integer.max(arr1[i] - arr2[i] - i, max3);
            min3 = Integer.min(arr1[i] - arr2[i] - i, min3);
            // th scenario arr1[i] - arr2[i] + i
            max4 = Integer.max(arr1[i] - arr2[i] + i, max4);
            min4 = Integer.min(arr1[i] - arr2[i] + i, min4);
        }
        int diff1 = max1 - min1;
        int diff2 = max2 - min2;
        int diff3 = max3 - min3;
        int diff4 = max4 - min4;
        return Integer.max(Integer.max(diff1, diff2), Integer.max(diff3, diff4));
    }
}
