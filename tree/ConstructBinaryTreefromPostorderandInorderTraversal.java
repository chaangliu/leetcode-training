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
    /**
     * 题意：从inorder和postorder序列恢复二叉树。
     * 解法：和给出inorder+preorder一样，维护一个全局rootIdx然后--，注意先生成右子树。
     */
    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            rootIdx = postorder.length - 1;
            return dfs(inorder, postorder, 0, inorder.length - 1);
        }

        int rootIdx;

        private TreeNode dfs(int[] inorder, int[] postorder, int l, int r) {
            if (l > r) return null;
            if (rootIdx < 0) return null;
            int rootVal = postorder[rootIdx--];
            int mid = -1;
            for (int i = l; i <= r; i++) {
                if (inorder[i] == rootVal) {
                    mid = i;
                    break;
                }
            }
            TreeNode node = new TreeNode(rootVal);
            node.right = dfs(inorder, postorder, mid + 1, r);
            node.left = dfs(inorder, postorder, l, mid - 1);
            return node;
        }
    }


    /**
     * 过往提交
     */
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
}
