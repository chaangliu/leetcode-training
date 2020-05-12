package stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * 20190216review
 */
class MinStack {
    /**
     * 题意：实现一个能O(1)获取最小元素的Stack。
     * 解法：辅助单调栈。
     * 除了用2个stack，这题也可以只用一个stack做, 思路来自leetcode:
     * 如果将要push的元素比当前min小，就把oldMin也push进去，这样，在最小的数下面就维护了一个第二小的元素
     * 但是这么做并没有什么优点，不容易理解而且占用的空间也是O(n)
     */
    class MinStack__ONSTACK {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if (stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }


    //initial post
    private Stack<Integer> mStack;
    private Stack<Integer> mMinStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        mStack = new Stack<>();
        mMinStack = new Stack<>();
    }

    public void push(int x) {
        mStack.push(x);
        if (mMinStack.empty() || mMinStack.peek() >= x) {//注意这里要>=，否则[0][1][0] push push push getmin pop getmin会有问题
            mMinStack.push(x);
        }
    }

    public void pop() {
        int x = mStack.pop();
        if (mMinStack.peek() == x) {
            mMinStack.pop();
        }
    }

    public int top() {
        return mStack.peek();
    }

    public int getMin() {
        return mMinStack.peek();
    }
}

class MinStack_ {

    Stack<Integer> stack = new Stack();
    Stack<Integer> mono = new Stack();

    /**
     * initialize your data structure here.
     */
    public MinStack_() {

    }

    public void push(int x) {
        stack.push(x);
        if (mono.empty() || mono.peek() >= x) {
            mono.push(x);
        }
    }

    public void pop() {
        if (mono.peek().equals(stack.pop())) mono.pop(); // 20200512review 这儿如果用==就会WA，因为自动装箱的缘故，装进两个stack的不是同一个Integer对象了
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mono.peek();
    }
}
