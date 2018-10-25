package jianzhioffer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 * 这题跟search in rotated sorted array 很像。https://www.cnblogs.com/kukri/p/8484992.html
 */
public class MinNumberInRotatedArray {

    //总感觉这种解法很tricky，看着很短，但是很难理解；
    // 1. 为什么跟right比，而不是跟left比。2. 为什么right = mid;而不是right = mid - 1；3. 终止条件也不清晰
    // 尤其是2， 是很corner的case
    // 关于1，洗澡的时候想了一下，只能跟right比，因为是非递减序列，所以如果跟left比，比left大的话，最小值有可能在左边(没有rotate)也有可能在右边
    // 看了我一晚上，气死了。明天写一下剑指offer的思路
//    public int minNumberInRotateArray(int[] array) {
//        if (array == null || array.length == 0) return 0;
//        int left = 0, right = array.length - 1;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (array[mid] > array[right]) {
//                left = mid + 1;
//            } else if (array[mid] == array[right]) {
//                left = left + 1;//10111, 一个个试. 或者right = right -1;
//            } else {
//                right = mid;
//            }
//        }
//        return array[left];
//    }

    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int left = 0, right = array.length - 1;
        int mid;
        //确保旋转
        while (array[left] >= array[right]) {
            //这个终止条件比较重要
            if (left + 1 == right) {
                return array[right];
            }
            mid = left + (right - left) / 2;
            if (array[mid] > array[left]) {
                //还在上升，所以最小值只能在mid的右边
                left = mid + 1;
            } else if (array[mid] < array[left]) {
                //最小值可能是mid或者mid的左边
                right = mid;
            } else {
                //10111,只能遍历
                int min = array[0];
                for (int i = 0; i < array.length; i++) {
                    min = Math.min(min, array[i]);
                }
                return min;
            }
        }
        return array[left];
    }

}
