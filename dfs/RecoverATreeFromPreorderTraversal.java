package dfs;

import java.util.Stack;

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
     * 题意：给一个深度优先遍历的二叉树的字符串，"-"的个数代表当前node的深度。请你恢复这棵树。
     * 解法：一年前做过；一年后用递归写了下，不好构造，被绕晕了。看题解推荐用stack，确实比递归清晰：
     * 用stack保存node，stack的size就代表深度，每当遇到一个新的数字，就一直pop到size=level的时候（所以，先计算dash的个数，也就是深度），这样再插，就能保证插入到指定的level。
     * 可用1-2--3-4--5模拟一下帮助理解。
     */
    public TreeNode recoverFromPreorder(String S) {
        int level, val;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length(); ) {
            for (level = 0; S.charAt(i) == '-'; i++) {
                level++;
            }
            while (!stack.empty() && level < stack.size()) {
                stack.pop(); // 一直pop到当前这个node所在的层级
            }
            for (val = 0; i < S.length() && Character.isDigit(S.charAt(i)); i++) {
                val = val * 10 + (S.charAt(i) - '0');
            }
            TreeNode node = new TreeNode(val);
            if (!stack.empty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.push(node);
        }
        while (stack.size() != 1) stack.pop();
        return stack.pop();
    }

    /**
     * 此题的recursive写法用一个index记录s的index；dfs的顺序就是buildTree的顺序，所以index是一直往后+的。
     * 另外看到也可以用stack来做iterative实现，跟preorder traversal类似。
     */
    public TreeNode recoverFromPreorder_RECURSION(String S) {
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
