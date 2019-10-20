package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * <p>
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * <p>
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * <p>
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 * Example 1:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 * 20191020
 * <p>
 * similar question: 986. Interval List Intersections
 */
public class MeetingScheduler {
    /**
     * 双周赛的第二题。
     * 题意：给你两个人的空闲时间数组，需要在两人都有空的时候开一个时长duration的meeting，请你返回最早的开会时间。
     * 我WA了七八次最后放弃了。我发现这种题是你觉得自己模拟了一种方案，能满足给定的case，但是等你提交，会发现你的方案千疮百孔。。到后来数据量大了你根本无从分析，卒。
     * 比如这题，我画出了大概五六种图，比如A包含B，B包含A，A在B前面，A在B后面等等，但最后仍然发现一些CASE无法满足。
     * 我联想到公交车(CarPooling)那题，觉得只用一个最小堆就行，后面发现似乎需要用两个堆？但是已经想不动了。
     * 看了答案发现大部分人确实用了两个指针，就是下面的Approach 1，这题的tag一开始也是two pointers,但是后来毛出了个新颖的答案，tag就变成了line sweep。
     * 双指针的做法是，先对两个slot排序，分别用两个指针指向slot中的interval，然后找[共同区间]，也就是两者start的最大值和end的最小值；
     * 如果区间长度满足duration，直接返回答案；否则把先结束的那个slot的指针右移。
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[1] - b[1]);//因为题目告诉我们同一个人的slot里面不会有intersection，所以这里比较a[0]或者a[1]都可以
        Arrays.sort(slots2, (a, b) -> a[1] - b[1]);
        int i = 0, j = 0, len1 = slots1.length, len2 = slots2.length;
        List<Integer> res = new ArrayList<>();
        while (i < len1 && j < len2) {
            int l = Math.max(slots1[i][0], slots2[j][0]);//intersect start
            int r = Math.min(slots1[i][1], slots2[j][1]);//intersect end
            if (r - l >= duration) {
                res.add(l);
                res.add(l + duration);
                return res;
            }
            if (slots1[i][1] < slots2[j][1]) {//这里跟course schedule iii 一样，比较的是end position
                i++;
            } else j++;
        }
        return res;
    }

    /**
     * Approach2. 最小堆
     * 他用了一个最小堆，注意两点，
     * 第一，在offer的时候可以直接过滤掉长度<duration的interval。
     * 第二，在堆的元素>=2的情况下，先poll出堆顶的元素的end，然后与此时堆顶元素的start + duration相比。这个又巧妙利用了上一个条件过滤后的保障。
     */

    public List<Integer> minAvailableDuration__(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        for (int[] s : slots1) if (s[1] - s[0] >= duration) pq.offer(s);
        for (int[] s : slots2) if (s[1] - s[0] >= duration) pq.offer(s);
        while (pq.size() >= 2) {
            if (pq.poll()[1] >= pq.peek()[0] + duration)
                return Arrays.asList(pq.peek()[0], pq.peek()[0] + duration);
        }
        return Arrays.asList();
    }

    /**
     * 附上我WA的代码
     */
    public List<Integer> minAvailableDuration__WA(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> {
            return a.num - b.num;
        });
        List<Integer> res = new ArrayList<>();
        for (int[] s : slots1) {
            queue.offer(new Pair(true, s[0]));
            queue.offer(new Pair(false, s[1]));
        }
        for (int[] s : slots2) {
            queue.offer(new Pair(true, s[0]));
            queue.offer(new Pair(false, s[1]));
        }
        int cons = 0, lastIndex = -1;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (cons == 0) {
                if (p.isStart) {
                    cons = 1;
                } else {
                    cons = 0;
                }
            } else if (cons == 1) {
                if (p.isStart) {
                    cons = 2;
                    lastIndex = p.num;
                } else {
                    cons = 0;
                }
            } else {
                if (!p.isStart) {
                    if (p.num - lastIndex >= duration) {
                        res.add(lastIndex);
                        res.add(lastIndex + duration);
                        return res;
                    } else {
                        cons = 0;
                    }
                } else {
                    cons = 0;
                }
            }
        }
        return res;
    }

    class Pair {
        boolean isStart;
        int num;

        Pair(boolean isStart, int num) {
            this.isStart = isStart;
            this.num = num;
        }
    }
}
