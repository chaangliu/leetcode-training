package cc150;

import java.util.Stack;

/**
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 * 示例1:
 * 输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null,null,null,1,null,2]
 * 示例2:
 * <p>
 * 输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 * 输出：
 * [null,null,null,null,null,true]
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 * 20200812
 */
public class SortOfStacks {
    /**
     * 题意：实现一个stack，这个stack的pop总能pop出最小的元素。只能借助一个额外的stack结构。
     * 解法：我一开始以为是MinStack那题(用O(1)时间检索最小元素)那种，发现不是，这题是需要最小元素真的在顶部。看讨论发现是类似汉诺塔。
     */
    class SortedStack {
        Stack<Integer> main = new Stack<>(), helper = new Stack<>();

        public SortedStack() {

        }

        // 3 5 1 4
        // 类似于汉诺塔，A柱上只能上面小下面大地放，当中途插进一个比A.peek大的数，就借助B柱，先把A上的比val小的都移到B柱，再移动回来
        public void push(int val) {
            if (!main.isEmpty() && val > main.peek()) {
                while (!main.isEmpty() && val > main.peek()) {
                    helper.push(main.pop());
                }
                main.push(val);
                while (!helper.isEmpty()) main.push(helper.pop());
            } else {
                main.push(val);
            }
        }

        public void pop() {
            if (!main.isEmpty()) main.pop();
        }

        public int peek() {
            return main.isEmpty() ? -1 : main.peek();
        }

        public boolean isEmpty() {
            return main.isEmpty();
        }
    }
}
