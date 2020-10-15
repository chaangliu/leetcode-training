package tree;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * Example:
 * You may serialize the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * Created by DrunkPiano on 12/05/2017.
 */

public class SerializeandDeserializeBinaryTree {
    /**
     * 题意：实现树的序列化函数的反序列化函数。
     * 解法：可以用BFS或者DFS，关键在于标记逗号和空节点。DFS比较简洁，preorder就行。
     */
    public class Codec {
        final String SEPARATOR = ",", NULL = "N";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return NULL;
            String res = root.val + SEPARATOR; // preorder
            res += serialize(root.left) + "," + serialize(root.right);
            return res;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] A = data.split(",");
            // System.out.println(data);
            return buildTree(A);
        }

        int index = 0;

        private TreeNode buildTree(String[] A) {
            if (index >= A.length) return null;
            if (A[index].equals(NULL)) {
                index++; // 已犯错误：这儿没有index++，会导致遇到第一个NULL结点的时候，index不会再继续增加。也可以像下面一样，用Queue.poll()避免index。
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(A[index++]));
            node.left = buildTree(A);
            node.right = buildTree(A);
            return node;
        }
    }

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

    //BFS
    public String serialize_(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize_(String data) {
        if (TextUtils.isEmpty(data)) return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}