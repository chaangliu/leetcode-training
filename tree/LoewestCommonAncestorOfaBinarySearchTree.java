package tree;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Created by DrunkPiano on 2017/3/28.
 */

class LoewestCommonAncestorOfaBinarySearchTree {

    public TreeNode lowestCommonAncestor_OLD(TreeNode root, TreeNode p, TreeNode q) {
//        if (root.val >= p.val && root.val <= q.val)
//            return root;
//        else if (root.val > p.val)
//            return lowestCommonAncestor(root.left, p, q);
//        else
//            return lowestCommonAncestor(root.right, p, q);

        return (root.val - p.val) * (root.val - q.val) < 1 ? root : lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
    }

    //20190212 review
    //BST隐含的条件是root比left大比right小
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) < 1)//如果 == 0，说明p == root 或 q == root，如果小于0，说明p q在root两侧
            return root;
        if (root.val - p.val > 0)
            return lowestCommonAncestor(root.left, p, q);
        return lowestCommonAncestor(root.right, p, q);
    }
}
