package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a rooted binary tree, find the lowest common ancestor of its deepest leaves.
 * <p>
 * Recall that:
 * <p>
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Example 2:
 * <p>
 * Input: root = [1,2,3,4]
 * Output: [4]
 * Example 3:
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 * Constraints:
 * <p>
 * The given tree will have between 1 and 1000 nodes.
 * Each node of the tree will have a distinct value between 1 and 1000.
 * 20190714
 */
public class LowestCommonAncestorofDeepestLeaves {
    /**
     * contest中的代码，用的是BFS找最后一层，然后找最后一层node的LCA。LCA借鉴求两个node LCA的代码，用一个set即可。
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> list = print(root);
        ArrayList<TreeNode> layer = list.get(list.size() - 1);
        if (layer.size() == 1) return layer.get(0);
        HashSet<TreeNode> set = new HashSet<>();
        for (TreeNode node : layer) set.add(node);
        return lowestCommonAncestor(root, set);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, HashSet<TreeNode> set) {
        if (null == root || set.contains(root))
            return root;
        TreeNode left = lowestCommonAncestor(root.left, set);
        TreeNode right = lowestCommonAncestor(root.right, set);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    ArrayList<ArrayList<TreeNode>> print(TreeNode pRoot) {
        ArrayList<ArrayList<TreeNode>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int curNum = 1, nextNum = 0;
        List<TreeNode> item = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curNum--;
            if (node == null) continue;
            item.add(node);
            if (node.left != null) {
                queue.offer(node.left);
                nextNum++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                res.add(new ArrayList<>(item));
                item.clear();
            }
        }
        return res;
    }
}
