package tree;

import java.util.HashMap;
import java.util.Map;

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
 * ----
 * <p>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */

public class ConstructBinaryTreefromPostorderandInorderTraversal {
    //20190210 review
    int postRightIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postRightIndex = postorder.length - 1;
        return helper(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int[] postorder, int inLow, int inHigh) {
        if (inLow > inHigh || postRightIndex < 0) return null;
        TreeNode root = new TreeNode(postorder[postRightIndex]);
        int pivot = -1;
        for (int i = inLow; i <= inHigh; i++) {//可用Map优化
            if (inorder[i] == postorder[postRightIndex]) {
                pivot = i;
                break;
            }
        }
        postRightIndex--;//这个原来写成了递归的参数，后来发现回溯之后index又回去了，显然不是想要的。所以写成全局变量
        root.right = helper(inorder, postorder, pivot + 1, inHigh);//观察两个序列想到的，先递归右边
        root.left = helper(inorder, postorder, inLow, pivot - 1);
        return root;
    }

    //----- 20181017 review -----
    public TreeNode buildTree__17(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(postorder, postorder.length - 1, 0, inorder.length - 1, map);
    }

    //思路与105题一样，仍然是找root在inorder中的位置，递归构造左右子树。
    private TreeNode helper(int[] postorder, int postIdx, int inStart, int inEnd, Map idxMap) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postIdx]);

        int pivot = (int) idxMap.get(postorder[postIdx]);
        //难点是新的postIdx，一开始写成了postIdx - (inorder.length - pivot) - 1，想法是对的但是没考虑周全。改了一次之后AC了。
        root.left = helper(postorder, postIdx - (inEnd - pivot) - 1, inStart, pivot - 1, idxMap);
        root.right = helper(postorder, postIdx - 1, pivot + 1, inEnd, idxMap);
        return root;
    }


    //----- original thread -----

//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        if (inorder == null || postorder == null) return null;
//        return construct(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
//    }
//
//    public TreeNode construct(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
//
//        if (inStart > inEnd || postStart > postEnd) return null;
//
//        int val = postorder[postEnd];
//        TreeNode p = new TreeNode(val);
//        int pivot = 0;
//        for (int i = 0; i < inorder.length; i++) {
//            if (inorder[i] == val) {
//                pivot = i;
//            }
//        }
//
//        //pivot is not the length !!! needs to - inStart to get the length
//        p.left = construct(inorder, inStart, pivot - 1, postorder, postStart, postStart + pivot - inStart - 1);
//        p.right = construct(inorder, pivot + 1, inEnd, postorder, postStart + pivot - inStart, postEnd - 1);
//        return p;
//    }
//
//
//    private void preorderTraverse(TreeNode root) {
//        if (root != null) {
//            System.out.println(root.val);
//            preorderTraverse(root.left);
//            preorderTraverse(root.right);
//        }
//    }
//    @Override
//    protected void finalize() throws Throwable
//    {
//        super.finalize();
//        System.out.println("finalize.");
//    }
//
//
//    /**
//     * preorder:  {7}, {10,4,3,1}, {2,8,11}
//     * inorder:   {4,10,3,1}, {7}, {11, 8,2}
//     */
//    public static void main(String args[]) {
////        ConstructBinaryTreefromPostorderandInorderTraversal constructBinaryTreefromPreorderandInorderTraversal = new ConstructBinaryTreefromPostorderandInorderTraversal();
////        int[] preorder = {7, 10, 4, 3, 1, 2, 8, 11};
////        int[] inorder = {4, 10, 3, 1, 7, 11, 8, 2};
////        TreeNode result = constructBinaryTreefromPreorderandInorderTraversal.buildTree(preorder, inorder);
////		String s = "asd";
////		s.intern();
////        constructBinaryTreefromPreorderandInorderTraversal.preorderTraverse(result);
//		System.out.println(Integer.parseInt("00"));
//    }
}
