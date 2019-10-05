package tree;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Created by DrunkPiano on 2017/3/28.
 *
 * 20190211 reviewed at pvg airport
 */

class LowestCommonAncestorOfaBinaryTree {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		//1. 当前子树中没找到p,q 2. 找到了p,q中的一个
		if (null == root || root == p || root == q)
			return root;

		//在左右子树分别寻找p,q的LCA，我疑惑的是这个left，right分别是什么呢？从上面的return结果可以看出来就是null或者p或者q啊。
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		//在左子树，右子树都找到了p，q，说明这个root就是我们要找的node，p, q分别在root两侧
		if (left != null && right != null) {
			return root;
		}
		//只有一个为空，或者两个都为空的情况
		return left == null ? right : left;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		new LowestCommonAncestorOfaBinaryTree().lowestCommonAncestor(root , root.left.right , root.right.left);
	}
}
