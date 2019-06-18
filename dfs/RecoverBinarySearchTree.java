package dfs;

import tree.TreeNode;
/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * Follow up:
 * <p>
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {

    /**
     * 这题很简单但是我搞了很久，两个case总是有一个过不了。。后来发现没有用TreeNode来存储，太蠢
     */
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;
        traverse(root.right);
    }
//    int big = Integer.MIN_VALUE, small = Integer.MAX_VALUE;
//    boolean bigWasFound = false, shouldContinue = false;
//
//    public void recoverTree(TreeNode root) {
//        dfs1(null, root);
//        dfs2(root);
//        System.out.print("GG");
//    }
//
//    /**
//     * 找出inorder下第一个比后面的数大的数、第一个比前面的数小的数
//     **/
//    private void dfs1(TreeNode pre, TreeNode cur) {
//        if (cur == null) return;
//        dfs1(cur, cur.left);
//        if (!bigWasFound && pre != null && pre.val > cur.val) {
//            big = pre.val;
//            bigWasFound = true;
//        }
//        if (shouldContinue && bigWasFound && pre != null && pre.val > cur.val) {
//            small = cur.val;
//            cur.val = big;
//            return;
//        }
//        if (bigWasFound) shouldContinue = true;
//        dfs1(cur, cur.right);
//    }
//
//    private void dfs2(TreeNode node) {
//        if (node == null) return;
//        dfs2(node.left);
//        if (node.val == big) {
//            node.val = small;
//            return;
//        }
//        dfs2(node.right);
//    }

}
