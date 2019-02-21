package basics;

import java.util.Arrays;

/**
 * Created by DrunkPiano on 2016/12/25.
 */

public class QuickSort {
//
//    //第一种写法
//    private static void quickSort(int[] a, int low, int high) {
//        //递归的出口
//        if (low > high) {
//            return;
//        }
//        int i = low;
//        int j = high;
//        int pivot = a[low];
//        //完成一趟排序
//        while (i < j) {
//            //[从右往左]找到第一个小于pivot的数
//            while (i < j && a[j] > pivot) {
//                j--;
//            }
//            //从左往右找到第一个大于pivot的数
//            while (i < j && a[i] <= pivot) {
//                i++;
//            }
//            //a[i]和a[j]交换
//            if (i < j) {
//                int temp = a[i];
//                a[i] = a[j];
//                a[j] = temp;
//            }
//        }
//        //a[i]和a[low]交换
//        int temp = a[i];
//        a[i] = a[low];
//        a[low] = temp;
//        quickSort(a, low, i - 1);
//        quickSort(a, i + 1, high);
//    }
//
//    //第二种写法，partition思想
//    private void quickSort2(int[] arr, int low, int high) {
//        if (low > high) return;
//        int i = low, pivot = arr[high];
//        for (int j = low; j < high; j++) {
//            //把<pivot的数换到前面去，并且i坐标右移1位，那么这样走一趟下来i（最后已经加过1）就是pivot的正确位置
//            if (arr[j] < pivot) {
//                swap(i, j, arr);
//                i++;
//            }
//        }
//        swap(i, high, arr);
//        quickSort2(arr, low, i - 1);
//        quickSort2(arr, i + 1, high);
//    }
//
//    private void swap(int a, int b, int[] arr) {
//        int tmp = arr[a];
//        arr[a] = arr[b];
//        arr[b] = tmp;
//    }
//
//    //第三种写法，抽出partition函数。单次partition求得pivot正确的位置
//    private int partition(int[] arr, int low, int high) {
//        int i = low, pivot = arr[high];
//        for (int j = low; j < high; j++) {
//            //把<pivot的数换到前面去，并且i坐标右移1位，那么这样走一趟下来i（最后已经加过1）就是pivot的正确位置
//            if (arr[j] < pivot) {
//                swap(i, j, arr);
//                i++;
//            }
//        }
//        swap(i, high, arr);
//        return i;
//    }
//
//    private void quickSort3(int[] arr, int low, int high) {
//        if (low > high) return;
//        int pivot = partition(arr, low, high);
//        quickSort3(arr, low, pivot - 1);
//        quickSort3(arr, pivot + 1, high);
//    }
//

    public static void main(String[] args) {
        int a[] = {9, 0, 2, 3, 1};
//        int a[] = {3, 2, 7, 3, 1};
//        int a[] = {3, 1, 2};
//        System.out.println(Arrays.toString(a));
//        quickSort(a, 0, a.length - 1);
        new QuickSort().quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    //20190103 review
    public void quickSort(int[] array) {
        if (array == null || array.length <= 1) return;
        helper(array, 0, array.length - 1);
    }

    private void helper(int[] array, int low, int high) {
        if (array == null || low >= high) return;
        int pivot = partition2(array, low, high);
        helper(array, low, pivot - 1);
        helper(array, pivot + 1, high);
    }

    //每次partition让比pivot小的数落在pivot左边
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int slot = low;
        //high本身是len - 1, 这里i < high，意思是把pivot排除在外
        for (int i = low; i < high; i++) {
            if (array[i] < pivot) {
                swap(array, slot++, i);
            }
        }
        //最后把pivot放在正确的位置上。比如，跟pivot对比的数都比它大，那么slot从来都没有增加过，pivot就被移动到了首位。
        swap(array, slot, high);
        return slot;
    }

    //每次partition让比pivot大的数落在pivot右边的写法
    private int partition2(int[] array, int low, int high) {
        int pivot = array[low];
        int slot = high;
        for (int i = low + 1; i < high + 1; i++) {
            if (array[i] > pivot) {
                swap(array, slot--, i);
            }
        }
        //最后把pivot放在正确的位置上。比如，跟pivot对比的数都比它小，那么slot从来都没有--过，pivot就被移动到了末位。
        swap(array, slot, low);
        return slot;
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}