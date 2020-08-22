package cc150;

/**
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 * 示例1:
 * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 * 输出: 8（元素5在该数组中的索引）
 * 示例2:
 * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 * 输出：-1 （没有找到）
 * 提示:
 * arr 长度范围在[1, 1000000]之间
 */
public class SearchRotateArray {
    /**
     * 题意：搜索旋转数组中的数字，如果有重复，返回index最小的那个。
     * cc150的这题跟lc上的还不太一样，如果单纯用lc的那种解法，[5,5,5,1,2,3,4,5] 5 的case会返回最后一个5的index而不是第一个。
     * 下面的解法参考https://leetcode-cn.com/problems/search-rotate-array-lcci/solution/er-fen-fa-by-armeria-program/
     * 对于A[lo] = A[mid]的情况，优先判断左边和target是否相等，如果不想等就右移lo，否则就是找到了。
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (right == -1)
            return -1;
        while (left < right) {                                         // 循环结束条件left==right
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {                              // 如果左值小于中值，说明左边区间升序
                if (nums[left] <= target && target <= nums[mid]) {     // 如果目标在左边的升序区间中，右边界移动到mid
                    right = mid;
                } else {                                               // 否则目标在右半边，左边界移动到mid+1
                    left = mid + 1;
                }
            }
            // 下面这部分，也跟lc的不一样；没有跟A[hi]比，而是仍然跟A[lo]比
            else if (nums[left] > nums[mid]) {                       // 如果左值大于中值，说明左边不是升序，右半边升序
                if (nums[left] <= target || target <= nums[mid]) {     // 如果目标在左边，右边界移动到mid
                    right = mid;
                } else {                                               // 否则目标在右半边，左边界移动到mid+1
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {                      // 如果左值等于中值，可能是已经找到了目标，也可能是遇到了重复值
                if (nums[left] != target) {                            // 如果左值不等于目标，说明还没找到，需要逐一清理重复值。
                    left++;
                } else {                                               // 如果左值等于目标，说明已经找到最左边的目标值
                    return left;
                }
            }
        }
        return (nums[left] == target) ? left : -1;                     // 返回left，或者-1
    }
}
