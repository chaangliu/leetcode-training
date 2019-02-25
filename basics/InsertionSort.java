package basics;

/**
 * 插入排序。稳定。时间O(n2)。
 * 20190107
 */
public class InsertionSort {
    private int[] insertionSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return nums;
        for (int i = 1; i < nums.length; i++) {
            //从第i位向前寻找【第一个】比它小的数(无需从头到尾寻找一遍)，插入到这个数的后面，寻找位置的过程中后面的数都后移一位。类似题目:ReOrderArray
            int tmp = nums[i];
            int k = i - 1;
            while (k >= 0 && nums[k] > tmp) {
                nums[k + 1] = nums[k];
                k--;
            }
            nums[k + 1] = tmp;
        }
        return nums;
    }

    //or : 一次次换
    private int[] insertionSort2(int[] nums) {
        if (nums == null || nums.length <= 1)
            return nums;
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            for (int k = i; k > 0; k--) {
                if (nums[k - 1] > nums[k]) {
                    nums[k] = nums[k - 1];
                    nums[k - 1] = tmp;
                } else {
                    break;
                }
            }
        }
        return nums;
    }

    //or : https://blog.csdn.net/jinwanchiji/article/details/79652304
    public static void Isertion(int[] arr) {
        for (int key = 1; key < arr.length; key++) {
            int temp;
            for (int i = key; i > 0; i--) {
                if (arr[i] < arr[i - 1]) {
                    temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                } else {
                    break;
                }
            }
        }
    }
}
