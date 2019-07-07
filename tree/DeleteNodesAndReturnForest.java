package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 * 20190707
 */
public class DeleteNodesAndReturnForest {

    /**
     * 这题我想着怎么才能从底向上操作，很自然地想到了post order dfs。
     */
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < to_delete.length; i++) set.add(to_delete[i]);
        dfs(root, set);
        if (root != null && !set.contains(root.val)) {
            res.add(root);
        }
        return res;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) return;
        dfs(root.left, set);
        dfs(root.right, set);

        if (root.left != null && set.contains(root.left.val)) {
            root.left = null;
        }
        if (root.right != null && set.contains(root.right.val)) {
            root.right = null;
        }
        if (set.contains(root.val)) {
            if (root.left != null) res.add(root.left);
            if (root.right != null) res.add(root.right);
        }
    }
}
