package jianzhioffer;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * <p>
 * LeetCoded 的 min stack一样。
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
 */

public class MinStack {

    //又没想出来。。想到了priorityqueue，但是pq的插入复杂度不是O(1)
    //常规方法是两个栈

    //20190216 这次想出来了。
    //另外在leetcode上看到了一个stack的方法:https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1360/discuss/49014/Java-accepted-solution-using-one-stack

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (minStack.empty() || node < minStack.peek()) { //已犯错误，忘记检查minStack是否是空，不检查会导致minstack无法加入第一个元素，另外，minStack.empty会产生exception
            minStack.push(node);
        }
    }

    public void pop() {
        int num = stack.pop();
        if (minStack.peek() == num) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public static void main(String args[]) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        System.out.print(minStack.min() + ", ");
        minStack.push(4);
        minStack.push(2);
        minStack.push(3);
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.push(0);
    }
}
