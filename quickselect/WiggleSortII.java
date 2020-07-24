package quickselect;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 * <p>
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * <p>
 * 20190411
 */
public class WiggleSortII {
    /**
     * 这题根据StefanPochmann的思路来，他证明了按照下面这种形式(先倒序，再把小的部分放到前一半odd的slot里，大的部分放到后一半even的slot里)就能通过。
     * LLMM MMSS
     * MLML SMSM
     * <p>
     * 我偷懒直接sort了，如果借助quick select之类的方法可以达到O(n)，不过我感觉难度有点大
     */
    public void wiggleSort(int[] nums) {
        Integer nums1[] = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) nums1[i] = nums[i];
        Arrays.sort(nums1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int i = 0, j = nums1.length / 2, idx = 0;
        while (j < nums.length || i < nums.length / 2) {
            nums[idx] = (idx & 1) == 0 ? nums1[j++] : nums1[i++];
            idx++;
        }
    }
}

/**
 * wiggle sort I被锁了，类似题目可以看https://leetcode-cn.com/problems/peaks-and-valleys-lcci/
 */
//class Solution {
//    public:
//    void wiggleSort(vector<int>& nums) {
//        for(int i=1;i<nums.size();i++){
//            if(i%2==0){
//                if(nums[i]<nums[i-1]) swap(nums[i],nums[i-1]);
//            }
//            else{
//                if(nums[i]>nums[i-1]) swap(nums[i],nums[i-1]);
//            }
//        }
//    }
//};
