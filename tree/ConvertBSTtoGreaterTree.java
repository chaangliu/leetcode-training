package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 * <p>
 * Example:
 * <p>
 * Input: The root of a Binary Search Tree like this:
 * 5
 * /   \
 * 2     13
 * <p>
 * Output: The root of a Greater Tree like this:
 * 18
 * /   \
 * 20     13
 * <p>
 * 20190402
 */
public class ConvertBSTtoGreaterTree {
    /**
     * approach1. O(3n)
     * 我的思路，inorder遍历，把想要的值保存到Map里，再遍历一次给原来的tree赋值
     */
    public TreeNode convertBST___SLOW(TreeNode root) {
        if (root == null) return null;
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(list.get(list.size() - 1), list.get(list.size() - 1));
        for (int i = list.size() - 2; i >= 0; i--) {
            map.put(list.get(i), list.get(i) + map.get(list.get(i + 1)));
        }
        return dfs(root, map);
    }

    private void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    private TreeNode dfs(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return null;
        root.val = map.get(root.val);
        dfs(root.left, map);
        dfs(root.right, map);
        return root;
    }

    /**
     * approach2. one pass
     * 倒过来的中序遍历即可逆序输出！！！先遍历右边再遍历左边即可
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    /**
     * approach2的非递归描述
     */
    public TreeNode convertBST__ITERATIVE(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || node != null) {
            /* push all nodes up to (and including) this subtree's maximum on
             * the stack. */
            while (node != null) {
                stack.add(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;

            /* all nodes with values between the current and its parent lie in
             * the left subtree. */
            node = node.left;
        }
        return root;
    }
}
