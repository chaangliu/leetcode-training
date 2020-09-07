package easy;

import java.util.Stack;

/**
 * Created by DrunkPiano on 12/07/2017.
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * to
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */

public class InvertBinaryTree {
    /**
     * 题意：翻转二叉树。
     * 解法：DFS。也可以BFS。
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    // 我习惯这么写，效果是一样的。
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // BFS
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>() {{
            add(root);
        }};
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}
