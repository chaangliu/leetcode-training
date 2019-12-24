package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllPossiblePopSequencesOfAStack {
    /**
     * 听来的一个interview question, 题意是给你一些入栈顺序，问所有可能的出栈顺序。
     * 两个递归，第一个负责不停地push，第二个负责pop，从1个pop到stack.size()个。
     * 参考： https://www.cnblogs.com/wj033/p/9124249.html
     */

    private static void dfs(int start, int n, Stack<Integer> stack, List<Integer> cell, int count, List<List<Integer>> res) {
        if (cell.size() == n) {
            count++;
            res.add(new ArrayList<>(cell));
        }

        if (start <= n) {
            stack.push(start);
            dfs(start + 1, n, stack, cell, count, res);
            stack.pop();// backtrack
        }

        if (stack.size() > 0) {
            int temp = stack.peek();
            stack.pop();
            cell.add(temp);
            System.out.println(cell.toString());
            dfs(start, n, stack, cell, count, res);// start:当前操作的数字
            stack.push(temp);
            cell.remove(cell.size() - 1);
        }
    }

    public static void main(String args[]) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, 3, new Stack<>(), new ArrayList<>(), 0, res);
        System.out.print(res.toString());
    }
}
