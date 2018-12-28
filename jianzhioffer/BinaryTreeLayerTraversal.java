package jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 * 20181226
 */
public class BinaryTreeLayerTraversal {

    //BFS
//    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
//        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//        if (pRoot == null) return res;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(pRoot);
//        int curNum = 1, nextNum = 0;
//        List<Integer> item = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            curNum--;
//            if (node == null) continue;
//            item.add(node.val);
//            if (node.left != null) {
//                queue.offer(node.left);
//                nextNum++;
//            }
//            if (node.right != null) {
//                queue.offer(node.right);
//                nextNum++;
//            }
//            if (curNum == 0) {
//                curNum = nextNum;
//                nextNum = 0;
//                res.add(new ArrayList<Integer>(item));
//                item.clear();
//            }
//        }
//        return res;
//    }

    //DFS
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        helper(res, 0, pRoot);
        return res;
    }

    private void helper(ArrayList<ArrayList<Integer>> res, int depth, TreeNode node) {
        if (node == null) return;
        if (depth > res.size() - 1) {
            ArrayList<Integer> item = new ArrayList<>();
            item.add(node.val);
            res.add(item);
        } else {
            res.get(depth).add(node.val);
        }
        helper(res, depth + 1, node.left);
        helper(res, depth + 1, node.right);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        new BinaryTreeLayerTraversal().Print(root);
    }
}
