package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class MergeTwoBinaryTrees {
    /**
     * 题意：合并两棵二叉树。
     * 解法：dfs。我写的时候因为想直接merge到t1上，所以提前判断了下一层。其实可以直接生成新node比较方便。也可以bfs，较长。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    public TreeNode mergeTrees_(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        if (t1.left == null) {
            t1.left = t2.left;
            t2.left = null;
        }
        if (t1.right == null) {
            t1.right = t2.right;
            t2.right = null;
        }
        mergeTrees_(t1.left, t2.left);
        mergeTrees_(t1.right, t2.right);
        return t1;
    }
}
