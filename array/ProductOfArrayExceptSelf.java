package array;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 * <p>
 * ref: https://blog.csdn.net/wzy_1988/article/details/46916179
 */
public class ProductOfArrayExceptSelf {


    //    [a,b,c,d]
    //    [1, a, ab, abc]
    //  ->[bcd, cd, d, 1]
    //    #1 两个数组相乘
//    public int[] productExceptSelf(int[] nums) {
//        if (nums == null) return null;
//        int len = nums.length;
//        int[] res1 = new int[len];
//        int[] res2 = new int[len];
//        res1[0] = 1;
//        res2[len - 1] = 1;
//        for (int i = 1; i < len; i++) {
//            res1[i] = nums[i - 1] * res1[i - 1];
//        }
//        for (int i = len - 2; i >= 0; i--) {
//            res2[i] = nums[i + 1] * res2[i + 1];
//        }
//
//        for (int i = 0; i < len; i++) {
//            res1[i] = res1[i] * res2[i];
//        }
//        return res1;
//    }

    //     follow up(optimization), using one array
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) return null;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }
}
