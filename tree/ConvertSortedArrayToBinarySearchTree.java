package tree;

/**
 * Given an array where elements are sorted in ascending order, convert it to a 【height balanced】BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *
 * Created by DrunkPiano on 2017/3/27.
 */

class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    //mention the parameters
    private TreeNode recursion(int nums[], int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursion(nums, left, mid - 1);
        root.right = recursion(nums, mid + 1, right);

        return root;
    }

    public static void main(String args[]) {
        int[] nums = {1, 2, 3};
        ConvertSortedArrayToBinarySearchTree obj = new ConvertSortedArrayToBinarySearchTree();
        TreeNode res = obj.sortedArrayToBST(nums);
        System.out.println(res);
    }
}
