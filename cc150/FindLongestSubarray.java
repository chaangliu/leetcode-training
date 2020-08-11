package cc150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
 * <p>
 * 返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * <p>
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2:
 * <p>
 * 输入: ["A","A"]
 * <p>
 * 输出: []
 * 提示：
 * array.length <= 100000
 */
public class FindLongestSubarray {
    /**
     * 题意：找最长subarray，字母和数字数量相同。
     * 解法：字母和数字转换成1，-1，然后利用前缀和求解。
     */
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Character.isDigit(array[i].charAt(0)) ? 1 : -1;
        }
        for (int i = 1; i < n; i++) {
            num[i] += num[i - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(num[i])) {
                map.put(num[i], i);
            } else {
                int first = map.get(num[i]);
                if (i - first > end - start + 1) {
                    start = first + 1;
                    end = i + 1;
                }
            }
        }
        return Arrays.copyOfRange(array, start, end); // end是exclusive的
    }
}
