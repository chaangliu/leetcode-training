package array;

import java.util.Stack;

import tree.TreeNode;

/**
 * Created by DrunkPiano on 2017/3/25.
 */

public class KthSmallestElementInaBST {
	public int kthSmallest1(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
//            k--;
			if (k-- == 0) return root.val;
			root = root.right;
		}
		return -1;
	}

	int res, count;

	public int kthSmallest2(TreeNode root, int k) {
		count = k;
		recursion(root);
		return res;
	}

	private void recursion(TreeNode root) {

		if (root == null) return;
		recursion(root.left);
		count--;
		if (count == 0) {
			res = root.val;
		}
		recursion(root.right);
	}


	public int kthSmallest(TreeNode root, int k) {
		int left = countNode(root.left);
		if (left + 1 == k) return root.val;
		else if (left + 1 > k) return kthSmallest(root.left, k);
		else return kthSmallest(root.right, k - left - 1);
	}

	private int countNode(TreeNode root) {
		//这里return 的应该是0 。用root只有一个node的情形考虑就行了。
		if (root == null) return 0;
		return 1 + countNode(root.left) + countNode(root.right);
	}

//
//	public boolean judgeCircle(String moves) {
//		if (moves == null || moves.length() == 0) return true;
//		int up = 0, down = 0, left = 0, right = 0;
//		for (int i = 0; i < moves.length(); i++) {
//			switch (moves.charAt(i)) {
//				case 'U':
//					up++;
//					break;
//				case 'D':
//					down++;
//					break;
//				case 'L':
//					left++;
//					break;
//				case 'R':
//					right++;
//					break;
//			}
//		}
//		return up == down && left == right;
//	}

}
