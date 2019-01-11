package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import tree.TreeNode;

/**
 * Created by DrunkPiano on 2017/3/1.
 */

public class MaximumDepthOfBinaryTree {
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

    class Pair {
        TreeNode node;
        int depth;

        Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    //20190111 review 只要以任意顺序遍历一遍这个tree就行，用pair保存高度，这样空间复杂度是O(log n)
    //iteration approach 1
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        while (!stack.empty()) {
            Pair pair = stack.pop();
            TreeNode node = pair.node;
            res = Math.max(res, pair.depth);
            if (node.left != null)
                stack.push(new Pair(node.left, pair.depth + 1));
            if (node.right != null)
                stack.push(new Pair(node.right, pair.depth + 1));
        }
        return res;
    }

    //20190111 review 只要以任意顺序遍历一遍这个tree就行，这里是用inorder
    //iteration approach 2
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        map.put(root, 1);
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);//把左侧的node全部加入stack
                if (root.left != null) {
                    map.put(root.left, map.get(root) + 1);
                }
                root = root.left;
            }
            root = stack.pop();
            res = Math.max(res, map.get(root));
            if (root.right != null) {
                map.put(root.right, map.get(root) + 1);
            }
            map.remove(root);//减少空间复杂度为O(log n)
            root = root.right;
        }
        return res;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(3);

        new MaximumDepthOfBinaryTree().maxDepth2(root);
    }

}
