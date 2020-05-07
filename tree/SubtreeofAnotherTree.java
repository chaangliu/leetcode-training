package tree;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的【所有】子孙。s 也可以看做它自身的一棵子树。
 * Created by DrunkPiano on 2017/5/7.
 */

public class SubtreeofAnotherTree {
    /**
     * 题意：给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的【所有】子孙。s 也可以看做它自身的一棵子树。
     * 解法：DFS，注意定义：s 的一个子树包括 s 的一个节点和这个节点的【所有】子孙
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return s != null && (checkSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    private boolean checkSame(TreeNode s, TreeNode t) {
        if (t == null && s == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return checkSame(s.left, t.left) && checkSame(s.right, t.right);
    }
}
