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
    /**
     * 题意：中序遍历二叉树。要求中序遍历。
     * 思路：用stack，但是这题的while条件比较复杂，还有最后node=node.right没写出来（我以为还要push进去）
     */
    //stack解法
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
            root = root.right; // 这里不需要、也不能加上root.right的判空，否则下一次将进入死循环
        }
        return res;
    }

    //20190205 盲写
    public List<Integer> inorderTraversal__20190205(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        //这个条件我想了一会儿才写出，一开始写的是!stack.empty()，但是这样就必须要先stack.push(root)进栈，会导致root被push两次，所以加了一个||root != null
        while (!stack.empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            root = node.right;//让rChild再执行同样的操作，类似递归执行同一段程序
        }
        return res;
    }

    //20200121没写出来


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

    //approach 1 经典dfs解法
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

}
