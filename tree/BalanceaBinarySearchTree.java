package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 * <p>
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * If there is more than one answer, return any of them.
 * Example 1:
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is between 1 and 10^4.
 * The tree nodes will have distinct values between 1 and 10^5.
 * 20200315
 */
public class BalanceaBinarySearchTree {
    /**
     * 题意：给你一个bst，把它变成一个平衡bst。
     * 解法：类似二分法地buildTree，很容易写，感觉在哪见过。
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getNode(root, list);
        return buildTree(list, 0, list.size() - 1);
    }

    private TreeNode buildTree(List<Integer> list, int l, int r) {
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildTree(list, l, mid - 1);
        root.right = buildTree(list, mid + 1, r);
        return root;
    }

    private void getNode(TreeNode node, List<Integer> list) {
        if (node == null) return;
        getNode(node.left, list);
        list.add(node.val);
        getNode(node.right, list);
    }
}
