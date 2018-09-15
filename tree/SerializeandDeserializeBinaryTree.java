package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by DrunkPiano on 12/05/2017.
 */

public class SerializeandDeserializeBinaryTree {
	private static final String spliter = ",";
	private static final String NN = "X";

	//DFS
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		preorder(root, sb);
		return sb.toString();
	}

	private void preorder(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(NN).append(spliter);
			return;
		}
		sb.append(node.val).append(spliter);
		preorder(node.left, sb);
		preorder(node.right, sb);
	}

	//	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<String> queue = new LinkedList<>();
		queue.addAll(Arrays.asList(data.split(spliter)));
		//如何返回root：递归返回的最终就是root
		return buildTree(queue);
	}

	private TreeNode buildTree(Queue<String> queue) {
		String val = queue.poll();
		if (val.equals(NN)) {
			return null;
		}
		TreeNode node = new TreeNode(Integer.valueOf(val));
		node.left = buildTree(queue);
		node.right = buildTree(queue);

		return node;
	}

	public static void main(String args[]) {
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(5);
		one.left = two;
		one.right = three;
		three.left = four;
		three.right = five;
//		System.out.println(new SerializeandDeserializeBinaryTree().serialize(one));
		String s = new SerializeandDeserializeBinaryTree().serialize(one);
		new SerializeandDeserializeBinaryTree().deserialize(s);
//		new SerializeandDeserializeBinaryTree().deserialize("1,2,X,X,3,4,X,X,5,X,X,");
	}
}
	//BFS
//	public String serialize(TreeNode root) {
//		if (root == null) return "";
//		Queue<TreeNode> q = new LinkedList<>();
//		StringBuilder res = new StringBuilder();
//		q.add(root);
//		while (!q.isEmpty()) {
//			TreeNode node = q.poll();
//			if (node == null) {
//				res.append("n ");
//				continue;
//			}
//			res.append(node.val + " ");
//			q.add(node.left);
//			q.add(node.right);
//		}
//		return res.toString();
//	}
//
//	public TreeNode deserialize(String data) {
//		if (data == "") return null;
//		Queue<TreeNode> q = new LinkedList<>();
//		String[] values = data.split(" ");
//		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
//		q.add(root);
//		for (int i = 1; i < values.length; i++) {
//			TreeNode parent = q.poll();
//			if (!values[i].equals("n")) {
//				TreeNode left = new TreeNode(Integer.parseInt(values[i]));
//				parent.left = left;
//				q.add(left);
//			}
//			if (!values[++i].equals("n")) {
//				TreeNode right = new TreeNode(Integer.parseInt(values[i]));
//				parent.right = right;
//				q.add(right);
//			}
//		}
//		return root;
//	}


	//449题，bST

	// Encodes a tree to a single string.
//	public String serialize(TreeNode root) {
//		//base case
//		if(root==null)
//			return "$,";
//		//ensure
//		return root.val+","+serialize(root.left)+serialize(root.right);
//	}
//	// Decodes your encoded data to tree.
//	public TreeNode deserialize(String data) {
//		//require
//		Queue<String> q=new LinkedList<String>();
//		//convert to string[] by " "
//		String[] strs=data.split(",");
//		for(String str:strs){
//			q.add(str);
//		}
//		return dfs(q);
//	}
//	private TreeNode dfs(Queue<String> q){
//		String str=q.remove();
//		//base case
//		if(str.equals("$"))
//			return null;
//
//		TreeNode root=new TreeNode(Integer.valueOf(str));
//		root.left=dfs(q);
//		root.right=dfs(q);
//		//ensure
//		return root;
//	}
//}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));