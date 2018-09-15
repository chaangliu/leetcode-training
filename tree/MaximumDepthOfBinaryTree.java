//package Array;
//
//import java.util.LinkedList;
//
///**
// * Created by DrunkPiano on 2017/3/1.
// */
//
//public class MaximumDepthOfBinaryTree {
//	class TreeNode {
//		TreeNode left;
//		TreeNode right;
//		int val;
//
//		TreeNode(int x) {
//			val = x;
//		}
//	}
//
//	public int maxDepth(TreeNode root) {
//		if (root == null) return 0;
//		//本层的node数
//		int curNum = 1;
//		//下一层的node数
//		int nextNum = 0;
//		int level = 0;
//		LinkedList<TreeNode> linkedList = new LinkedList<>();
//		linkedList.add(root);
//		while (linkedList.size() != 0) {
//			TreeNode node = linkedList.poll();
//			curNum--;
//			if (node.left != null) {
//				linkedList.add(node.left);
//				nextNum++;
//			}
//
//			if (node.right != null) {
//				linkedList.add(node.right);
//				nextNum++;
//			}
//
//			if (curNum == 0) {
//				level++;
//				curNum = nextNum;
//				nextNum = 0;
//			}
//		}
//		return level;
//	}
//
//	    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
//    }
//	int max = 0;
//
//	public int maxDepth(TreeNode root) {
//		traverse(root, 1);
//		return max;
//	}
//
//	private void traverse(TreeNode root, int lvl) {
//		if (root == null) return;
//		max = Math.max(lvl, max);
//		traverse(root.left, lvl + 1);
//		traverse(root.right, lvl + 1);
//	}
//
//}
