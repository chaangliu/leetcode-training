package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * 这题就是LeetCode 179. Largest Number
 */
public class MinNumber {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        List<String> res = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            res.add(numbers[i] + "");
        }
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i));
        }
        return sb.toString();
    }


    public static void main(String args[]) {
        int[] nums = {3, 32, 321};
        new MinNumber().PrintMinNumber(nums);
    }
}
