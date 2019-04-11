package stack;

import java.util.Stack;

/**
 * Given two sequences pushed and popped with distinct values, return true if and only if
 * this could have been the result of a sequence of push and pop operations on an initially empty stack.
 * Example 1:
 * <p>
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 * <p>
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 */
public class ValidateStackSequences {
    //剑指offer原题StackPopOrder。出得比leetcode早。
    //调试了好多次。。记得看下原答案
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length)
            return true;
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0, popIndex = 0;
        while (popIndex < popped.length) {
            if (stack.empty() || stack.peek() != popped[popIndex]) {
                if (pushIndex >= pushed.length) return false;//debug发现的case
                stack.push(pushed[pushIndex]);
                pushIndex++;
            } else {
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();
    }


    /**
     * 20190411review
     * 用一个stack模拟push，每push一个数就检查栈顶是否和pop队列的指针j指向的一致，是的话就要不停弹出，因为此时不贪就没机会贪了，是所谓「贪心」
     * If the stack has say, 2 at the top, then if we have to pop that value next, we must do it now.
     * That's because any subsequent push will make the top of the stack different from 2, and we will never be able to pop again.
     */
    public boolean validateStackSequences__(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == N;
    }

    public static void main(String args[]) {
//        int[] pushed = {1, 0};
//        int[] popped = {1, 0};
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 3, 5, 1, 2};
        new ValidateStackSequences().validateStackSequences(pushed, popped);
    }
}
