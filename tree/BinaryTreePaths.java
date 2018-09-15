package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 * <p>
 * Created by DrunkPiano on 2017/4/25.
 */

public class BinaryTreePaths {
    //    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> res = new ArrayList<>();
//        if (root == null) return res;
//        String item = "";
//        dfs(res, root, item);
//        return res;
//    }
//
//    private void dfs(List<String> res, TreeNode root, String item) {
//        if (root == null) return;
//        if (isLeaf(root)) {
//            item += root.val;
//            res.add(item);
//            return;
//        }
//        dfs(res, root.left, item + root.val  + "->");
//        dfs(res, root.right, item + root.val + "->" );
//    }
//
//    private boolean isLeaf(TreeNode node) {
//        return (node != null && node.left == null && node.right == null);
//    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        StringBuilder sb = new StringBuilder();
        dfs(res, root, sb);
        return res;
    }

    private void dfs(List<String> res, TreeNode root, StringBuilder sb) {
//        if (root == null) return;
        if (isLeaf(root)) {
            sb.append(root.val);
            res.add(new String(sb));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        if (root.left != null) {
            sb.append(root.val).append("->");
            dfs(res, root.left, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (root.right != null) {
            sb.append(root.val).append("->");
            dfs(res, root.right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return (node != null && node.left == null && node.right == null);
    }

    public static void main(String args[]) {
        BinaryTreePaths instance = new BinaryTreePaths();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        instance.binaryTreePaths(root);
    }
}
