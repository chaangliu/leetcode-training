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
//    public int findKthLargest(int[] nums, int k) {
//        if (k < 1 || k > nums.length || nums == null) return Integer.MAX_VALUE;
//        return helper(nums, 0, nums.length - 1, k);
//    }
//
//    //要注意index需要+1
//    private int helper(int[] nums, int low, int high, int k) {
//        int index = partition(nums, low, high);
//        if (index + 1 == k) {
//            return nums[index];
//        } else if (index + 1 < k) {
//            return helper(nums, index + 1, high, k);
//        } else {
//            return helper(nums, low, index - 1, k);
//        }
//    }
//
//    private int partition(int[] nums, int low, int high) {
//        int i = low, pivot = nums[high];
//        for (int j = low; j < high; j++) {
//            if (nums[j] > pivot) {
//                swap(i, j, nums);
//                i++;
//            }
//        }
//        swap(i, high, nums);
//        return i;
//    }
//
//    private void swap(int a, int b, int[] arr) {
//        int tmp = arr[a];
//        arr[a] = arr[b];
//        arr[b] = tmp;
//    }

    //20190104
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return helper(nums, k, 0, nums.length - 1);
    }

    private int helper(int[] nums, int k, int low, int high) {
//        if (low > high) return -1;//已犯错误1. （（删除线）这里不能是>=，否则虽然能排序，但是会漏掉很多次求pivot的步骤）这里完全不需要判断low，high，partition会判断
        int pivot = partitioning(nums, low, high);
        System.out.println("pivot == " + pivot);
        if (pivot + 1 == k) {
            return nums[pivot];////已犯错误3. 此处不需要pivot + 1..
        } else if (pivot + 1 > k) {//已犯错误2. 此处需要对partition进行折半，否则效率低
            return helper(nums, k, low, pivot - 1);
        } else {
            return helper(nums, k, pivot + 1, high);
        }
    }

    private int partitioning(int[] nums, int low, int high) {
        int pivot = nums[high];
        int slot = low;
        for (int i = low; i < high; i++) {
            if (nums[i] > pivot) {//已犯错误4. kth largest，不是kth smallest，所以这题要倒序（只要把这儿改成>就行了）
                exch(nums, i, slot++);
            }
        }
        exch(nums, high, slot);
        return slot;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String args[]) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(new KthLargestElementInAnArray().findKthLargest(nums, 4));
    }
}
