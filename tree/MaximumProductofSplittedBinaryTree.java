package tree;

import java.util.HashMap;

/**
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
 * <p>
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * Example 3:
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * Example 4:
 * Input: root = [1,1]
 * Output: 1
 * Constraints:
 * Each tree has at most 50000 nodes and at least 2 nodes.
 * Each node's value is between [1, 10000].
 * 20200319
 */
public class MaximumProductofSplittedBinaryTree {
    /**
     * 题意：把一棵树中的一个edge断开，剩下的两部分乘在一起的最大值是多少？
     * 解法：注意不要把sum理解成当前node的sum，而是要先遍历一遍，算出整棵树的sum，接下来再遍历一遍，注意在遍历的过程中，就可以求当前node的sum和剩下的部分的乘积。
     * 这题关键是overflow，注意total和max都有可能overflow。
     */
    long total = -1, MOD = (int) 1e9 + 7, max = 0;

    public int maxProduct(TreeNode root) {
        total = sum(root);
        sum(root);
        return (int) (max % MOD);
    }

    private int sum(TreeNode root) {
        if (root == null) return 0;
        int res = root.val + sum(root.left) + sum(root.right);
        if (total != -1) max = Math.max(max, (total - res) * res);
        return res;
    }


    /**
     * 我一开始想要借助map，但其实没必要，因为都有遍历两次的。
     */
    HashMap<TreeNode, Integer> memo = new HashMap<>();
    int sum = -1, mod = (int) 1e9 + 7;

    public int maxProduct_(TreeNode root) {
        if (root == null) return 0;
        // int cur = getSum(root, memo);
        if (sum == -1) sum = getSum(root, memo);
        int left = getSum(root.left, memo) % mod;
        int right = getSum(root.right, memo) % mod;
        int cutLeft = (sum - left) * left % mod;
        int cutRight = (sum - right) * right % mod;
        int res = Math.max(cutLeft, cutRight) % mod;
        res = Math.max(res, Math.max(maxProduct_(root.left), maxProduct_(root.right))) % mod;
        return res % mod;
    }

    private int getSum(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int res = root.val + getSum(root.left, memo) % mod + getSum(root.right, memo) % mod;
        memo.put(root, res);
        return res % mod;
    }
}
