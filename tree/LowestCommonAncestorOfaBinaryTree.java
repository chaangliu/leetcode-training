package tree;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Note:
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 * Created by DrunkPiano on 2017/3/28.
 * 20190211 reviewed at pvg airport
  */

class LowestCommonAncestorOfaBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //1. 当前子树中没找到p,q 2. 找到了p,q中的一个
        if (null == root || root == p || root == q)
            return root;

        //在左右子树分别寻找p,q的LCA，我疑惑的是这个left，right分别是什么呢？从上面的return结果可以看出来就是null或者p或者q啊。
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //在左子树，右子树都找到了p，q，说明这个root就是我们要找的node，p, q分别在root两侧
        if (left != null && right != null) {
            return root;
        }
        //只有一个为空，或者两个都为空的情况
        return left == null ? right : left;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        new LowestCommonAncestorOfaBinaryTree().lowestCommonAncestor(root, root.left.right, root.right.left);
    }
}
