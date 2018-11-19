package jianzhioffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class HalfOfTotalOccurence {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int times = map.getOrDefault(array[i], 0) + 1;
            if (times > array.length / 2) {
                return array[i];
            }
            map.put(array[i], times);
        }
        return 0;
    }
}
