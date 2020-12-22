package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by DrunkPiano on 2017/4/2.
 */

public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 题意：之字形打印二叉树。
     * 解法：用一个flag控制bfs的插入链表位置就行了。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean toRight = true;
        while (!q.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            for (int size = q.size(); size > 0; size--) {
                TreeNode node = q.poll();
                if (toRight) item.add(node.val);
                else item.add(0, node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            toRight = !toRight;
            res.add(item);
        }
        return res;
    }
}
