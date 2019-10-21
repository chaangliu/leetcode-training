package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 * Return the number of good subarrays of A.
 * Example 1:
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * Example 2:
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * Note:
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * 20190326
 */
public class SubarraysWithKDifferentIntegers {
    /**
     * 题意：给你一个数字字符串，求这个字符串有多少个子串(连续，可以相同)中的不相同的数字正好是K个。
     * 如果用普通的Sliding Window思路，也就是r不停地expand，l不停地shrink，会发现会漏掉一些case，因为有时候l和r是需要折返的，也就是左移，怎么办呢？
     * 一个思路是这样的，计算Exactly K可以转化为计算A中一共有多少个【不超过】K个unique number的subarray和多少个【不超过】K-1个unique number的subarray的数量，两者相减，多出来的部分就是正好有K个unique number的subarray的数量.
     * solutions里有使用两个sliding window的另外的思路，不研究了。
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return subArrayCountOfNoMoreThanNDistinctNumbers(A, K) - subArrayCountOfNoMoreThanNDistinctNumbers(A, K - 1);
    }

    private int subArrayCountOfNoMoreThanNDistinctNumbers(int[] A, int N) {
        int left = 0, right = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (right < A.length) {
            if (map.getOrDefault(A[right], 0) == 0) N--;//区间内没有A[right]这样的数，还能容纳的K减1
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            while (N < 0) {//超出了容纳上限
                map.put(A[left], map.get(A[left]) - 1);
                if (map.get(A[left]) == 0) N++;//有一个字母彻底从左边移出去了=>又能多容纳一个了
                left++;
            }
            res += right - left + 1;//加入当前A[right]之后增加的子串数量。（比较神奇的是，下面一行right++放到这一行上面也可以过，但按理说应该是放到下面的）
            right++;
        }
        return res;
    }


    /**
     * TLE的解法：
     * O(n2)
     */
    public int subarraysWithKDistinct__TLE(int[] A, int K) {
        int count = 0;
        for (int left = 0; left < A.length - K + 1; left++) {
            HashSet<Integer> set = new HashSet<>();
            for (int right = left; right < A.length; right++) {
                set.add(A[right]);
                if (set.size() == K) {
                    count++;
                } else if (set.size() > K) {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        new SubarraysWithKDifferentIntegers().subarraysWithKDistinct(new int[]{1, 2, 3, 1, 2}, 3);
    }
}
