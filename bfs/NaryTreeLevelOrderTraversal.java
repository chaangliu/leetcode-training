package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * Example 1:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * Example 2:
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * Constraints:
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 * 20191126
 */
public class NaryTreeLevelOrderTraversal {
    /**
     * 题意：N叉树的层序遍历。
     * 解法很常规，
     * 1，BFS
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                item.add(node.val);
                for (Node n : node.children) {
                    q.offer(n);
                }
            }
            res.add(item);
        }
        return res;
    }

    /**
     * 和二叉树类似，也可用DFS
     * 解法2，DFS，来自讨论区
     */
    public List<List<Integer>> levelOrder_(Node root) {
        return levelOrder(root, 0, new ArrayList<>());
    }

    private List<List<Integer>> levelOrder(Node node, int level, List<List<Integer>> order) {
        if (node == null) {
            return order;
        }
        List<Integer> list = order.size() > level ? order.get(level) : new ArrayList<>();
        list.add(node.val);
        if (order.size() <= level) {
            order.add(list);
        }
        for (Node n : node.children) {
            levelOrder(n, level + 1, order);
        }
        return order;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
