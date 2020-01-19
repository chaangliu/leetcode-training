package dfs;

import tree.TreeNode;

/**
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 * Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).
 * Example 1:
 * Input: root = [1,2,3,2,null,2,4], target = 2
 * Output: [1,null,3,null,4]
 * Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
 * After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
 * Example 2:
 * Input: root = [1,3,3,3,2], target = 3
 * Output: [1,3,null,null,2]
 * Example 3:
 * Input: root = [1,2,null,2,null,2], target = 2
 * Output: [1]
 * Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
 * Example 4:
 * Input: root = [1,1,1], target = 1
 * Output: []
 * Example 5:
 * <p>
 * Input: root = [1,2,3], target = 1
 * Output: [1,2,3]
 * Constraints:
 * 1 <= target <= 1000
 * Each tree has at most 3000 nodes.
 * Each node's value is between [1, 1000].
 * 20200119
 */
public class DeleteLeavesWithaGivenValue {
    /**
     * 题意：给你一棵树，把所有leaf.val是target的leaf都trim掉。注意如果trim完了上一层又成了parent，要继续trim。
     * 解法：我一开始觉得这题能用O(n)的one pass解法，但是没想出来，于是就想写个每次递归完了返回是否要继续的dfs函数，外层用while判断。但是这种我没写出来，好几个corner case过不了。。
     * 然后又重新思考能不能one pass，就想到，可以写一个dfs判断当前node是否应该被trim掉，这样一层层往上就能one pass了。
     * 然后就写出来了下面的代码，而且我觉得think out loud很重要，一边说话一边写，思路清晰很多。
     * 然而我看了discussion，发现我的代码还是最长的..感觉已经很简洁了XD
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return dfs(root, target) ? null : root;
    }

    /**
     * dfs返回当前子树是否应该被trim掉。
     */
    private boolean dfs(TreeNode root, int target) {
        if (root == null) return true;
        if (isLeaf(root) && root.val == target) return true;
        if (isLeaf(root)) return false;
        boolean b1 = false, b2 = false;
        if (dfs(root.left, target)) {
            root.left = null;
            b1 = true;
        }
        if (dfs(root.right, target)) {
            root.right = null;
            b2 = true;
        }
        return root.val == target && b1 && b2;// 已犯错误：忘记root.val == target，因为上面只判断了root的是leaf的情况要不要trim，这里要额外判断
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }


    /**
     * lee的写法，利用当前函数dfs就行，当前函数就表示trim后的tree，如果是null就说明已经被trim掉了
     */
    public TreeNode removeLeafNodes___(TreeNode root, int target) {
        if (root.left != null) root.left = removeLeafNodes___(root.left, target);
        if (root.right != null) root.right = removeLeafNodes___(root.right, target);
        return root.left == root.right && root.val == target ? null : root;
    }
}
