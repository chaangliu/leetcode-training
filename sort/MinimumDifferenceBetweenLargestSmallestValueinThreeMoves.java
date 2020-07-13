package sort;

import java.util.Arrays;

public class MinimumDifferenceBetweenLargestSmallestValueinThreeMoves {
    /**
     * 题意：给你一个数组，你可以改变三个数字的值，问最大值和最小值的差的最小值。
     * 解法：我一开始想的是，先排序，然后把最小值换成最大值，或者把最大值换成最小值，如此执行8次即可；
     * 于是写了个backtrack，结果发现思路有问题，并不能cover所有case，
     * 因为有时候两端的数字不一定要变成最大或最小值，有时候可能需要变成中间的某个值。
     * 看了lee的答案，他是直接把两端的数字删掉，这样就相当于变成了数组中的某个值，你不需要知道那个值。
     * 四种情况：
     * 1. 左边删掉3个
     * 2. 左边删掉2个右边删掉1个
     * 3. 左边删掉1个右边删掉2个
     * 4. 右边删掉3个
     **/
    public int minDifference(int[] A) {
        Arrays.sort(A);
        int n = A.length, res = A[n - 1] - A[0];
        if (n <= 4) return 0;
        // 1 2 3 4 5
        res = Math.min(res, A[n - 1] - A[3]);
        res = Math.min(res, A[n - 2] - A[2]);
        res = Math.min(res, A[n - 3] - A[1]);
        res = Math.min(res, A[n - 4] - A[0]);
        return res;
    }
}
