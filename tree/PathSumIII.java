package tree;

/**
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * <p>
 * Example:
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * *  * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * Return 3. The paths that sum to 8 are:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 * <p>
 * 20190209
 */
public class PathSumIII {
    /**
     * 从每一个node出发dfs，遇到sum满足就target + 1，到达leaf为止
     */
    int res = 0;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        helper(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
    }

    //sum == root.val那儿调试了半个小时。。一开始用sum == 0那种方式来判断，发现会多走一层，回溯到叶子结点之后还会重复对右侧节点做一次递归，导致答案错误
    private void helper(TreeNode root, int sum) {
        if (root == null)
            return;
        if (sum == root.val)
            res++;
        if (root.left != null)
            helper(root.left, sum - root.val);
        if (root.right != null)
            helper(root.right, sum - root.val);
    }


//    private void helper(TreeNode root, int sum) {
//        if (sum == 0)
//            res++;
//        if (root == null)
//            return;
//        helper(root.left, sum - root.val);
//        helper(root.right, sum - root.val);
//    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        new PathSumIII().pathSum(root, 3);
    }
}
