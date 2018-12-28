package jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * 20181226
 */
public class ZigZagPrintTree {

    //BFS 信手拈来了https://www.jianshu.com/p/a753d5c733ec
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int curNum = 1, nextNum = 0;
        boolean rightForward = true;
        List<Integer> item = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curNum--;
            if (node == null) continue;
            if (rightForward) {
                item.add(node.val);
            } else {
                item.add(0, node.val);
            }
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
                res.add(new ArrayList<Integer>(item));
                item.clear();
                rightForward = !rightForward;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        new ZigZagPrintTree().Print(root);
    }
}
