package tree;

import linkedlist.ListNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * Created by DrunkPiano on 2017/3/28.
 */

class ConvertSortedListtoBinarySearchTree {
    /**
     * 题意：给你一个升序的链表，请建立一棵高度平衡的BST。
     * 方法1 跟第一题数组一样，递归；要点找链表中点，用快慢指针要注意结束条件。
     */
    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head, null);
    }

    /**
     * l: inclusive, r: exclusive
     */
    private TreeNode dfs(ListNode l, ListNode r) {
        if (l == r) return null; // 递归终止条件
        ListNode mid = findMid(l, r); // 保证l不为空
        TreeNode root = new TreeNode(mid.val);
        root.left = dfs(l, mid);
        root.right = dfs(mid.next, r);
        return root;
    }

    /**
     * 注意结束条件是fast != end && fast.next != end，而不是!=null
     **/
    private ListNode findMid(ListNode start, ListNode end) {
        ListNode slow = start, fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        return slow; // 保证slow不为空
    }


    /**
     * 方法2，在构造树的过程中顺便遍历链表，保证最先构造的是链表的前面的节点，也就是左子树，所以按照inorder遍历就行了
     * 这种利用全局变量的手法，有点像ConstructBinaryTreefromPreorderandInorderTraversal
     */
    ListNode globalHead;

    public TreeNode sortedListToBST_(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(-1);
        root.left = buildTree(left, mid - 1); // 中序遍历
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }
}
