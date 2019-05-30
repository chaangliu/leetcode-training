package dfs.divideandconquer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 * <p>
 * Example 1:
 * <p>
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 * <p>
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * -------------------
 * 20190530
 */
public class DifferentWaysToAddParentheses {
    /**
     * 这题跟Unique Binary Search Trees II很像，都是计算左半部分和右半部分，然后分别从里面取一个，计算结果。这就是分治思想。
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String left = input.substring(0, i);//divide成从[0,i - 1]和[i + 1, len - 1]；这里的input在递归过程中会被不断切割成小段
                String right = input.substring(i + 1);
                List<Integer> leftRes = diffWaysToCompute(left);//input被切割的左半部分能组成的所有计算结果（无需考虑去重）
                List<Integer> rightRes = diffWaysToCompute(right);
                for (int l : leftRes)
                    for (int r : rightRes) {
                        switch (input.charAt(i)) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));//断点发现单个数字在这里会被出现多次，input被切割成单个数字的时候。
        }
        return res;
    }

    /**
     * 优化：
     * 用Map减少递归次数
     */

    public List<Integer> diffWaysToCompute_MEMO(String input) {
        return dfs(input, new HashMap<>());
    }

    public List<Integer> dfs(String input, Map<String, List<Integer>> cache) {
        if (cache.containsKey(input)) return cache.get(input);
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String left = input.substring(0, i);//divide成从[0,i - 1]和[i + 1, len - 1]；这里的input在递归过程中会被不断切割成小段
                String right = input.substring(i + 1);
                List<Integer> leftRes = diffWaysToCompute(left);//input被切割的左半部分能组成的所有计算结果（无需考虑去重）
                List<Integer> rightRes = diffWaysToCompute(right);
                for (int l : leftRes)
                    for (int r : rightRes) {
                        switch (input.charAt(i)) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));//断点发现单个数字在这里会被出现多次，input被切割成单个数字的时候。
        }
        cache.put(input, res);
        return res;
    }

    public static void main(String args[]) {
        new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5");
    }
}
