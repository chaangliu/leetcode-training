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
     * 题意：给你一个数组，找出出现频率最高的k个数字。要求时间复杂度低于O(n log n)。
     * 解法1：很简单的BucketSort，每个桶的长度是1，很巧妙，同样是先取得一个FrequencyMap，
     * 取得Map之后根据frequency来散列到一个一些桶里（把出现次数同样多的放到一个桶里），在从桶从后往前加入到结果集。
     * Idea is simple. Build a array of list to be buckets with length 1 to sort.
     * Time: O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1]; // 这儿用了new List,如果用new ArrayList会提示Generic List Creation错误
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

    // 返回定长数组
    public int[] topKFrequent__(int[] nums, int k) {
        Map<Integer, Integer> occurenceMap = new HashMap<>();
        for (int num : nums) {
            occurenceMap.put(num, occurenceMap.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[k];
        ArrayList[] bucket = new ArrayList[nums.length + 1]; // 桶最大的情况：数组里只有同一个数字重复了n次
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : occurenceMap.entrySet()) {
            if (bucket[e.getValue()] == null) bucket[e.getValue()] = new ArrayList();
            bucket[e.getValue()].add(e.getKey());
        }
        for (int i = bucket.length - 1; i >= 0; i--) {
            ArrayList list = bucket[i];
            if (list == null) continue;
            int listIdx = 0;
            while (idx < k && listIdx < list.size()) {
                res[idx++] = (int) list.get(listIdx++);
            }
            if (idx == k) break;
        }
        return res;
    }

    /**
     * 解法2. heap。
     * 低于O(n log n)，可以是O(n)；也可以是O(n log k), 也就是维持一个size为k的heap。
     * leetcode的official solution，最后还有用一次heap的遍历，跟KSmallestNumbers那题一个操作
     * Time complexity : O(Nlog(k))
     */
    public List<Integer> topKFrequent____LeetCode(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    /**
     * 解法3，力扣官方的quick select；跟topk大元素类似了。。
     * 时间O(n)
     */
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
            for (int num : nums) {
                occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
            }
            List<int[]> values = new ArrayList<int[]>();
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                int num = entry.getKey(), count = entry.getValue();
                values.add(new int[]{num, count});
            }
            int[] ret = new int[k];
            qsort(values, 0, values.size() - 1, ret, 0, k);
            return ret;
        }

        public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
            // partition
            int picked = (int) (Math.random() * (end - start + 1)) + start;
            Collections.swap(values, picked, start);
            int pivot = values.get(start)[1];
            int index = start;
            for (int i = start + 1; i <= end; i++) {
                if (values.get(i)[1] >= pivot) {
                    Collections.swap(values, index + 1, i);
                    index++;
                }
            }
            Collections.swap(values, start, index);
            if (k <= index - start) {
                qsort(values, start, index - 1, ret, retIndex, k);
            } else {
                for (int i = start; i <= index; i++) {
                    ret[retIndex++] = values.get(i)[0];
                }
                if (k > index - start + 1) {
                    qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
                }
            }
        }
    }

    /**
     * 解法4
     * use treeMap. Use freqncy as the key so we can get all freqencies in order
     * <p>
     * TreeMap可以自定义Comparator，默认是好像是顺序，所以每次pollLastEntry()就是key最大的
     */
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
            Map.Entry<Integer, LinkedList<Integer>> entry = freqMap.pollLastEntry();//其实就是想用这个api，否则用普通hashmap也行
            res.addAll(entry.getValue());
        }
        return res;
    }
}
