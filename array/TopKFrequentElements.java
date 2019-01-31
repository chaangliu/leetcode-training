package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * 20190131
 */
public class TopKFrequentElements {

    /**
     * =========================================================================================================================
     * 我的Solution：HashMap + PriorityQueue(最大堆)
     * =========================================================================================================================
     */
//    public class Pair {
//        int val;
//        int count;
//
//        Pair(int v, int c) {
//            val = v;
//            count = c;
//        }
//    }
//
//    public List<Integer> topKFrequent(int[] nums, int k) {
//        List<Integer> res = new ArrayList<>();
//        if (nums == null || nums.length == 0) return res;
//        Map<Integer, Pair> map = new HashMap<>();
//        for (int num : nums) {
//            if (!map.containsKey(num)) {
//                map.put(num, new Pair(num, 1));
//            } else {
//                map.get(num).count++;
//            }
//        }
//        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(100, new Comparator<Pair>() {
//            @Override
//            public int compare(Pair o1, Pair o2) {
//                return o2.count - o1.count;
//            }
//        });
//        //最大堆，先poll的是最大数
//        int count = 0;
//        for (Map.Entry entry : map.entrySet()) {
//            //留在queue里的数量
//            count++;
//            priorityQueue.offer((Pair) entry.getValue());
//            if (k + count > map.size()) {
//                res.add(priorityQueue.poll().val);
//            }
//        }
//        return res;
//    }

    /**
     * =========================================================================================================================
     * leetcode的official solution，最后还有用一次heap的遍历，跟KSmallestNumbers那题一个操作
     * =========================================================================================================================
     */
//    public List<Integer> topKFrequent____LeetCode(int[] nums, int k) {
//        // build hash map : character and how often it appears
//        HashMap<Integer, Integer> count = new HashMap();
//        for (int n : nums) {
//            count.put(n, count.getOrDefault(n, 0) + 1);
//        }
//
//        // init heap 'the less frequent element first'
//        PriorityQueue<Integer> heap =
//                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));
//
//        // keep k top frequent elements in the heap
//        for (int n : count.keySet()) {
//            heap.add(n);
//            if (heap.size() > k)
//                heap.poll();
//        }
//
//        // build output list
//        List<Integer> top_k = new LinkedList();
//        while (!heap.isEmpty())
//            top_k.add(heap.poll());
//        Collections.reverse(top_k);
//        return top_k;
//    }


    /**
     * =========================================================================================================================
     * BucketSort，每个桶的长度是1，很巧妙，同样是先取得一个FrequencyMap，取得Map之后根据frequency来散列到一个一些桶里，在从桶从后往前加入到结果集。
     * <p>
     * Idea is simple. Build a array of list to be buckets with length 1 to sort.
     * =========================================================================================================================
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }


    /**
     * =========================================================================================================================
     * use treeMap. Use freqncy as the key so we can get all freqencies in order
     * <p>
     * TreeMap可以自定义Comparator，默认是好像是顺序，所以每次pollLastEntry()就是key最大的
     * =========================================================================================================================
     */
    public class Solution {
        public List<Integer> topKFrequent_____TREEMAP(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int n : nums) {
                map.put(n, map.getOrDefault(n, 0) + 1);
            }

            TreeMap<Integer, LinkedList<Integer>> freqMap = new TreeMap<>();
            for (int num : map.keySet()) {
                int freq = map.get(num);
                if (!freqMap.containsKey(freq)) {
                    freqMap.put(freq, new LinkedList<Integer>());
                }
                freqMap.get(freq).add(num);
            }

            List<Integer> res = new ArrayList<>();
            while (res.size() < k) {
                Map.Entry<Integer, LinkedList<Integer>> entry = freqMap.pollLastEntry();
                res.addAll(entry.getValue());
            }
            return res;
        }
    }

    public static void main(String args[]) {
        int nums[] = {1, 1, 1, 2, 2, 3};
        new TopKFrequentElements().topKFrequent(nums, 2);
    }
}
