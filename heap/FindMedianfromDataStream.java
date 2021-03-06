package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * Example:
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * Follow up:
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * 20191101
 */
public class FindMedianfromDataStream {
    /**
     * 题意：给你一个数据流，让你用O(1)时间获取中位数。剑指offer原题。
     * 做法是用一个最小堆和一个最大堆。如果最小堆的peek>最大堆的peek，交换两个数字。注意小顶堆存放较大的数。
     */
    class MedianFinder {
        //small:24
        //large:3
        int cnt = 0;
        PriorityQueue<Integer> small = new PriorityQueue<Integer>((a, b) -> b - a);
        PriorityQueue<Integer> large = new PriorityQueue<Integer>((a, b) -> a - b);

        public void addNum(int num) {
            if ((cnt++ & 1) == 0) {
                small.offer(num);
            } else {
                large.offer(num);
            }
            if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
                int l = large.poll(), s = small.poll();
                small.offer(l);
                large.offer(s);
            }
        }

        public double findMedian() {
            if (cnt == 0) return 0;
            if (cnt == 1) return small.peek();
            if ((cnt & 1) == 1) {
                return small.peek();
            }
            return (small.peek() + large.peek()) / 2.0;
        }
    }

    /**
     * solutions里的模拟方法，每次往large里加，并且把large顶部的移动到small里；这时如果small的size较大，再把small顶部的放到large里，保证large的size更大。
     */
    class MedianFinder__ {
        PriorityQueue<Integer> small = new PriorityQueue<Integer>((a, b) -> b - a);
        private Queue<Integer> large = new PriorityQueue<>();

        // Adds a number into the data structure.
        public void addNum(int num) {
            large.add(num);
            small.add(large.poll());
            if (large.size() < small.size())
                large.add(small.poll());
        }

        // Returns the median of current data stream
        public double findMedian() {
            return large.size() > small.size()
                    ? large.peek()
                    : (large.peek() + small.peek()) / 2.0;
        }
    }


    /**
     * 还有种做法，每次往少的那堆去加。
     */
    class MedianFinder_ {

        /**
         * initialize your data structure here.
         */
        PriorityQueue<Integer> big = new PriorityQueue<>(), small = new PriorityQueue<>((a, b) -> b - a);

        public MedianFinder_() {

        }

        public void addNum(int num) {
            if (small.size() <= big.size()) {
                if (big.isEmpty() || num < big.peek()) {
                    small.offer(num);
                } else {
                    small.offer(big.poll());
                    big.offer(num);
                }
            } else {
                if (small.isEmpty() || num < small.peek()) {
                    big.offer(small.poll());
                    small.offer(num);
                } else {
                    big.offer(num);
                }
            }
            // System.out.println("after " + num + ", small is " + small + " big is " + big);
        }

        public double findMedian() {
            return small.size() > big.size() ? small.peek() : small.size() < big.size() ? big.peek() : small.size() == 0 ? 0 : (small.peek() + big.peek()) / 2.0d;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
