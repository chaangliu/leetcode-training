package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * <p>
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * <p>
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 * <p>
 * 20190311
 */
public class _24Game {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (Integer i : nums) list.add(i * 1.0);
        return dfs(list);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 0) return false;
        //浮点型的0 在内存中并不是严格等于0的，比较完全相等通常不可靠.因此可以认为当一个浮点数离原点足够近时,也就是f>0.00001 && f<-0.00001,认为f是0
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;
        for (int i = 0; i < nums.size(); i++)
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;
                //预留两个数做运算，把剩下的数（可能有0个1个或者2个）加入下一次递归
                List<Double> nums2 = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) nums2.add(nums.get(k));
                }
                //把前面预留的数拿来运算，并且加入到下次递归
                for (int k = 0; k < 4; k++) {
                    //此处可对加法和乘法这种不分顺序的符号做优化if (k < 2 && j > i) continue;
                    if (k == 0) {
                        nums2.add(nums.get(i) + nums.get(j));
                    } else if (k == 1) {
                        nums2.add(nums.get(i) - nums.get(j));
                    } else if (k == 2) {
                        nums2.add(nums.get(i) * nums.get(j));
                    } else if (nums.get(j) != 0) {
                        nums2.add(nums.get(i) / nums.get(j));
                    } else continue;
                    if (dfs(nums2)) return true;
                    nums2.remove(nums2.size() - 1);
                }
            }
        return false;
    }

    public static void main(String args[]) {
        System.out.print(new _24Game().judgePoint24(new int[]{1, 2, 1, 2}));
    }
}
