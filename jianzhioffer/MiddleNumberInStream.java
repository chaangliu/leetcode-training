package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class MiddleNumberInStream {


    ///bad approach: list，每次insert之后就sort。时间复杂度：O(n * n * log(n))
//    ArrayList<Integer> list = new ArrayList<>();
//
//    public void Insert(Integer num) {
//        list.add(num);
//        Collections.sort(list);
//    }
//
//    public Double GetMedian() {
//        int size = list.size();
//        if (size == 0) return null;
//        if ((size & 1) == 1) {
//            return list.get(size / 2) * 1.0;
//        } else {
//            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0;
//        }
//    }

    ///good approach: two heaps
    ///利用一个最大堆和一个最小堆，轮流向两个堆插入数据；最大堆的最小的数也比最大堆最大的数大，这样最小堆的peek和最大堆的peak就是交界点
    ///一个肥肠重要的point是，当轮到这个数插入到一个堆里面，如果不合适（比如明显应该取代另一堆的堆顶），那就要跟另一堆的堆顶元素换一下。这一点还是有点思维难度的
    //heap:https://www.jianshu.com/p/86fe8d1a781b


    int count = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();//默认是自然排序，最小堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    //maxHeap minHeap
    //[] [5]
    //[2] [5]
    //[2] [3,5]
    //[3,2] [4,5]
    public void Insert(Integer num) {
        count++;
        if ((count & 1) == 1) {
            if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        } else {
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }

    public Double GetMedian() {
        if (count == 0) return null;
        return (count & 1) == 1 ? minHeap.peek() * 1.0 : (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
