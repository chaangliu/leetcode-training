package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.
 * <p>
 * The function is constantly increasing, i.e.:
 * <p>
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * The function interface is defined like this:
 * <p>
 * interface CustomFunction {
 * public:
 * // Returns positive integer f(x, y) for any given positive integer x and y.
 * int f(int x, int y);
 * };
 * For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent one function from an secret internal list, on the examples you'll know only two functions from the list.
 * <p>
 * You may return the solutions in any order.
 * Example 1:
 * <p>
 * Input: function_id = 1, z = 5
 * Output: [[1,4],[2,3],[3,2],[4,1]]
 * Explanation: function_id = 1 means that f(x, y) = x + y
 * Example 2:
 * <p>
 * Input: function_id = 2, z = 5
 * Output: [[1,5],[5,1]]
 * Explanation: function_id = 2 means that f(x, y) = x * y
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= function_id <= 9
 * 1 <= z <= 100
 * It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
 * It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000
 * 20191027
 */
public class FindPositiveIntegerSolutionforaGivenEquation {
    /**
     * 题意：给你一个函数f(x,y) = z和z的值，告诉你这个函数是随着x,y单调增的；求x,y的解集
     * 读题读了半天，一开始想了O(n^2)的解法，看到单调增之后就想到binary search。
     * 实际上我看了不用binary search也能过，只要及时break。
     * 另外lee表示这题可以看做矩阵行列都increasing的那题来做。虽然感觉时间上并不会快。
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            int l = 1, r = 1000;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                System.out.println("mid is " + mid);
                if (customfunction.f(i, mid) == z) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(mid);
                    res.add(item);
                    break;
                }
                if (customfunction.f(i, mid) < z) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return res;
    }

    abstract class CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public abstract int f(int x, int y);
    }
}
