package jianzhioffer;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 2018-11-15
 */
public class ConvetBstToDLinkedList {
    //这题我不是很理解，也没说要返回head。它的意思大概是说把tree.left当做node.prev吧，那就中序遍历，把cur.left指向prev，prev.right指向cur

    TreeNode prev = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        helper(pRootOfTree);//我看了一下c++的答案，这里他把prev也当做参数来递归，这一点跟java不一样，java记录上一次遍历的节点只能用全局变量
        TreeNode res = pRootOfTree;
        while (res.left != null) res = res.left;
        return res;
    }

    private void helper(TreeNode cur) {
        if (cur == null) return;
        helper(cur.left);
        cur.left = prev;
        if (prev != null) prev.right = cur;
        prev = cur;
        helper(cur.right);
    }
}
