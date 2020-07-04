package greedy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of integers arr of even length n and an integer k.
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * Return True If you can find a way to do that or False otherwise.
 * Example 1:
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * Example 4:
 * <p>
 * Input: arr = [-10,10], k = 2
 * Output: true
 * Example 5:
 * <p>
 * Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 * 20200628
 */
public class CheckIfArrayPairsAreDivisiblebyk {
    /**
     * 题意：给你一个int数组，问是否能把所有的数字分成n/2组，每组的2个数字加起来都能被k整除。
     * 解法：我的做法，用map保存一下mod k之后数字的数量，最后比较一下，mod是v和k-v的数字加起来是否相等（0的case要单独处理）。有个要点是对负数的处理，i = k - (-i) % k;这个我在纸上算了下弄出来的，之前好像也遇到过类似的。
     */
    public boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (i < 0) {
                i = k - (-i) % k;
            }
            int mod = i % k;
            set.add(mod);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        for (int v : set) {
            if (v == 0) {
                if (map.get(0) % 2 != 0) return false;
            } else if (!map.get(v).equals(map.get(k - v))) {
                return false;
            }
        }
        return true;
    }

    /**
     * discuss里的做法，对于负数，先mod再+k
     */
    public boolean canArrange_(int[] arr, int k) {
        int[] reminderFreq = new int[k];
        for (int a : arr) {
            int rmd = a % k;
            if (rmd < 0) {
                rmd += k;
            }
            reminderFreq[rmd]++;
        }

        for (int i = 1; i < k; i++) {
            if (reminderFreq[i] != reminderFreq[k - i])
                return false;
        }

        return reminderFreq[0] % 2 == 0;
    }
}
