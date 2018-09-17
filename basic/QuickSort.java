package basic;

import java.util.Arrays;

/**
 * Created by DrunkPiano on 2016/12/25.
 */

public class QuickSort {

    //第一种写法
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

    //第二种写法，直接调用
    private void quickSort2(int[] arr, int low, int high) {
        if (low > high) return;
        int i = low, pivot = arr[high];
        for (int j = low; j < high; j++) {
            //把<pivot的数换到前面去，并且i坐标右移1位，那么这样走一趟下来i（最后已经加过1）就是pivot的正确位置
            if (arr[j] < pivot) {
                swap(i, j, arr);
                i++;
            }
        }
        swap(i, high, arr);
        quickSort2(arr, low, i - 1);
        quickSort2(arr, i + 1, high);
    }

    private void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    //第三种写法，单次partition求得pivot正确的位置
    private int partition(int[] arr, int low, int high) {
        int i = low, pivot = arr[high];
        for (int j = low; j < high; j++) {
            //把<pivot的数换到前面去，并且i坐标右移1位，那么这样走一趟下来i（最后已经加过1）就是pivot的正确位置
            if (arr[j] < pivot) {
                swap(i, j, arr);
                i++;
            }
        }
        swap(i, high, arr);
        return i;
    }

    private void quickSort3(int[] arr, int low, int high) {
        if (low > high) return;
        int pivot = partition(arr, low, high);
        quickSort3(arr, low, pivot - 1);
        quickSort3(arr, pivot + 1, high);
    }


    public static void main(String[] args) {
//        int a[] = {9, 0, 2, 3, 1};
        int a[] = {3, 2, 7, 3, 1};
//        System.out.println(Arrays.toString(a));
//        quickSort(a, 0, a.length - 1);
        new QuickSort().quickSort3(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}