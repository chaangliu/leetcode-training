package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import tree.TreeNode;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * <p>
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * <p>
 * 20190615
 */
public class AllNodesDistanceKInBinaryTree {

    /**
     * 这题是求所有距离targetNode是K的node集合，题目很好。
     * <p>
     * 方法1. 用Map记录父母
     * 又是一道往parent方向走的题目，用Map记录parent然后bfs
     * <p>
     * 也可以这么建图: Map<TreeNode, List<TreeNode>> map = new HashMap();
     */
    Map<TreeNode, TreeNode> map = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    List<Integer> res = new ArrayList<>();
    Set<TreeNode> visited = new HashSet<>();//因为是双向的，相当于无向图，所以bfs要避免走反路

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (K == 0) {
            res.add(target.val);
            return res;
        }
        dfs(root.left, root);//也可以合成一句：dfs(root, null);
        dfs(root.right, root);
        q.offer(target);
        visited.add(target);//容易漏掉：自己也要加入visited，否则结果必然多一个自己
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                if (K == 0) {
                    res.add(tmp.val);
                } else {
                    TreeNode left = tmp.left;
                    TreeNode right = tmp.right;
                    TreeNode p = map.get(tmp);
                    if (left != null && !visited.contains(left)) {
                        q.offer(left);
                        visited.add(left);//注意不要省事写在if外面，否则可能把null加进queue
                    }
                    if (right != null && !visited.contains(right)) {
                        q.offer(right);
                        visited.add(right);
                    }
                    if (p != null && !visited.contains(p)) {
                        q.offer(p);
                        visited.add(p);
                    }
                }
            }
            if (K-- == 0) return res;
        }
        return res;
    }

    private void dfs(TreeNode node, TreeNode parent) {//top down或者bottom up都可以
        if (node == null) return;
        map.put(node, parent);
        dfs(node.left, node);
        dfs(node.right, node);
    }


    /**
     * ------------------------------------------------------------------------------------------------------
     * 方法2. 新定义一个结构，一个树含有指向parent的node（多年前在1point3上看过这样的面经）
     */
    private GNode targetGNode;

    private class GNode {
        TreeNode node;
        GNode parent, left, right;

        GNode(TreeNode node) {
            this.node = node;
        }
    }

    public List<Integer> distanceK___(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null || K < 0) return res;
        cloneGraph(root, null, target);
        if (targetGNode == null) return res;
        Set<GNode> visited = new HashSet<GNode>();
        Queue<GNode> q = new LinkedList<GNode>();
        q.add(targetGNode);
        visited.add(targetGNode);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size; i++) res.add(q.poll().node.val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                GNode gNode = q.poll();
                if (gNode.left != null && !visited.contains(gNode.left)) {
                    visited.add(gNode.left);
                    q.add(gNode.left);
                }
                if (gNode.right != null && !visited.contains(gNode.right)) {
                    visited.add(gNode.right);
                    q.add(gNode.right);
                }
                if (gNode.parent != null && !visited.contains(gNode.parent)) {
                    visited.add(gNode.parent);
                    q.add(gNode.parent);
                }
            }
            K--;
        }
        return res;
    }

    private GNode cloneGraph(TreeNode node, GNode parent, TreeNode target) {
        if (node == null) return null;
        GNode gNode = new GNode(node);
        if (node == target) targetGNode = gNode;
        gNode.parent = parent;
        gNode.left = cloneGraph(node.left, gNode, target);
        gNode.right = cloneGraph(node.right, gNode, target);
        return gNode;
    }
}
