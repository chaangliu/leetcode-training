package dp;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Created by DrunkPiano on 2017/4/2.
 */

public class PathSumII {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;
        cell.add(root.val);
        helper(root, sum - root.val);

        return res;
    }

    List<Integer> cell = new ArrayList<>();

    private void helper(TreeNode root, int sum) {
        if (root == null) return;

        if (root.left == null && root.right == null && 0 == sum) {
            res.add(cell);
            cell = new ArrayList<>();
            //相当于else
            return;
        }
        if (root.left != null && sum > 0) {
            cell.add(root.left.val);
            helper(root.left, sum - root.left.val);
            cell.remove(cell.size() - 1);
        }
        if (root.right != null && sum > 0) {
            cell.add(root.right.val);
            helper(root.right, sum - root.right.val);
            cell.remove(cell.size() - 1);
        }
    }
}
