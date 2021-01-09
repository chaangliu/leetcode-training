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
     * 解法：归并排序。关于归并排序，递归有两种写法，一种是把merge单独提出去，另一种就是直接在一个函数里merge and sort。
     * 这里采用第二种；并且要注意的是，merge sort是一种需要额外空间的算法。
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
            while (i <= mid) temp[k++] = arr[i++];
            while (j <= end) temp[k++] = arr[j++];
            System.arraycopy(temp, 0, arr, start, end - start + 1);
            return count;
        }

        public int reversePairs(int[] nums) {
            return merge(nums, 0, nums.length - 1);
        }
    }

    /**
     * 归并排序merge sort模板; 注意需要额外空间 + 额外指针k
     */
    void merge(int[] arr, int start, int end) {
        if (start == end) return;
        int mid = (start + end) / 2;
        merge(arr, start, mid);
        merge(arr, mid + 1, end);
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= end) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, start, end - start + 1);
    }

    public static void main(String[] args) {
        int[] res = new int[]{9, 2, 3, 1, 5, 4, 8, 7, 9, 3};
        new InversePairs().merge(res, 0, res.length - 1);
        for (int i = 0; i < res.length; i++) System.out.print(res[i] + ",");
    }
}
