package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * 20191001
 */
public class LongestConsecutiveSequence {
    /**
     * 这题是说在无需数列里找连续数列的最大长度。要求O(n)，我一开始想quick select？仔细想想不行。
     * TAG标了一个UnionFind但是大部分人用了Hash。
     * 我看了一下HashSet的提示，写出了下面的代码，早上急匆匆的WA了好几次。
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        HashSet<Integer> all = new HashSet<>();
        for (int num : nums) all.add(num);
        HashSet<Integer> visited = new HashSet<>();
        int len = all.size();
        int maxStreak = 1, curStreak = 1;
        for (int i = 0; i < len; i++) {
            if (visited.contains(nums[i])) continue;
            int l = nums[i] - 1, r = nums[i] + 1;
            while (all.contains(l) && !visited.contains(l)) {
                curStreak++;
                visited.add(l);
                l--;
            }
            while (all.contains(r) && !visited.contains(r)) {
                curStreak++;
                visited.add(r);
                r++;
            }
            maxStreak = Math.max(maxStreak, curStreak);
            curStreak = 1;

        }
        return maxStreak;
    }

    /**
     * 十月份用的是hashset，也是O(n)但不是One pass。讨论区看到一个One pass的，用map。
     * For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.
     * [100, 99, 101]
     */
    public int longestConsecutive_(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            if (map.containsKey(n)) continue;
            int left = map.containsKey(n - 1) ? map.get(n - 1) : 0;
            int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;
            int streak = left + right + 1;
            res = Math.max(res, streak);
            map.put(n, streak);
            //if(map.containsKey(n-1))// 不需要contain
            map.put(n - left, streak);// n - left，不是n - 1
            //if(map.containsKey(n+1))
            map.put(n + right, streak);
        }
        return res;
    }

    /**
     * hashset, one ass
     * 如果set中没有num左边的数，就跳过
     * 如果有左边，说明左边一定在后面。也就是一直往最左边的那个数字找，然后再找最长streak
     */
    public int longestConsecutive__(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
