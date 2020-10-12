package stack.mono;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
class MaxQueue {
    /**
     * 题意：实现一个MaxQueue类，要求能O(1)时间获取队列的最大值。
     * 解法：跟sliding window maximum的操作一模一样，双端队列。
     * push_back的时候从队尾（也就是栈顶（右边），也就是最近加入的）开始，把小于即将加入的value的数出队，因为这些数都没用利用价值了，要用也是用value；
     */
    Deque<Integer> mono = new ArrayDeque<>();
    Queue<Integer> q = new LinkedList<>(); // mono: 单调队列，左边大右边小

    public MaxQueue() {

    }

    public int max_value() {
        if (mono.isEmpty()) return -1;
        return mono.peekFirst();
    }

    public void push_back(int value) {
        q.offer(value);
        while (!mono.isEmpty() && mono.peekLast() < value) { // 从队尾（也就是栈顶（右边），也就是最近加入的）开始，把小于即将加入的value的数出队，因为这些数都没用利用价值了，要用也是用value；
            mono.pollLast();
        }
        mono.offer(value);
    }

    public int pop_front() {
        if (q.isEmpty()) return -1;
        int res = q.poll(); // 这儿如果不poll，先peek最后再poll就会WA，why？
        if (!mono.isEmpty() && res == mono.peekFirst()) mono.pollFirst();
        return res;
    }
}