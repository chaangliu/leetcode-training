package slidingwindow;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

import linkedlist.SortList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 20190401
 */
public class SlidingWindowMaximum {
    /**
     * 《剑指offer》原题
     * 题意是一个sliding window不停右移，每移动1位就记录当前window中最大的数字记录下来，最后输出。
     * approach1. O(nlogn)解法，slidingWindow + heap(priorityQueue)
     * 维护一个size为k的最大堆（这个手法在TopKFrequentElements等题也用到）,不停地往里加就完事了；同时要把移出window的数字remove掉，注意heap是支持remove具体数字的。
     * reHeap的复杂度是log n，总时间O(nlogn)
     */
    public int[] maxSlidingWindow__HEAP(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k) {
                heap.offer(nums[i]);
            }
            if (heap.size() == k) {
                res[i - k + 1] = heap.peek();
                heap.remove(nums[i - k + 1]);
            }
        }
        return res;
    }

    /**
     * approach2. 双端队列O(n)解法，很经典，单调队列(栈底(左边)最大，栈顶(右边)最小)，像一个播放键；
     * 相当于两边都能操作的单调栈，想象一个从左到右的队列，为了跟窗口移动方向一致，右边是队头head左边是队尾rear；
     * 队头保存window中的最大数的index，添加新数的时候不断从队尾移出比新数小的数的index
     * 需要注意的是peek/pollLast是最后(最近)加入的元素, peekLast(Added)。
     */
    public int[] maxSlidingWindow(int[] A, int k) {
        int[] res = new int[A.length - k + 1];
        int index = 0;
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            while (!d.isEmpty() && i - d.peekFirst() >= k) d.pollFirst();
            while (!d.isEmpty() && A[d.peekLast()] <= A[i]) d.pollLast();
            d.offer(i);
            if (i >= k - 1) res[index++] = A[d.peekFirst()];
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
     */
    public int[] maxSlidingWindow_(int[] A, int k) {
        if (A == null || k <= 0) return new int[0];
        int n = A.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            // remove numbers out of range k，删掉窗口外的元素；当前指针i指向窗口的右边缘，那么左边缘(inclusive)是i - k + 1
            if (!q.isEmpty() && q.peek() < i - k + 1) { // peek() 返回 the first(aka最左边) element of this deque
                q.poll();//相当于pollFirst，也就是栈底（左边），也就是队列正常的出队顺序，把左边的out of window的index移出
            }
            // remove smaller numbers in k range as they are useless，对于窗口内的元素，从栈顶开始把比当前小的元素index都移除
            while (!q.isEmpty() && A[q.peekLast()] < A[i]) {
                // 从队尾（也就是栈顶（右边），也就是最近加入的）开始，把小于即将加入的a[i]的数出队，因为这些数都没用利用价值了，要用也是用a[i]；
                // 这里因为要保证队头(peek)永远是window中的最大数，所以从右边开始比较；
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                //队头(peek)永远是window中的最大数
                res[ri++] = A[q.peek()];
            }
        }
        return res;
    }

    /**
     * offerFirst写法，无法通过[7,2,4], 2
     */
    public int[] maxSlidingWindow_OFFERFIRST(int[] A, int k) {
        if (A == null || k <= 0) return new int[0];
        int n = A.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if (!q.isEmpty() && q.peek() < i - k + 1) { // peek() 返回 the first(aka最左边) element of this deque
                q.poll();
            }
            while (!q.isEmpty() && A[q.peek()] < A[i]) {
                q.poll();
            }
            // q contains index... r contains content
            q.offerFirst(i);
            if (i >= k - 1) {
                //队头(peek)永远是window中的最大数
                res[ri++] = A[q.peekLast()];
            }
        }
        return res;
    }
}
