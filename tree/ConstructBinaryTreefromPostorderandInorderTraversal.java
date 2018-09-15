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

public class ConstructBinaryTreefromPostorderandInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        return construct(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode construct(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd) return null;

        int val = postorder[postEnd];
        TreeNode p = new TreeNode(val);
        int pivot = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                pivot = i;
            }
        }

        //pivot is not the length !!! needs to - inStart to get the length
        p.left = construct(inorder, inStart, pivot - 1, postorder, postStart, postStart + pivot - inStart - 1);
        p.right = construct(inorder, pivot + 1, inEnd, postorder, postStart + pivot - inStart, postEnd - 1);
        return p;
    }


    private void preorderTraverse(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preorderTraverse(root.left);
            preorderTraverse(root.right);
        }
    }
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        System.out.println("finalize.");
    }


    /**
     * preorder:  {7}, {10,4,3,1}, {2,8,11}
     * inorder:   {4,10,3,1}, {7}, {11, 8,2}
     */
    public static void main(String args[]) {
//        ConstructBinaryTreefromPostorderandInorderTraversal constructBinaryTreefromPreorderandInorderTraversal = new ConstructBinaryTreefromPostorderandInorderTraversal();
//        int[] preorder = {7, 10, 4, 3, 1, 2, 8, 11};
//        int[] inorder = {4, 10, 3, 1, 7, 11, 8, 2};
//        TreeNode result = constructBinaryTreefromPreorderandInorderTraversal.buildTree(preorder, inorder);
//		String s = "asd";
//		s.intern();
//        constructBinaryTreefromPreorderandInorderTraversal.preorderTraverse(result);
		System.out.println(Integer.parseInt("00"));
    }
}
