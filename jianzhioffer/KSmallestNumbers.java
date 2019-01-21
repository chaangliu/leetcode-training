package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class KSmallestNumbers {
    //这一题非常好，把priorityQueue和最大最小堆的关系体现了出来。
    //用最大堆保存这k个数，每次只和堆顶比，如果比堆顶小，删除堆顶，新数入堆。
    //遍历、建堆，复杂度是O(n + nlogk)
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) return res;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < input.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(input[i]);
            } else {
                if (maxHeap.peek() > input[i]) {
                    maxHeap.poll();
                    maxHeap.offer(input[i]);
                }
            }
        }
        res.addAll(maxHeap);
        return res;
    }


    //20190121 发现无需比较堆顶元素，因为对于最大堆，size到了k之后每次poll的都是堆顶元素，所以会自动保留k个最小的数
    public ArrayList<Integer> GetLeastNumbers_Solution______leetcode(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) return res;
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // keep k largest elements in the heap
        for (int n: input) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }
        res.addAll(heap);
        // output
        return res;
    }

    //20190121review
    //这题同样可以解决第k小元素
    //https://leetcode.com/articles/kth-largest-element-in-an-array/

    public static void main(String args[]) {
        int[] nums = {4, 5, 1, 6, 2, 7, 3, 8};
        new KSmallestNumbers().GetLeastNumbers_Solution(nums, 4);
    }
}
