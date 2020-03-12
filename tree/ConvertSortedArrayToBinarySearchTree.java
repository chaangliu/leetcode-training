package tree;

/**
 * Given an array where elements are sorted in ascending order, convert it to a 【height balanced】BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example:
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/27.
 */

class ConvertSortedArrayToBinarySearchTree {
    /**
     * 题意：给你一个sorted array，构建一个balanced bst.
     * 解法：从上到下buildTree就行了。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] A, int l, int r) {
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        TreeNode node = new TreeNode(A[mid]);
        node.left = dfs(A, l, mid - 1);
        node.right = dfs(A, mid + 1, r);
        return node;
    }
}
