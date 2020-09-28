package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Follow up:
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Constraints:
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 * Created by DrunkPiano on 2017/4/3.
 * <p>
 * 20190211 review @ pvg airport
 */

public class PopulatingNextRightPointersinEachNode {
    /**
     * 题意：给你一个正常的完全二叉树，让你把每个node和它的右边node连接起来。
     * 解法：
     * 1. 这题和II都可以用BFS做，只要记录一下prev就行了，不用constant space记录一行的元素。
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);
        while (Q.size() > 0) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                Node node = Q.poll();
                if (i < size - 1) {
                    node.next = Q.peek();
                }
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * approach 2. iteration，比较tricky，主要是cur.right.next = cur.next.left; 如下
     */
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode pre = root;
        TreeLinkNode cur;
        while (pre.left != null) {
            cur = pre;
            while (cur != null) {
                //pre.left!=null保证了cur.left不为Null
                cur.left.next = cur.right;//把每个node的左右两个node连接起来
                //把下一层的最左边的node跟右边连接起来了
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            pre = pre.left;
        }
    }

    //based on level order traversal
    public void connect__OLD(TreeLinkNode root) {

        TreeLinkNode cur = root;
        TreeLinkNode pre = null; //下一level的dummyNode
        TreeLinkNode head = null; //下一level的head node

        while (cur != null) {
            //外层while用于移动到下一level，内层while用于同一层level order traverse
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    //无论pre是否
                    pre = cur.left;
                }

                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                //想象一个perfect binary tree，在pre到达第3层的时候，cur是第二层，cur的next已经被之前的pre链接起来了
                cur = cur.next;
            }

            //move to next level
            cur = head;
            pre = null;
            head = null;
        }
    }
}
