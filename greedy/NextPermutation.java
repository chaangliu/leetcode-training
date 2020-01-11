package greedy;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * Created by DrunkPiano on 2016/12/22.
 * 201200111 --review
 */

public class NextPermutation {
    /**
     * 题意：找到当前数组
     * 20190526review 今天遇到1053. Previous Permutation With One Swap过来回顾一下；
     * 这题不用递归，思路也比较直观，从后往前找一个递增的pair，然后把pair[0]和它之后所有数里最接近的那个数交换位置，最后把pair[0]后面的数reverse一下。需要纸上画一画。
     */
    public static void nextPermutation(int[] num) {

        //1.find the last number which breaks the reversal increasing order
        int pos = -1;
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i] > num[i - 1]) { // [1 2 4 6 5 3] => 4
                pos = i - 1;
                break;
            }
        }

        //2.如果不存在升序，即这个数是最大的，那么反排这个数组
        if (pos < 0) {
            reverse(num, 0, num.length - 1);
            return;
        }

        //3. find the last number which is bigger than i，注意是last，也就是从后往前第一个大于num[pos]的数
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i] > num[pos]) { // [1 2 5 6 4 3]
                //swap position and this number
                int temp = num[pos];
                num[pos] = num[i];
                num[i] = temp;
                break;
            }
        }

        //4.反排pos之后的数
        reverse(num, pos + 1, num.length - 1);// [1 2 5 3 4 6]
    }

    public static void reverse(int[] num, int begin, int end) {
        int l = begin, r = end;
        while (l < r) {
            int tmp = num[l];
            num[l] = num[r];
            num[r] = tmp;
            l++;
            r--;
        }
    }
}
