package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * <p>
 * 20190218
 */
public class DailyTemperatures {

    //approach1
    //从后往前，比如对于[73, 74, 75, 71, 69, 72, 76, 73]，到72的时候，就在[73,100]在next[]数组寻找最小的index；这样的复杂度是O(NW)，where NN is the length of T and WW is the number of allowed values for T[i]. Since W = 71W=71, we can consider this complexity O(N).
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }

    /**
     * approach2 单调栈
     * 从后往前遍历，用一个栈保存index，遍历到一个数的时候先把栈中所有比它小的数的index出栈，再入栈这个数的index。因为后面的都没价值了。有点像QueueReconstructionByHeight那题，在两个高个子的人中间的矮子都没存在的必要了，因为不会用他们做参照物😄
     * 这个就是单调栈的应用，这里用的是「小顶栈」（我发明的词）。
     *
     * 这题google面过
     */
    public int[] dailyTemperatures__STACK(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> indicesStack = new Stack<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!indicesStack.empty() && T[indicesStack.peek()] <= T[i]) {
                indicesStack.pop();
            }
            res[i] = indicesStack.empty() ? 0 : indicesStack.peek() - i;//单调栈的peek存放着第一个比自己大的数的index，正是我们要找的index
            indicesStack.push(i);
        }
        return res;
    }

    /**
     * 单调栈写法二
     * 小顶栈，left to right。没有前面那种直观
     */
    public int[] dailyTemperatures__MONOSTACK2(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> indicesStack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!indicesStack.empty() && T[indicesStack.peek()] < T[i]) {//考虑1，2，3..
                res[indicesStack.peek()] = i - indicesStack.peek();
                indicesStack.pop();
            }
            indicesStack.push(i);
        }
        return res;
    }

}
