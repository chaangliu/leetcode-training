package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Example:
 * Input:
 * 1
 * / \
 * 3   2
 * / \   \
 * 5   3   9
 * Output: [1, 3, 9]
 * <p>
 * Created by DrunkPiano on 2017/4/5.
 */

public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curNum = 1;
        int nextNum = 0;
        int rowMax = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            curNum--;
            if (temp.val > rowMax) rowMax = temp.val;

            if (temp.left != null) {
                queue.offer(temp.left);
                nextNum++;
            }

            if (temp.right != null) {
                queue.offer(temp.right);
                nextNum++;
            }

            if (curNum == 0) {
                result.add(rowMax);
                curNum = nextNum;
                nextNum = 0;
                rowMax = Integer.MIN_VALUE;
            }
        }
        return result ;
    }
}
