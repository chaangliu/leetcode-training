package easy;

import tree.TreeNode;

/**
 * Created by DrunkPiano on 04/06/2017.
 */

public class ConstructStringfromBinaryTree {
	public String tree2str(TreeNode t) {
		return preOrder(t);
	}

	private String preOrder(TreeNode t) {
		if (t == null)
			return "";
		//右孩子是空的情况，为了避免加括号，避免preorder右孩子
		if (t.left != null && t.right == null) {
			return t.val + "(" + preOrder(t.left) + ")";
		}
		if (t.left == null && t.right == null) {
			return t.val + "";
		}
		return t.val + "(" + preOrder(t.left) + ")(" + preOrder(t.right) + ")";
	}
}
