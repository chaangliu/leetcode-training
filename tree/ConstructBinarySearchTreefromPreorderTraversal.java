package tree;

import java.util.Arrays;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * <p>
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 * <p>
 * 20190310
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    /**
     * 这题偷懒，就直接调用ConstructBinaryTreefromPreorderandInorderTraversal那题的函数吧。
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        return buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int preOrderStart, int preOrderEnd, int[] inorder, int inOrderStart, int inOrderEnd) {
        if (preOrderStart > preOrderEnd) return null;

        int val = preorder[preOrderStart];
        TreeNode node = new TreeNode(val);

        int pivot = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (val == inorder[i]) {
                pivot = i;
                break;
            }
        }
        node.left = construct(preorder, preOrderStart + 1, preOrderStart + (pivot - inOrderStart), inorder, inOrderStart, pivot - 1);
        node.right = construct(preorder, preOrderStart + pivot - inOrderStart + 1, preOrderEnd, inorder, pivot + 1, inOrderEnd);
        return node;
    }
}
