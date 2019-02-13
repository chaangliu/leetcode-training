package tree;

import tree.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Created by DrunkPiano on 2017/4/2.
 */

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == root.val) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 20190208review ，bottom-up:
     */
    public boolean hasPathSum__bottomUp(TreeNode root, int sum) {
        if (root != null && root.left == null && root.right == null && sum == root.val) return true;
        if (root == null) return false;
        //从左到右DFS，LEFT-BOTTOM的不满足就回溯到倒数第二层往右；有点像max tree depth里的bottom-up解法的思路；可以想象这里分裂成N个||的分支
        return hasPathSum__bottomUp(root.left, sum - root.val) || hasPathSum__bottomUp(root.right, sum - root.val);
    }

    /**
     * 20190208review top down，总感觉恢复现场有点问题，但竟然能AC:
     */
    int remain;

    public boolean hasPathSum__topDown(TreeNode root, int sum) {
        remain = sum;
        return helper(root);

    }

    private boolean helper(TreeNode root) {
        if (root != null && root.left == null && root.right == null && remain == root.val) return true;
        if (root == null) return false;
        remain -= root.val;
        if (helper(root.left) || helper(root.right)) return true;
        remain += root.val;
        return false;
    }

    //当然，非递归的深度遍历也可以

}
