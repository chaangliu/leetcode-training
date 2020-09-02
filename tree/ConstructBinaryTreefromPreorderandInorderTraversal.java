package tree;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * Created by DrunkPiano on 2017/2/19.
 * 20190211 review @ PVG airport
 * 20200123 review @ SZ
 */

public class ConstructBinaryTreefromPreorderandInorderTraversal {

    /**
     * 题意：给你一个前序遍历和中序遍历的数组，让你还原一棵树。
     * 解法：维护preOrder的index和inorder的左右子树的起始和结束index。
     * 20200522review: 这题关键在于右子树的root index的确定，如果不用全局变量记录，你需要跳过整颗左子树去定位右边子树的root位置。
     * 20200902review: 这题其实就是给定边界，向左向右递归建树
     */
    class Solution {
        int rootIdx = 0;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return dfs(preorder, inorder, 0, preorder.length - 1);
        }

        private TreeNode dfs(int[] preorder, int[] inorder, int l, int r) {
            if (l > r) return null;
            int num = preorder[rootIdx++];
            int pivot = -1;
            for (int i = 0; i < inorder.length; i++)
                if (inorder[i] == num) {
                    pivot = i;
                    break;
                }
            TreeNode root = new TreeNode(num);
            root.left = dfs(preorder, inorder, l, pivot - 1);
            root.right = dfs(preorder, inorder, pivot + 1, r);
            return root;
        }
    }

    int preOrderIndex;

    public TreeNode buildTree__2019(int[] preorder, int[] inorder) {
        preOrderIndex = 0;
        return helper(inorder, preorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int[] preorder, int inLow, int inHigh) {
        if (inLow > inHigh || preOrderIndex > preorder.length - 1) return null;
        TreeNode root = new TreeNode(preorder[preOrderIndex]);
        int pivot = -1;
        for (int i = inLow; i <= inHigh; i++) {
            if (inorder[i] == preorder[preOrderIndex]) {//关键：在inorder里找root所在的index，这里就能确定左右子树的范围
                pivot = i;
                break;
            }
        }
        preOrderIndex++;//这儿是另一个关键，如果这儿不写成全局变量，那左边root变为preOrderIndex+1；而右边root变为preStart + pivot - inStart + 1
        root.left = helper(inorder, preorder, inLow, pivot - 1);
        root.right = helper(inorder, preorder, pivot + 1, inHigh);
        return root;
    }


    //---------------------------------------------------------------------------------------------------------------

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


    private void preorderTraverse(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preorderTraverse(root.left);
            preorderTraverse(root.right);
        }
    }


    /**
     * ---------------------------------------------------------------------------------------------------------------
     * 2020 review
     */
    public TreeNode buildTree_(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, preorder.length - 1);
    }

    int rootPos = 0;

    private TreeNode dfs(int[] preorder, int[] inorder, int l, int r) {
        if (rootPos >= preorder.length) return null;
        if (l > r) return null;
        // if (l == r) return new TreeNode(inorder[l]);
        int midPos = -1;
        for (int i = l; i <= r; i++) {
            if (inorder[i] == preorder[rootPos]) {
                midPos = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[rootPos]);
        rootPos++;
        root.left = dfs(preorder, inorder, l, midPos - 1);
        root.right = dfs(preorder, inorder, midPos + 1, r);
        return root;
    }
}
