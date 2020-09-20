package jianzhioffer;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNodeInBST_LC54 {
    /**
     * 对应LC的54题，不过那题是找第K『大』的。
     * 二分法，向左向右找；类题「二叉树的下一个结点」那题
     * O(log N)
     */
    public int kthLargest(TreeNode root, int k) {
        if (root == null) return 0;
        int rightCnt = dfs(root.right);
        if (rightCnt == k - 1) return root.val;
        if (rightCnt > k - 1) {
            return kthLargest(root.right, k);
        }
        return kthLargest(root.left, k - rightCnt - 1);
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        return 1 + dfs(node.left) + dfs(node.right);
    }

    /**
     * 中序遍历法，挺难写的；k写成全局变量
     * O(n)
     */
    TreeNode res;
    int count;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) return null;
        count = k;
        recursion(pRoot);
        return res;
    }

    private void recursion(TreeNode root) {
        if (root == null) return;
        recursion(root.left); // 这儿是找第K小的所以先遍历左边；如果找第K大的，先遍历右边
        if (--count == 0) {
            res = root;
            return;
        }
        recursion(root.right);
    }
}
