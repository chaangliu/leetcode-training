package tree;

/**
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 * <p>
 * <p>
 * <p>
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 * <p>
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * <p>
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 * <p>
 * 20190330
 */
public class LeafSimilarTrees {
    /**
     * 解法：dfs
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        dfs(s1, root1);
        dfs(s2, root2);
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i)) return false;
        return true;
    }

    private void dfs(StringBuilder sb, TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            sb.append(node.val);
        }
        dfs(sb, node.left);
        dfs(sb, node.right);
    }
}
