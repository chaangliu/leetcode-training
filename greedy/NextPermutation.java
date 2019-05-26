package greedy;

/**
 * NextPermutation.
 * Created by DrunkPiano on 2016/12/22.
 */

public class NextPermutation {
    /**
     * 20190526review 今天遇到1053. Previous Permutation With One Swap过来回顾一下；
     * 这题不用递归，思路也比较直观，从后往前找一个递增的pair，然后把pair[0]和它之后所有数里最接近的那个数交换位置，最后把pair[0]后面的数reverse一下。需要纸上画一画。
     */
    public static void nextPermutation(int[] num) {

        //1.find the last number which breaks the reversal increasing order
        int pos = -1;
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i] > num[i - 1]) {
                pos = i - 1;
                break;
            }
        }

        //2.如果不存在升序，即这个数是最大的，那么反排这个数组
        if (pos < 0) {
            reverse(num, 0, num.length - 1);
            return;
        }

        //3. find the last number which is bigger than i
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i] > num[pos]) {
                //swap position and this number
                int temp = num[pos];
                num[pos] = num[i];
                num[i] = temp;
                break;
            }
        }

        //4.反排pos之后的数
        reverse(num, pos + 1, num.length - 1);
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
