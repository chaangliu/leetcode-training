package tree;

import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * Example:
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 */

public class BSTIterator {

    ///20190110
    ///这题跟BinaryTreeInorderTraversal一样思路，用Stack模拟中序遍历，因为一直在pop，所以空间是O(n)。在纸上画一下模拟一下就好。
    Stack<TreeNode> mStack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            mStack.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = mStack.pop();
        TreeNode right = node.right;
        while (right != null) {
            mStack.push(right);
            right = right.left;
        }
        return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !mStack.empty();
    }


    public static void main(String args[]) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode(5);
        BSTIterator bstIterator = new BSTIterator(node);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }
}
