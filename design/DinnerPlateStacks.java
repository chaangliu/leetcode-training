package design;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.
 * <p>
 * Implement the DinnerPlates class:
 * <p>
 * DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
 * void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
 * int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks are empty.
 * int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that stack, and returns -1 if the stack with that given index is empty.
 * Example:
 * <p>
 * Input:
 * ["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
 * [[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
 * Output:
 * [null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
 * <p>
 * Explanation:
 * DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
 * D.push(1);
 * D.push(2);
 * D.push(3);
 * D.push(4);
 * D.push(5);         // The stacks are now:  2  4
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 2.  The stacks are now:     4
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.push(20);        // The stacks are now: 20  4
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.push(21);        // The stacks are now: 20  4 21
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.popAtStack(2);   // Returns 21.  The stacks are now:     4
 * 1  3  5
 * ﹈ ﹈ ﹈
 * D.pop()            // Returns 5.  The stacks are now:      4
 * 1  3
 * ﹈ ﹈
 * D.pop()            // Returns 4.  The stacks are now:   1  3
 * ﹈ ﹈
 * D.pop()            // Returns 3.  The stacks are now:   1
 * ﹈
 * D.pop()            // Returns 1.  There are no stacks.
 * D.pop()            // Returns -1.  There are still no stacks.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= capacity <= 20000
 * 1 <= val <= 20000
 * 0 <= index <= 100000
 * At most 200000 calls will be made to push, pop, and popAtStack.
 * 20190830
 */
public class DinnerPlateStacks {
    /**
     * 这题看似简单，但是有很多corner case，或者说你想不到的case；而这种case往往肉眼比较难以看出..如果看不出的话就要浪费很多时间调试，比赛基本就gg了。
     * 基本做法是用最小堆维护下一个空桶；corner case是remove空桶有技巧
     */
    class DinnerPlates {
        List<Stack<Integer>> list = new ArrayList<>();
        int capacity;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
        }

        public void push(int val) {
            if (pq.size() == 0) pq.offer(list.size());
            int availableStack = pq.poll();
            if (list.size() <= availableStack) {
                list.add(new Stack<>());
            }
            Stack<Integer> s = list.get(availableStack);
            s.push(val);
            if (s.size() != capacity) {//still available
                pq.offer(availableStack);
            }
        }

        public int pop() {
            if (list.size() == 0) return -1;
            return popAtStack(list.size() - 1);
        }

        public int popAtStack(int index) {
            if (index < 0 || list.size() <= index || list.get(index) == null || list.get(index).size() == 0) {
                return -1;
            }
            if (list.get(index).size() == capacity) {
                pq.offer(index);
            }
            int res = list.get(index).pop();
            if (index == list.size() - 1 && list.get(index).size() == 0) {//只能remove最后的bucket不能remove中间的bucket，否则后面的bucket会向左平移，index就变了
                list.remove(index);
                int i = list.size() - 1;
                while (i >= 0 && list.get(i) != null && list.get(i).size() == 0) list.remove(i--);//1 2 _ _ 5的情况，如果bucket5被remove了，那么把2~5之间所有bucket都remove
            }
            return res;
        }
    }
}
