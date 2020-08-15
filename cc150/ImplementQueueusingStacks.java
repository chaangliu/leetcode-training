package cc150;

import java.util.Stack;

public class ImplementQueueusingStacks {
    /**
     * 题意：用两个栈实现一个队列。
     * 解法：模拟。取只从helper取，push往main里面push。注意：main不要打成mian😂
     */
    class MyQueue {
        Stack<Integer> main = new Stack<>(), helper = new Stack<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            main.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (!helper.empty()) return helper.pop(); // 或者直接调用一下peek再helper.pop()
            while (!main.empty()) helper.push(main.pop());
            return helper.empty() ? -1 : helper.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (!helper.empty()) return helper.peek();
            while (!main.empty()) helper.push(main.pop());
            return helper.empty() ? -1 : helper.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return helper.empty() && main.empty();
        }
    }
}
