package array;

/**
 * Created by DrunkPiano on 2017/3/25.
 */

public class MedianofTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //假设nums1的长度不够m+n，不能inplace，需要把数组存起来
        int totalLen = nums1.length + nums2.length;
        int[] sum = new int[totalLen];
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) {
                sum[totalLen - 1] = nums2[j];
                totalLen--;
                j--;
            } else {
                sum[totalLen - 1] = nums1[i];
                totalLen--;
                i--;
            }
        }
        if (i >= 0) {
            for (int index = 0; index <= i; index++)
                sum[index] = nums1[index];
        }
        if (j >= 0) {
            for (int index = 0; index <= j; index++)
                sum[index] = nums2[index];
        }
        totalLen = nums1.length + nums2.length;
        if (totalLen % 2 == 0) {
            double res = (sum[totalLen / 2 - 1] + sum[totalLen / 2]) / 2.0;
            return res;
        } else {
            return sum[totalLen / 2];
        }
    }

    public static void main(String args[]) {
        MedianofTwoSortedArrays medianofTwoSortedArrays = new MedianofTwoSortedArrays();
        int nums1[] = {1,2,3,4,5,6};
        int nums2[] = {7,8};
        double res = medianofTwoSortedArrays.findMedianSortedArrays(nums1, nums2);
//        for (int num : res) {
        System.out.println(res);
//        }
    }


}
