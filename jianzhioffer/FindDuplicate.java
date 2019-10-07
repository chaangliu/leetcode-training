package jianzhioffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class FindDuplicate {

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    //# approach1, sort
    //# approach2, map/set
//    public boolean duplicate(int numbers[], int length, int[] duplication) {
//        if (numbers == null || length < 0) return false;
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < length; i++) {
//            if (set.contains(numbers[i])) {
//                duplication[0] = numbers[i];
//                return true;
//            }
//            set.add(numbers[i]);
//        }
//        return false;
//    }
    //# approach3, inplace substitution, 无需额外空间，利用「长度为n的数组里的所有数字都在0到n-1的范围内」这个条件。
    // 剑指offer上的解法是换位，每次不断把nums[i]换到与i相等；
    // 以下是牛客网上的高票解法，思路是把nums[nums[i]]上的数字+length，这样下次如果发现nums[nums[i]]已经＞=length了，说明已经加过一次了。
    // 其实就是像dfs floodfill那样，把原有格子涂色，不用额外开辟空间
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) index -= length;//注意，这个操作只是临时的，不会保存到数组里
            if (numbers[index] >= length) {
//                duplication[0] = numbers[i] >= length ? numbers[i] - length : numbers[i];//这里注意numbers[i] 可能已经>=length了
                duplication[0] = index;//index必定<=length
                return true;
            }
            numbers[index] += length;
        }
        return false;
    }

}
