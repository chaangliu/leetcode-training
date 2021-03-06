package jianzhioffer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ReOrderArray {
    /**
     * 这题如果不要求稳定的话，可以直接用2 pointers，l指向odd,r指向even，l > r时停止
     * OJ上要求稳定，那就用下面的整体位移的方法。
     */
    //考察稳定排序（插入、归并）。空间换时间就不谈了。
    //approach1 类似插入排序。稳定。
    //1. 维护一个已经排序好的奇数的index
    //2. 从前往后找到一个奇数(位置k)
    //3. 把index + 1 ~ k位置上的数整体右移一位然后把k放到index + 1
    //    时间：O(n2)
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) return;
        int oddIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                int j = i;
                int tmp = array[j];
                while (j > oddIndex) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[oddIndex] = tmp;
                oddIndex++;
            }
        }
    }

    //20190106review
    public void reOrderArray20190106(int[] array) {
        if (array == null || array.length <= 1) return;
        int odIdx = 0;
        for (int i = 0; i < array.length; i++) {
            //如果i位是奇数，那么把[odIdx ,i - 1]的数往右移动1位，把i上的数字插到odIdx上
            if ((array[i] & 1) == 1) {
                int tmp = array[i];
                int k = i;
                while (k > odIdx) {
                    array[k] = array[k - 1];
                    k--;
                }
                array[odIdx++] = tmp;
            }
        }
    }

    //还有其他思路，都是换位置，比如：
    //    /**
    //     * 1.要想保证原有次序，则只能顺次移动或相邻交换。
    //     * 2.i从左向右遍历，找到第一个偶数。
    //     * 3.j从i+1开始向后找，直到找到第一个奇数。
    //     * 4.将[i,...,j-1]的元素整体后移一位，最后将找到的奇数放入i位置，然后i++。
    //     * 5.終止條件：j向後遍歷查找失敗。
    //     */


    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4};
        new ReOrderArray().reOrderArray(arr);
        new ReOrderArray().reOrderArray(arr);
    }

}
