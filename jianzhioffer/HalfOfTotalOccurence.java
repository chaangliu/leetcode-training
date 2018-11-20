package jianzhioffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class HalfOfTotalOccurence {
    //map解法：
    public int MoreThanHalfNum_Solution2(int[] array) {
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

    //最优解法：
    //作者：无聊刷刷题
    //链接：https://www.nowcoder.com/questionTerminal/e8a1b01a2df14cb2b228b30ee6a92163
    //「我来解释一下这题为什么可以这么做。首先第一个for循环结束后得到的num是什么？如果这个数组中存在个数大于数组长度一半的数，
    //那么这个num一定是这个数，因为数组中所有不是num的数，一定会被这个数覆盖，所以最后得到的数是num。但是，如果这个数组中根本不存在个数大于数组长度一半的数，
    //那么这个num就是一个不确定的值，这也是为什么找出num之后，还要再做一次循环验证这个数出现的个数是不是大于数组长度一半的原因」
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        // 遍历每个元素，并记录次数；若与前一个元素相同，则次数加1，否则次数减1
        int result = array[0];
        int times = 1; // 次数
        for (int i = 1; i < array.length; ++i) {
            if (times == 0) {
                // 更新result的值为当前元素，并置次数为1
                result = array[i];
                times = 1;
            } else if (array[i] == result) {
                ++times; // 相同则加1
            } else {
                --times; // 不同则减1
            }
        }

        // 判断result是否符合条件，即出现次数大于数组长度的一半
        times = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == result) ++times;
        }
        return (times > array.length / 2) ? result : 0;
    }
}
