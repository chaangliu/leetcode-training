package dfs;

import tree.TreeNode;

/**
 * We run a preorder depth first search on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output S of this traversal, recover the tree and return its root.
 * <p>
 * Example 1:
 * Input: "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * <p>
 * 20190419
 */
public class RecoverATreeFromPreorderTraversal {
    /**
     * 此题的recursive写法用一个index记录s的index；dfs的顺序就是buildTree的顺序，所以index是一直往后+的。
     * <p>
     * 另外看到也可以用stack来做iterative实现，跟preorder traversal类似。
     */
    public TreeNode recoverFromPreorder(String S) {
        return dfs(S, 0);
    }

    int index = 0;

    private TreeNode dfs(String s, int depth) {
        if (index >= s.length()) return null;
        //计算层数
        int numDash = 0;
        while (index + numDash < s.length() && s.charAt(index + numDash) == '-')
            numDash++;
        //空结点
        if (numDash != depth) return null;

        //过滤数字
        index += numDash;//index来到数字的首位
        int numDigit = 0;
        while (index + numDigit < s.length() && s.charAt(index + numDigit) != '-')
            numDigit++;
        int val = Integer.parseInt(s.substring(index, index + numDigit));
        index = index + numDigit;
        TreeNode node = new TreeNode(val);
        node.left = dfs(s, depth + 1);
        node.right = dfs(s, depth + 1);
        return node;
    }
}
