package tree;

import linkedlist.ListNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * Created by DrunkPiano on 2017/3/28.
 */

class ConvertSortedListtoBinarySearchTree {
//    public TreeNode sortedListToBST(ListNode head) {
//        if (head == null) return null;
//        List<Integer> list = new ArrayList<>();
//        while (head != null) {
//            list.add(head.val);
//            head = head.next;
//        }
//        int[] nums = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            nums[i] = list.get(i);
//        }
//
//        return recursion(nums, 0, nums.length - 1);
//    }
//
//    private TreeNode recursion(int nums[], int left, int right) {
//        if (left > right) return null;
//        int mid = (left + right) / 2;
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = recursion(nums, left, mid - 1);
//        root.right = recursion(nums, mid + 1, right);
//
//        return root;
//    }

//    that's O(n);


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return recursion(head, null);

    }

    private TreeNode recursion(ListNode head, ListNode tail) {
        ListNode runner = head;
        ListNode walker = head;
        if (head == tail) return null;

        while (runner.next != tail && runner.next.next != tail) {
            walker = walker.next;
            runner = runner.next.next;
        }
        TreeNode root = new TreeNode(walker.val);
        root.left = recursion(head, walker);
        root.right = recursion(walker.next, tail);

        return root;
    }

}
