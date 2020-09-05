package dfs;

/**
 * Created by DrunkPiano on 16/06/2017.
 */

public class PermutationSequence {
    /**
     * 题意：给你n个数字，求第k个permutation。
     * 解法：Math没看，暴力permutation也能过。
     */
    class Solution {
        int k = 0;

        public String getPermutation(int n, int k) {
            this.k = k;
            return permute("", n, new boolean[n + 1]);
        }

        private String permute(String cur, int n, boolean[] visited) {
            if (cur.length() == n) {
                if (--k == 0) {
                    return cur;
                } else return "";
            }
            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                String r = permute(cur + i, n, visited);
                if (r.length() != 0) return r;
                visited[i] = false;
            }
            return "";
        }
    }

    String res = "";

    public String getPermutation(int n, int k) {
        int[] count = new int[1];
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
        }
        backtracking(count, k, num, "", new boolean[num.length]);
        return res;

    }

    private void backtracking(int[] count, int k, int[] nums, String item, boolean[] used) {
        if (item.length() == nums.length) {
            if (++count[0] == k) {
                res = item;
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                backtracking(count, k, nums, item + nums[i], used);
                used[i] = false;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(new PermutationSequence().getPermutation(3, 5));
    }
}
