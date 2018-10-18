package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * Created by DrunkPiano on 2017/3/8.
 */

public class BinaryTreeInorderTraversal {

    //approach 1 经典解法
//	public ArrayList<Integer> inorderTraversal(TreeNode root) {
//		ArrayList<Integer> res = new ArrayList<>();
//		dfs(res, root);
//		return res;
//	}
//
//	private void dfs(List<Integer> res, TreeNode node) {
//		if (node == null) return;
//		dfs(res, node.left);
//		res.add(node.val);
//		dfs(res, node.right);
//	}

    //approach 2 思路和递归一样，但是是用stack
    //20181018 review
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {//这个root!= null，其实是为了让第一个root不要重复push
            while (root != null) {
                stack.push(root);//把左侧的node全部加入stack
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);//这样保证先加入的最left的值
            root = root.right;
        }
        return res;
    }


//	public ArrayList<Integer> inorderTraversal(TreeNode root) {
//		ArrayList<Integer> res = new ArrayList<>();
//		int pre = -1;
//		LinkedList<TreeNode> stack = new LinkedList<>();
//		while (root != null || !stack.isEmpty()) {
//			if (root != null) {
//				stack.push(root);
//				root = root.left;
//			} else {
//				root = stack.pop();
//				res.add(root.val);
//				root = root.right;
//			}
//		}
//		return res;
//	}

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.add(p.val);  // Add before going to children
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                p = node.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left;           // Reverse the process of preorder
            }
        }
        return result;
    }

}
