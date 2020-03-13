package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 * 20191004
 */
public class InsertDeleteGetRandomO1 {
    /**
     * 题意：实现一个数据结构，支持O(1)地插入，删除和随机返回一个数。
     * 这题的关键在于如何Delete，而不是如何GetRandom（因为可以直接用rand.nextInt()）。
     * <p>
     * 下面是我的做法，后面附上讨论区的做法。
     * 我的思路：首先set是没有index概念的，所以可以用一个list来记录输入的这些数字，random的时候就利用list的index来做随机。
     * 但是这种思路有个问题，就是remove的时候，list是无法做到O1删除的（ArrayList要移动后面的数字，我这里用了LinkedList，但是由于搜索也需要O(n)，我觉得也达不到O(1)）
     * 我的做法，能过但是速度慢。
     */
    class RandomizedSet {
        LinkedList<Integer> helper;
        HashSet<Integer> set;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            helper = new LinkedList<Integer>();
            set = new HashSet<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (set.add(val)) {
                helper.add(val);
                return true;
            }
            return false;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (set.remove((Integer) val)) {
                helper.remove((Integer) val);
                return true;
            }
            return false;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            Random rand = new Random();
            int index = rand.nextInt(set.size());
            return helper.get(index);
        }
    }

    /**
     * 讨论区的做法。如何使得remove某个数达到O(1)？
     * 技巧是，我们在insert的时候就用map记录插入的顺序loc，这样在remove的时候，把loc位置的元素和list末位交换，这样remove list结尾就行了。同时要注意map要更新loc位置。
     */
    public class RandomizedSet_ {
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        java.util.Random rand = new java.util.Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet_() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Integer>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if (contain) return false;
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if (!contain) return false;
            int loc = locs.get(val);
            if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);// list.set()函数
                locs.put(lastone, loc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
