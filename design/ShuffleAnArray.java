package design;

import java.util.ArrayList;
import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * <p>
 * Example:
 * <p>
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * <p>
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * <p>
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 * 20191003
 */
public class ShuffleAnArray {
    class Solution {
        /**
         * 题意：给一个数组，实现打乱数组的功能。
         * 我的解法：每次用Random产生一个0~size-1的随机index，添加进结果集
         **/
        int[] origin;
        Random rand = new Random();

        public Solution(int[] nums) {
            origin = new int[nums.length];
            for (int i = 0; i < nums.length; i++) origin[i] = nums[i];
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return origin;
        }

        /**
         * Returns a random shuffling of the array.
         */
        ArrayList<Integer> idx;

        public int[] shuffle() {
            int k = 0;
            int[] res = new int[origin.length];
            idx = new ArrayList<>();
            for (int i = 0; i < origin.length; i++) idx.add(origin[i]);
            while (idx.size() > 0) {
                int index = rand.nextInt(idx.size());
                res[k++] = idx.get(index);
                idx.remove(index);
            }
            return res;
        }
    }

    /**
     * 讨论区的解法：利用swap。每次交换当前j和[0,j]中的一个数的位置i，i属于[0,j]，j从1开始遍历到最后。
     * 直觉上这么做确实能让每个数有机会参与random, 但是如果把random那一行改成random.nextInt(nums.length);就会WA，似乎是因为分配不均造成的，这个需要数学证明。
     * 值得注意，数组的clone方法很方便。
     */
    public class Solution_ {
        private int[] nums;
        private Random random;

        public Solution_(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return nums;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            if (nums == null) return null;
            int[] a = nums.clone();
            for (int j = 1; j < a.length; j++) {
                int i = random.nextInt(j + 1);
                swap(a, i, j);
            }
            return a;
        }

        private void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
