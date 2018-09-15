package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 27/07/2017.
 */

public class BinaryTreePreorderTraversal {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		return dfs(root, res);
	}

	private List<Integer> dfs(TreeNode root, List<Integer> res) {
		if (root == null) {
			return res;
		}
		res.add(root.val);
		dfs(root.left, res);
		dfs(root.right, res);
		return res;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		new BinaryTreePreorderTraversal().preorderTraversal(root);
	}
}
