package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 20200802
 */
public class FlattenBinaryTreetoLinkedList {
    /**
     * 题意：把一棵二叉树打成一根向右的棍子。
     * 解法：preorder 遍历。
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}
