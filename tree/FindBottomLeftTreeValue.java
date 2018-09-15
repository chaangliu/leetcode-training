package tree;

/**
 * Created by DrunkPiano on 27/05/2017.
 */

public class FindBottomLeftTreeValue {
//	public int findBottomLeftValue(TreeNode root) {
//		int res = 0;
//		Queue<TreeNode> q = new LinkedList<>();
//		q.add(root);
//		int curNum = 1, nexNum = 0, preNum = 0;
//		while (!q.isEmpty()) {
//			TreeNode node = q.poll();
//			if (curNum == preNum) {
//				res = node.val;
//			}
//			curNum--;
//
//			if (node.left != null) {
//				q.add(node.left);
//				nexNum++;
//			}
//			if (node.right != null) {
//				q.add(node.right);
//				nexNum++;
//			}
//
//			if (curNum == 0) {
//				preNum = nexNum;
//				curNum = nexNum;
//				nexNum = 0;
//			}
//		}
//		return res;
//	}


//	int maxLvl = -1, res = 0;

	public int findBottomLeftValue(TreeNode root) {
		int level = 0;
		return dfs(root, level, -1, 0);
	}

	private int dfs(TreeNode node, int level, int maxLvl, int res) {
		if (node == null) return 10000;
		if (level > maxLvl) {
			maxLvl = level;
			res = node.val;
		}
		dfs(node.left, level + 1, maxLvl, res);
		dfs(node.right, level + 1, maxLvl, res);
		return res;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
//		right.left = new TreeNode(5);
//		right.left.left = new TreeNode(7);

		System.out.println(new FindBottomLeftTreeValue().findBottomLeftValue(root));
	}
}
