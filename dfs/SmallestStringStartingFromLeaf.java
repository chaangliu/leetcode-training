package dfs;

import java.util.Comparator;
import java.util.PriorityQueue;

import tree.TreeNode;

/**
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 * <p>
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 * <p>
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 * <p>
 * 20190329
 */
public class SmallestStringStartingFromLeaf {

    /**
     * 1. 我的解法，遍历所有的字符串，用最小堆存储；自己用Comparator实现了字典序的排序规则(所以实际上不用存储在queue中的。。直接用compare维护最小的就行)
     */
    public String smallestFromLeaf(TreeNode root) {
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //bc, abc
                for (int i = 0; i < o1.length() && i < o2.length(); i++) {
                    if (o1.charAt(i) != o2.charAt(i)) return o1.charAt(i) - o2.charAt(i);
                }
                return o1.length() - o2.length();
            }
        });
        dfs(pq, root, "");
        return pq.peek();
    }

    private void dfs(PriorityQueue<String> pq, TreeNode node, String tmp) {
        if (node == null) return;
        //注意说的是从叶子节点开始，所以必须是左右子树都为空而不是其中一个为空
        if (node.left == null && node.right == null) {
            tmp += (char) (node.val + 'a');
            StringBuilder sb = new StringBuilder();
            for (int i = tmp.length() - 1; i >= 0; i--) {
                sb.append(tmp.charAt(i));
            }
            pq.offer(sb.toString());
        }
        dfs(pq, node.left, tmp + (char) (node.val + 'a'));
        dfs(pq, node.right, tmp + (char) (node.val + 'a'));
    }

    /**
     * 2. leetcode解法，用了个String.compareTo API，直接就能实现字典序比较大小
     */
    String ans = "~";

    public String smallestFromLeaf__SOLUTION(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append((char) ('a' + node.val));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            if (S.compareTo(ans) < 0)
                ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
