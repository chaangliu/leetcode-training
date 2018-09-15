package tree;

import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Created by DrunkPiano on 2017/3/1.
 */

public class MinimumDepthOfBinaryTree {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        int curNum = 1;
        int nextNum = 0;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.poll();
            curNum--;
            if (node.left == null && node.right == null)
                return level + 1;

            if (node.left != null) {
                linkedList.add(node.left);
                nextNum++;
            }
            if (node.right != null) {
                linkedList.add(node.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;

            }
        }
        return level;
    }
}
