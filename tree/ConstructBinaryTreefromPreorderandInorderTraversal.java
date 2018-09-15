package tree;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Created by DrunkPiano on 2017/2/19.
 * <p>
 * <p>
 * <p>
 * * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    private TreeNode construct(int[] preorder, int preOrderStart, int preOrderEnd, int[] inorder, int inOrderStart, int inOrderEnd) {
        //important to have return null
//        if (preOrderStart > preOrderEnd || inOrderStart > inOrderEnd) return null;
        if (preOrderStart > preOrderEnd) return null;

        int val = preorder[preOrderStart];
        TreeNode node = new TreeNode(val);

        int pivot = 0;
//        for (int i = 0; i < inOrderEnd; i++) {---->silly mistake
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


    private void preorderTraverse(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preorderTraverse(root.left);
            preorderTraverse(root.right);
        }
    }

    /**
     * preorder:  {7}, {10,4,3,1}, {2,8,11}
     * inorder:   {4,10,3,1}, {7}, {11, 8,2}
     */
    public static void main(String args[]) {
        ConstructBinaryTreefromPreorderandInorderTraversal constructBinaryTreefromPreorderandInorderTraversal = new ConstructBinaryTreefromPreorderandInorderTraversal();
        int[] preorder = {7, 10, 4, 3, 1, 2, 8, 11};
        int[] inorder = {4, 10, 3, 1, 7, 11, 8, 2};
        TreeNode result = constructBinaryTreefromPreorderandInorderTraversal.buildTree(preorder, inorder);

        constructBinaryTreefromPreorderandInorderTraversal.preorderTraverse(result);

    }
}
