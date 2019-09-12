package dfs;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * <p>
 * Example:
 * <p>
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding 11,22,33,44,55,66,77,88,99
 * 20190912
 */
public class CountNumberswithUniqueDigits {
    /**
     * dfs做法，类似permutations
     */
    public int countNumbersWithUniqueDigits(int n) {
        int bound = (int) Math.pow(10, n);
        boolean visited[] = new boolean[10];
        for (int i = 1; i < 10; i++) {//最高位分开枚举，1~9；值得学习。这个最高位不一定严格是n，也可能是短于n的数的最高位
            visited[0] = true;
            backtrack(i, bound, visited);
            visited[0] = false;
        }
        return res;
    }

    int res = 1;

    private void backtrack(long prev, int bound, boolean visited[]) {
        if (prev >= bound) {
            return;
        }
        res++;
        for (int i = 0; i < 10; i++) {//当前位置能填的数字
            if (visited[i]) continue;
            visited[i] = true;
            System.out.println((prev * 10 + i));
            backtrack(prev * 10 + i, bound, visited);
            visited[i] = false;
        }
    }
}
