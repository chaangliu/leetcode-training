package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DrunkPiano on 2017/4/3.
 */

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> cell = new ArrayList<>();
        queue.add(root);
        int curNum = 1;
        int nextNum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            cell.add(node.val);
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
                res.add(0, cell);
                curNum = nextNum;
                nextNum = 0;
                cell = new ArrayList<>();
            }
        }
        return res;
    }


}
