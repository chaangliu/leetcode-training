package array;

import java.util.Stack;

import tree.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * Created by DrunkPiano on 2017/3/25.
 */

public class KthSmallestElementInaBST {


    /**
     * 题意：求BST中第k小的node。
     * 解法一：常规解法: BST的遍历。用traversal或者stack
     */
    public int kthSmallest3(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {//是--k，不是k--，一年多了犯了同样的错误？？。。
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }

    //original thread
    public int kthSmallest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
//            k--;
            if (k-- == 0) return root.val;//xx => --k
            root = root.right;
        }
        return -1;
    }

    int res, count;

    public int kthSmallest2(TreeNode root, int k) {
        count = k;
        recursion(root);
        return res;
    }

    private void recursion(TreeNode root) {

        if (root == null) return;
        recursion(root.left);
        count--;
        if (count == 0) {
            res = root.val;
            return;
        }
        recursion(root.right);
    }


    /**
     * 解法二：O(height of tree)，先构造一个TreeNodeWithCount，就是每个node都有一个额外的字段叫做count，记录当前子树有多少个node。
     * 这样一来就可以根据当前node的左右子树的count关系来向左或向右搜索。
     */
    public int kthSmallest(TreeNode root, int k) {
        int left = countNode(root.left);
        if (left + 1 == k) return root.val;
        else if (left + 1 > k) return kthSmallest(root.left, k);
        else return kthSmallest(root.right, k - left - 1);
    }

    private int countNode(TreeNode root) {
        //这里return 的应该是0 。用root只有一个node的情形考虑就行了。
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.left.right = new TreeNode(2);
        int k = new KthSmallestElementInaBST().kthSmallest3(node, 1);
        System.out.println(k);
    }

    public int kthSmallest__(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
        return kthSmallest(rootWithCount, k);
    }

    private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildTreeWithCount(root.left);
        rootWithCount.right = buildTreeWithCount(root.right);
        if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
        if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
        return rootWithCount;
    }

    private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
        if (k <= 0 || k > rootWithCount.count) return -1;
        if (rootWithCount.left != null) {
            if (rootWithCount.left.count >= k) return kthSmallest(rootWithCount.left, k);
            if (rootWithCount.left.count == k - 1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k - 1 - rootWithCount.left.count);
        } else {
            if (k == 1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k - 1);
        }
    }

    class TreeNodeWithCount {
        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;

        TreeNodeWithCount(int x) {
            val = x;
            count = 1;
        }
    }
}
