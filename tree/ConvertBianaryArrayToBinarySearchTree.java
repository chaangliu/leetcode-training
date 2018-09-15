package tree;

/**
 * ConvertBianaryArrayToBinarySearchTree
 * Created by DrunkPiano on 2017/3/27.
 */

class ConvertBianaryArrayToBinarySearchTree {

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
//        TreeNode root = new TreeNode(2);
//        TreeNode left = new TreeNode(1);
//        TreeNode right = new TreeNode(3);
//        root.left = left ;
//        root.right = right ;

        int[] nums = {1, 2, 3};
        ConvertBianaryArrayToBinarySearchTree obj = new ConvertBianaryArrayToBinarySearchTree();

        TreeNode res = obj.sortedArrayToBST(nums);
        System.out.println(res);
    }
}
