package jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p>
 * leetcode原题。
 * https://www.jianshu.com/p/a753d5c733ec
 */
public class BinaryTreeLayerOrderTraversal {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curNum = 1, nextNum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);//no need to check null pointer
            curNum--;
            if (node.left != null) {
                queue.offer(node.left);
                nextNum++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        new BinaryTreeLayerOrderTraversal().PrintFromTopToBottom(node);
        int a;
    }
}
