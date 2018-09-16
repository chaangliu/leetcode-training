package basic;

import java.util.Arrays;

/**
 * Created by DrunkPiano on 2016/12/25.
 */

public class QuickSort {

    private static void quickSort(int[] a, int low, int high) {
        //递归的出口
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int pivot = a[low];
        //完成一趟排序
        while (i < j) {
            //[从右往左]找到第一个小于pivot的数
            while (i < j && a[j] > pivot) {
                j--;
            }
            //从左往右找到第一个大于pivot的数
            while (i < j && a[i] <= pivot) {
                i++;
            }
            //a[i]和a[j]交换
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        //a[i]和a[low]交换
        int temp = a[i];
        a[i] = a[low];
        a[low] = temp;
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);
    }

    public static void main(String[] args) {
        int a[] = {9, 0, 2, 3, 1};
        System.out.println(Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }


//    private void quickSort(int left, int right, int[] nums) {
//        if (left >= right)
//            return;
//        int i = left, j = right, pivot = nums[left];
//        while (i < j) {
//            while (i < j && nums[j] >= pivot)
//                j--;
//            if (i < j)
//                nums[i] = nums[j];
//            while (i < j && nums[i] <= pivot)
//                i++;
//            if (i < j)
//                nums[j] = nums[i];
//        }
//        nums[i] = pivot;
//        quickSort(left, i - 1, nums);
//        quickSort(i + 1, right, nums);
//    }

//    public void quickSort(int array[], int low, int high) {
//        int i, j;
//        int index;
//        if (low >= high) return;
//
//        i = low;
//        j = high;
//        //stash一下最左边的元素
//        index = array[i];
//        while (i < j) {
//            while (i < j && array[j] > index)
//                j--;
//            if (i < j)
//                array[i++] = array[j];
//            while (i < j && array[i] < index)
//                i++;
//            if (i < j)
//                array[j--] = array[i];
//        }
//        array[i] = index;
//        quickSort(array, low, i - 1);
//        quickSort(array, i + 1, high);
//
//    }

}