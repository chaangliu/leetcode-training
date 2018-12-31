package jianzhioffer;

import java.util.ArrayDeque;
import java.util.ArrayList;


/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class MaxNumInSlidingWindow {

    ///my approach, 每次slide都更新最大值和第二大的值；但是这种方法在滑出的数是最大或者第二大的数的时候仍然需要遍历。
//    public ArrayList<Integer> maxInWindows(int[] num, int size) {
//        ArrayList<Integer> res = new ArrayList<>();
//        if (num == null || num.length == 0 || size < 1) return res;
//        if (size > num.length) size = num.length;
//        int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
//        int left = 0, right = size - 1;
//        //找出第一个window里最大和第二大的值
//        for (int i = 0; i < size; i++) {
//            if (num[i] > largest) {
//                secondLargest = largest;
//                largest = num[i];
//            } else if (num[i] > secondLargest) {
//                secondLargest = num[i];
//            }
//        }
//        res.add(largest);
//        while (right < num.length - 1) {
//            left++;
//            right++;
//            if (num[left - 1] == largest) {
//                largest = secondLargest;
//                ///重新找第二大的数
//                secondLargest = getSecondMax();
//            }
//            if (num[left - 1] == secondLargest) {
//                ///重新找第二大的数
//                secondLargest = getSecondMax();
//            }
//            if (num[right] > largest) {
//                secondLargest = largest;
//                largest = num[right];
//            } else if (num[right] > secondLargest) {
//                secondLargest = num[right];
//            }
//            res.add(largest);
//        }
//    }
//
//    private int getSecondMax() {
//        //not implemented
//    }

    //better approach，利用双端队列的pollFirst, pollLast功能，还是比较难用的
    //链接：https://www.nowcoder.com/questionTerminal/1624bc35a45c42c0bc17d17fa0cba788

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            begin = i - size + 1;
            if (q.isEmpty())
                q.add(i);
            else//左边界大于最大值所在的index了，就把最大值的index出列
                if (begin > q.peekFirst())
                    q.pollFirst();
            //右边进来的数比队尾的数大，就从后往前把所有比它小的数的index出列，然后自己入列(有可能最后queue只有它一个)
            while ((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            q.add(i);
            if (begin >= 0)
                res.add(num[q.peekFirst()]);
        }
        return res;
    }

    public static void main(String args[]) {
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        new MaxNumInSlidingWindow().maxInWindows(arr, 3);
    }
}
