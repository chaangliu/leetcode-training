package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
 * Example 1:
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Flipped Trees Diagram
 * Note:
 * Each tree will have at most 100 nodes.
 * Each value in each tree will be a unique integer in the range [0, 99].
 * 20191214
 */
public class FlipEquivalentBinaryTrees {
    /**
     * 题意：给你两棵树，每个node都可以翻转，问你两棵树是否可以通过一些次翻转变成相同的两棵树。
     * 题目的例子可以看出，翻转是仅对当前node翻转。不过其实也无所谓，毕竟可以翻转无数次。
     * 稍微分析一下就写了下面的dfs代码，还比较轻松。用5-7这样的tree检查了一下发现是对的就提交了。
     * 方法1. DFS
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return root1 == null;
        if (root1.val != root2.val) return false;
        return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left) ||
                flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    }

    /**
     * rock的dfs
     */
    public boolean flipEquiv__dfs(TreeNode r1, TreeNode r2) {
        if (r1 == null || r2 == null) return r1 == r2;
        return r1.val == r2.val &&
                (flipEquiv(r1.left, r2.left) && flipEquiv(r1.right, r2.right) ||
                        flipEquiv(r1.left, r2.right) && flipEquiv(r1.right, r2.left));
    }


    /**
     * 方法2. BFS
     * 摘抄rock的BFS写法，思路是对root1的当前左右子树进行矫正，尝试矫正成root2的样子。不过这种速度没有dfs快。
     * 比较神奇的是他用的是stack，也许应该成为迭代版的dfs？这个操作不多见。
     */
    public boolean flipEquiv__(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stk1 = new Stack<>(), stk2 = new Stack<>();
        stk1.push(root1);
        stk2.push(root2);
        while (!stk1.isEmpty() && !stk2.isEmpty()) {
            TreeNode n1 = stk1.pop(), n2 = stk2.pop();
            if (n1 == null && n2 == null) {
                continue;
            } else if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }
            if (n1.left == n2.left || n1.left != null && n2.left != null && n1.left.val == n2.left.val) {
                stk1.addAll(Arrays.asList(n1.left, n1.right));
            } else {
                stk1.addAll(Arrays.asList(n1.right, n1.left));
            }
            stk2.addAll(Arrays.asList(n2.left, n2.right));
        }
        return stk1.isEmpty() && stk2.isEmpty();
    }
}
