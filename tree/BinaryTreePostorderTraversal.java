package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * 20190206
 */
public class BinaryTreePostorderTraversal {

    /**
     * 这题是一道hard题，iteration解法相比preOrder和Inorder，我觉得它难在把node.val加入到结果集中的时机；
     * preOrder是一边向左遍历一边就加入了，inorder是遍历到左边端点的时候从stack中拿出一个node.val加入，
     * <p>
     * leetcode上有三种方法的合集，postorder就是preorder反过来操作，并且利用list.addFirst方法
     */

    //先看一下preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.add(node.val);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return res;
    }

    //改造成postorder
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();//addFirst是LinkedList的方法
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.addFirst(node.val);// reverse the process of preorder，这样最先加入的就被放到了最后面
                node = node.right;// reverse the process of preorder
            } else {
                node = stack.pop().left; //reverse the process of preorder
            }
        }
        return res;
    }

}
