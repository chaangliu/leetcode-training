package tree;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class MergeTwoBinaryTrees {
	int value;

	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		TreeNode res = dfs(t1, t2);
		return res;
	}


	private TreeNode dfs(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		if (t1 != null && t2 != null) {
			value = t1.val + t2.val;
		} else if (t2 != null) {
			return t2;
		} else {
			return t1;
		}
		TreeNode node = new TreeNode(value);
		node.left = mergeTrees(t1.left, t2.left);
		node.right = mergeTrees(t1.right, t2.right);
		return node;
	}


	public static void main(String args[]) {
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);

		TreeNode root2 = new TreeNode(4);
		root2.left = new TreeNode(5);
		root2.right = new TreeNode(6);
		new MergeTwoBinaryTrees().mergeTrees(root1, root2);
	}
}
