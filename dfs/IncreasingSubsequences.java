package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 20190319
 */

public class IncreasingSubsequences {

    /**
     * 题意：给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     * 这题跟subsets基本一样。不需要visited记录访问情况。我一开始有点疑惑这题的终止条件，后来发现不需要。另外要考虑重复情况。
     * 我的解法：借助set，启发是：可以用一种不带入递归的集合来判断是否访问过同层相同元素
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> item, int[] nums, int start) {
        if (item.size() > 1) res.add(new ArrayList<>(item));
        Set<Integer> used = new HashSet<>();// set不用代入递归，因为它只判断当前层是否使用过相同数字就行了；也就是，不要从同样数字的入口进入下一层递归
        for (int i = start; i < nums.length; i++) {
            if (!used.contains(nums[i]) && (item.size() == 0 || nums[i] >= item.get(item.size() - 1))) {
                used.add(nums[i]);
                item.add(nums[i]);
                dfs(res, item, nums, i + 1);
                item.remove(item.size() - 1);
            }
        }
    }

    /**
     * 解法2， 不带for循环的dfs；
     * 第一个dfs代表选当前元素，第二个dfs代表不选当前元素，相当于把每个数字选或不选都枚举了一遍。
     * 初看其实还挺难理解的。。因为这种模式并不常见。中文讨论区很多讨论。类似题目：combinations，官方也采取了这种做法。
     * 对于[1,2,3,4], 会打印出：
     * [[1,2,3,4],[1,2,3],[1,2,4],[1,2],[1,3,4],[1,3],[1,4],[2,3,4],[2,3],[2,4],[3,4]]
     */
    class Solution {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0) return res;
            dfs(res, new ArrayList<Integer>(), nums, 0);
            return res;
        }

        private void dfs(List<List<Integer>> res, List<Integer> item, int[] A, int index) {
            // if (item.size() > 1) res.add(new ArrayList<>(item)); // 不能写在这儿
            if (index == A.length) {
                if (item.size() > 1) res.add(new ArrayList<>(item));
                return;
            }
            if (item.size() == 0 || A[index] >= item.get(item.size() - 1)) {
                item.add(A[index]);
                dfs(res, item, A, index + 1); // 选当前元素
                item.remove(item.size() - 1);
            }
            if (item.size() > 0 && A[index] == item.get(item.size() - 1)) return; // 当前元素和上一个相同，必须选当前元素，不能跳过当前元素
            dfs(res, item, A, index + 1); // 不选当前元素
        }
    }
}
