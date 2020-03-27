package tree;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * The cloned tree is a copy of the original tree.
 * Return a reference to the same node in the cloned tree.
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 * Example 1:
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
 * Example 2:
 * Input: tree = [7], target =  7
 * Output: 7
 * Example 3:
 * Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * Output: 4
 * Example 4:
 * Input: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
 * Output: 5
 * Example 5:
 * Input: tree = [1,2,null,3], target = 2
 * Output: 2
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * The values of the nodes of the tree are unique.
 * target node is a node from the original tree and is not null.
 * 20200327
 */
public class FindaCorrespondingNodeofaBinaryTreeinaCloneofThatTree {
    /**
     * 题意: 给你一个树和一颗它的克隆，再给你一个原树中的node，让你找出clone树中的对应的node。
     * 这题我拿到觉得直接dfs在clone树中找一下就完事了，事实也确实能AC，但是我的那种做法遇到重复node就不行了。推荐下面做法，两棵树一起traverse。
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) return cloned;
        TreeNode res = getTargetCopy(original.left, cloned.left, target);
        if (res != null) return res;
        return getTargetCopy(original.right, cloned.right, target);
    }

    /**
     * 我的代码
     */
    public final TreeNode getTargetCopy_(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null) return null;
        if (cloned.val == target.val) {
            return cloned;
        }
        TreeNode l = getTargetCopy(original, cloned.left, target);
        TreeNode r = getTargetCopy(original, cloned.right, target);
        if (l != null) return l;
        if (r != null) return r;
        return null;
    }
}
