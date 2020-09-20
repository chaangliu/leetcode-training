package jianzhioffer;

/**
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * 对于%50的数据,size<=10^4
 * 对于%75的数据,size<=10^5
 * 对于%100的数据,size<=2*10^5
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 */
public class InversePairs {
    /**
     * 题意：求数组中的逆序对。
     * 解法：归并排序。
     */
    class Solution {
        int merge(int[] arr, int start, int end) {
            if (arr.length == 0) return 0;
            if (start == end) return 0;
            int mid = (start + end) / 2;
            int count = merge(arr, start, mid) + merge(arr, mid + 1, end); // 把左右两边都排序了
            int[] temp = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                count += arr[i] > arr[j] ? mid + 1 - i : 0; // 相比模板多的一行
                temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            }
            while (i <= mid) {
                //count += mid + 1 - i;
                temp[k++] = arr[i++];
            }
            while (j <= end)
                temp[k++] = arr[j++];
            System.arraycopy(temp, 0, arr, start, end - start + 1);
            return count;
        }

        public int reversePairs(int[] nums) {
            return merge(nums, 0, nums.length - 1);
        }
    }

    /**
     * 归并排序merge sort模板
     */
    void merge(int[] arr, int start, int end) {
        if (start == end) return;
        int mid = (start + end) / 2;
        merge(arr, start, mid);
        merge(arr, mid + 1, end);

        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end)
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= end)
            temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, start, end);
    }

    ///20190109 review
    //    int count = 0;
    //
    //    public int InversePairs(int[] array) {
    //        if (array == null || array.length <= 1) return 0;
    //        divide(array, 0, array.length - 1);
    //        return count;
    //    }
    //
    //    private void divide(int array[], int low, int high) {
    //        if (low >= high) return; //这里是>=，否则无法退出递归
    //        int mid = low + (high - low) / 2;
    //        divide(array, low, mid);
    //        divide(array, mid + 1, high);// 这里 + 1，不能是上面mid - 1，否则无法退出递归
    //        merge(array, low, mid, high);
    //    }
    //
    //    private void merge(int array[], int low, int mid, int high) {
    //        int[] tmp = new int[high - low + 1];
    //        int i = low, j = mid + 1, k = 0;
    //        while (i <= mid && j <= high) {
    //            if (array[i] < array[j]) {
    //                tmp[k++] = array[i++];
    //            } else {
    //                tmp[k++] = array[j++];
    //                count += mid - i + 1;//已犯错误: 不是count++;：对于j来说 比它大的有mid - i + 1个； 对于i来说比它小的有j - mid 个；所以为什么cnt += j - mid; 是错的？？？通不过
    //                count %= 1000000007;
    //            }
    //        }
    //        while (i <= mid) {
    //            tmp[k++] = array[i++];
    //        }
    //        while (j <= high) {//已犯错误: 这边没注意j <= mid了，查了20分钟
    //            tmp[k++] = array[j++];
    //        }
    //        k = 0;
    //        for (i = low; i <= high; i++) {
    //            array[i] = tmp[k++];
    //        }
    //    }
}
