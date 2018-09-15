package dfs;

/**
 * NextPermutation.
 * Created by DrunkPiano on 2016/12/22.
 */

public class NextPermutation {
    //    public static void arrange(int [] str, int start, int len) {
//        if (start == len - 1) {
//            for (int i = 0; i < len; i++) {
//                System.out.print(str[i] + "  ");
//            }
//            System.out.println();
//        } else {
//            for (int i = start; i < len; i++) {
//                swap(str, start, i);
//                arrange(str, start + 1, len);
//                swap(str, start, i);
//            }
//        }
//    }
//    public static void swap(int [] str , int a , int b ){
//        int temp = str[a];
//        str[b] = str[a];
//        str[a] = temp;
//    }
//
//    public static void nextPermutation() {
//        int[] nums = {1, 2, 3};
//        int start = 0;
//        int len = nums.length;
//        arrange(nums, start, len);
//    }
    //2763541
    public static void main(String args[]) {
        int[] nums = {1, 2, 3};

        nextPermutation(nums);
        System.out.println(nums[0]);
        System.out.println(nums[1]);
        System.out.println(nums[2]);
    }

    public static void nextPermutation(int[] num) {

//        用for循环 +break比较容易理解。。
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

        //find the last number which is bigger than i
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
