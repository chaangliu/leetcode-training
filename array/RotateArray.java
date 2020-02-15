package array;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * 20200215
 */
public class RotateArray {
    /**
     题意：把array往右移动k步数，O(1) space。
     看到inplace,这题我一开始觉得能用swap，但做了发现不能，除非k=len/2，不然没法swap完的。
     所以可以reverse。
     **/
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        reverse(nums, 0,nums.length-1);
        reverse(nums, 0,k-1);
        reverse(nums,k,nums.length-1);
    }
    private void reverse(int [] A, int l , int r){
        while(l < r) {
            int tmp=A[l];
            A[l]=A[r];
            A[r]=tmp;
            l++;r--;
        }
    }
}
