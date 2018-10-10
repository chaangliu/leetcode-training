package easy;

/**
 * Created by DrunkPiano on 12/07/2017.
 * 	4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * to
 * 	4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */

public class InvertBinaryTree {

	//20181010 review
	//Approach 1. 自顶向下。先交换，再递归执行子树。
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}

	//Approach 2. 自底向上。
//	public TreeNode invertTree(TreeNode root) {
//		if(root == null) return null;
//		TreeNode left = invertTree(root.right);//新的左子树就是翻转后的root.right
//		TreeNode right = invertTree(root.left);
//		root.left = left;
//		root.right = right;
//		return root;
//	}

	//original
//	public TreeNode invertTree(TreeNode root) {
//		if (root == null) {
//			return null;
//		}
//		TreeNode tmp = root.left;
//		root.left = root.right;
//		root.right = tmp;
//		invertTree(root.left);
//		invertTree(root.right);
//		return root;
//	}
}
