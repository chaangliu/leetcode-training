package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class HasIntersections {
    /**
     * 听朋友说的一道interview question：判断一些intervals里是否有交叉的。[1,3][2,4]算交叉，[1,2][1,4]不算，[1,2][2,4]也不算。要求时间复杂度<O(n^2)
     * 我的思路是，先把start，end排序；然后把所有start都入栈，每次遇到end都从栈顶判断是否属于同一个pair。这样事件复杂度是O(nlogn)
     */
    boolean solve(int[][] A) {
        //Arrays.sort(A, (a, b) -> a[0] - b[0]);
        ArrayList<Node> list = new ArrayList<>();
        for (int[] a : A) {
            list.add(new Node(true, a[0]));
            list.add(new Node(false, a[1]));
        }
        Collections.sort(list, (a, b) -> a.val - b.val);
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] a : A) {
            map.putIfAbsent(a[1], new HashSet<>());
            map.get(a[1]).add(a[0]);
        }
        Stack<Node> stack = new Stack<>();
        for (Node n : list) {
            if (n.isStart) {
                stack.push(n);
            } else {
                if (stack.isEmpty() || !map.get(n.val).contains(stack.pop().val))
                    return true;
            }
        }
        return false;
    }

    class Node {
        boolean isStart;
        int val;

        Node(boolean b, int v) {
            isStart = b;
            val = v;
        }
    }
}
