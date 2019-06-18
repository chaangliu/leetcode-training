package dfs;

import tree.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * Follow up:
 * <p>
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {

    /**
     * 这题思路我是想到了，找到两个大小不对的数记录下来，然后交换；真正做的时候搞了很久，两个case总是有一个过不了。。
     * 另外一点，用TreeNode保存内存地址就好，不用保存integer再重新遍历一遍。
     */
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    //注意，这里我写的时候受到以前的一道题的影响，参数写成了(TreeNode pre, TreeNode cur)，但这么写跟用全局变量记录上一次访问的node是有区别的：
    //pre写到参数里代表的是当前node前驱节点，比如[1,3,null,null,2]，将会无法记录prev = 3, cur = 2的情况
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (firstElement == null && prevElement.val > root.val) {
            firstElement = prevElement;
        }
        if (firstElement != null && prevElement.val > root.val) {
            //注意2. 这里暗藏玄机；找到之后不能立即return，要以最后一次为准；否则会有case[1,3,null,null,2]不满足。有可能在同一次递归里刚执行完上面那句firstElement赋值就执行了这里。
            secondElement = root;
        }
        prevElement = root;
        traverse(root.right);
    }


    public static void main(String args[]) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        new RecoverBinarySearchTree().recoverTree(root);
        new RecoverBinarySearchTree().recoverTree(root);
    }

    /**
     * 我的WA代码
     */
    //    int big = Integer.MIN_VALUE, small = Integer.MAX_VALUE;
    //    boolean bigWasFound = false, shouldContinue = false;

    //
    //    public void recoverTree(TreeNode root) {
    //        dfs1(null, root);
    //        dfs2(root);
    //        System.out.print("GG");
    //    }
    //
    //    /**
    //     * 找出inorder下第一个比后面的数大的数、第一个比前面的数小的数
    //     **/
    //    private void dfs1(TreeNode pre, TreeNode cur) {
    //        if (cur == null) return;
    //        dfs1(cur, cur.left);
    //        if (!bigWasFound && pre.val > cur.val) {
    //            big = pre.val;
    //            bigWasFound = true;
    //        }
    //        if (bigWasFound && pre.val > cur.val) {
    //            small = cur.val;
    //            cur.val = big;
    //            return;
    //        }
    //        dfs1(cur, cur.right);
    //    }
    //
    //    private void dfs2(TreeNode node) {
    //        if (node == null) return;
    //        dfs2(node.left);
    //        if (node.val == big) {
    //            node.val = small;
    //            return;
    //        }
    //        dfs2(node.right);
    //    }

}
