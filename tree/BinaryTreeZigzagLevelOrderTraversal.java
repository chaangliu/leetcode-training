package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DrunkPiano on 2017/4/2.
 */

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int curNum = 1;
        int nextNum = 0;
        List<Integer> cell = new ArrayList<>();
        boolean flag = true;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            curNum--;
            if (flag) {
                cell.add(temp.val);
            } else {
                cell.add(0, temp.val);
            }

            if (temp.left != null) {
                queue.add(temp.left);
                nextNum++;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                nextNum++;
            }
            if (curNum == 0) {
                res.add(cell);
                curNum = nextNum;
                nextNum = 0;
                flag = !flag;
                cell = new ArrayList<>();
            }
        }
        return res;
    }
}
