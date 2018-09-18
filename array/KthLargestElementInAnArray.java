package array;

//Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//        Example 1:
//
//        Input: [3,2,1,5,6,4] and k = 2
//        Output: 5
//        Example 2:
//
//        Input: [3,2,3,1,2,4,5,5,6] and k = 4
//        Output: 4
//        Note:
//        You may assume k is always valid, 1 ≤ k ≤ array's length.

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (k < 1 || k > nums.length || nums == null) return Integer.MAX_VALUE;
        return helper(nums, 0, nums.length - 1, k);
    }

    //要注意index需要+1
    private int helper(int[] nums, int low, int high, int k) {
        int index = partition(nums, low, high);
        if (index + 1 == k) {
            return nums[index];
        } else if (index + 1 < k) {
            return helper(nums, index + 1, high, k);
        } else {
            return helper(nums, low, index - 1, k);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int i = low, pivot = nums[high];
        for (int j = low; j < high; j++) {
            if (nums[j] > pivot) {
                swap(i, j, nums);
                i++;
            }
        }
        swap(i, high, nums);
        return i;
    }

    private void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String args[]) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(new KthLargestElementInAnArray().findKthLargest(nums, 4));
    }
}
