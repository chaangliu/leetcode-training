package jianzhioffer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */

public class ImplementingAQueueWithTwoStacks {

    //我的解法。比较简单的思考，每pop一次都要重新遍历两次。但其实没必要，只要stack2不为空，就一直可以pop；stack1一直pending就可以。
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

//    public void push(int node) {
//        stack1.push(node);
//    }
//
//    public int pop() {
//        while (!stack1.empty()) {
//            stack2.push(stack1.pop());
//        }
//        int res = stack2.pop();
//        while (!stack2.empty()) {
//            stack1.push(stack2.pop());
//        }
//        return res;
//    }

    //效率高的解法。
    //20100108 review 可以这么想，stack2的都是与stack1反向的，如果stack2空了，就从stack1里面拿。
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
