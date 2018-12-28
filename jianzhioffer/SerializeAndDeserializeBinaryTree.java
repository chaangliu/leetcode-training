package jianzhioffer;

import java.util.LinkedList;
import java.util.Queue;


///请实现两个函数，分别用来序列化和反序列化二叉树。
///20181228
public class SerializeAndDeserializeBinaryTree {

    ///BFS，序列化反序列化都借助queue
    String Serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //无需记录每层数量
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val).append(" ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return res.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] nodes = str.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode node = queue.poll();
            if (!nodes[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                node.left = left;
                queue.offer(left);
            }
            //tricky..
            if (!nodes[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                node.right = right;
                queue.offer(right);
            }
        }
        return root;
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
        String s = new SerializeAndDeserializeBinaryTree().Serialize(one);
        new SerializeAndDeserializeBinaryTree().Deserialize(s);
//		new SerializeandDeserializeBinaryTree().deserialize("1,2,X,X,3,4,X,X,5,X,X,");
    }
}
