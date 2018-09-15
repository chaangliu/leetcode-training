package tree;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Created by DrunkPiano on 2017/3/28.
 */

class LoewestCommonAncestorOfaBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root.val >= p.val && root.val <= q.val)
//            return root;
//        else if (root.val > p.val)
//            return lowestCommonAncestor(root.left, p, q);
//        else
//            return lowestCommonAncestor(root.right, p, q);

        return (root.val - p.val) * (root.val - q.val) < 1 ? root : lowestCommonAncestor(p.val< root.val ? root.left : root.right , p , q);
    }
}
