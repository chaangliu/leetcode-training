package dfs;

import tree.TreeNode;

/**
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * <p>
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * <p>
 * Return the sum of these numbers.
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is between 1 and 1000.
 * node.val is 0 or 1.
 * The answer will not exceed 2^31 - 1.
 * <p>
 * 20190407
 */
public class SumOfRootToLeafBinaryNumbers {
    /**
     * 这题要mod 1e9+7才能过，很坑(大数阶乘，大数的排列组合等，一般都要求将输出结果对 1000000007 取模。)
     */
    int sum = 0, mod = (int) 1e9 + 7;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, long cur) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            sum += (cur * 2 + root.val) % mod;
            return;
        }
        dfs(root.left, (cur * 2 + root.val) % mod);
        dfs(root.right, (cur * 2 + root.val) % mod);
    }


    /**
     * 另种写法
     */
    public int sumRootToLeaf__(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        val = (val * 2 + root.val) % mod;
        return (root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val)) % mod;
    }
}
