package basics;

/**
 * 查找第k小的元素
 * Created by DrunkPiano on 2016/12/25.
 */

public class FindKthMin {

    /**
     * 以下解法未验证，正确写法可参考：KthLargestElementInAnArray
     */

//    public static int findKthMin(int[] array, int len, int k) {
//        int low = 0, high = len - 1;
//        while (low < high) {
//            int i = low , j = high;
//            int pivot = array[i];
//            while (i < j && array[j] > pivot)
//                j--;
//            if (i < j)
//                array[i++] = array[j];
//            while (i < j && array[i] < pivot)
//                i++;
//            if (i < j)
//                array[j--] = array[i];
//
//            array[i] = pivot;
//            if (k == i) return pivot;
//            if (k < i) {
//                high = i - 1;
//            }
//            if (k > i) {
//                low = i + 1;
//            }
//        }
//        return -1;
//    }

//    public static void main(String args[]) {
//        int array[] = {2, 3, 4, 1, 5, 10, 9, 7, 8, 6};
//        int k = 3;
//        System.out.println(findKthMin(array, array.length, k));
//    }

}
