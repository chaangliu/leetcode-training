package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 * Example 3:
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 * Example 4:
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 * Example 5:
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 * Constraints:
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 * 20191229
 */
public class AllElementsinTwoBinarySearchTrees {
    /**
     * 题意：把两个bst里的数字升序放到一个数组里。
     * 我contest的时候想像merge 2 sorted linked list那样，阻止某个tree的dfs进程，但是发现不太好写，就直接写了naive方法，如下。
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        helper(root1, res1);
        helper(root2, res2);
        res.addAll(res1);
        res.addAll(res2);
        Collections.sort(res);
        return res;
    }

    private void helper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        helper(node.left, res);
        res.add(node.val);
        helper(node.right, res);
    }

    /**
     * 事实上看到答案没有发现真的能阻止dfs进程的那种做法，但是也能O(N)，就是先分别inorder，然后用merge 2 sorted list那样处理
     */
    public List<Integer> getAllElements_(TreeNode root1, TreeNode root2) {
        Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        inorder(root1, q1);
        inorder(root2, q2);
        List<Integer> ans = new ArrayList<>();
        while (!q1.isEmpty() || !q2.isEmpty())
            if (q2.isEmpty())
                ans.add(q1.poll());
            else if (q1.isEmpty())
                ans.add(q2.poll());
            else
                ans.add(q1.peek() < q2.peek() ? q1.poll() : q2.poll());
        return ans;
    }
    private void inorder(TreeNode n, Queue<Integer> q) {
        if (n == null) return;
        inorder(n.left, q);
        q.offer(n.val);
        inorder(n.right, q);
    }
}
