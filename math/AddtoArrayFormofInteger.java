package math;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 */
public class AddtoArrayFormofInteger {
    /**
     * 题意：给你一个数组和一个数字，求和，和也用数组表示。
     * 思路：每次把K的最后一位和数组的最后一位相加得到sum，然后可以把K的最后一位省略掉，进一步处理。
     * 另一种思路是把最后一位与K相加。
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int n = A.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            res.add(0, sum);
        }
        for (; K > 0; K /= 10) { // 设想K非常大
            res.add(0, K % 10);
        }
        return res;
    }
}
