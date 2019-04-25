package linkedlist;

/**
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
 * <p>
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.
 * <p>
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.
 * <p>
 * Return a List of ListNode's representing the linked list parts that are formed.
 * <p>
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 * Example 1:
 * Input:
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 * Example 2:
 * Input:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 * Note:
 * <p>
 * The length of root will be in the range [0, 1000].
 * Each value of a node in the input will be an integer in the range [0, 999].
 * k will be an integer in the range [1, 50].
 * <p>
 * 20190425
 */
public class SplitLinkedListInParts {
    /**
     * 这题我读完之后觉得，没有任何技巧啊，就链表指针操作呗，然后做了一个小时才AC；
     * 我印象里链表的题目就是这样，很多内存地址指向的细节能让人崩溃。不过这次还好能AC，以前估计心态早崩了。
     * 对堆内存的理解有待加强
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) return res;
        ListNode tmp = root;
        int len = 0;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        int subLen = len / k; //14 / 15 = 0
        int extra = len % k; // 14 % 15 = 14

        while (k > 0) {
            tmp = root != null ? new ListNode(root.val) : null;
            ListNode fakeHead = new ListNode(-1);
            fakeHead.next = tmp;
            int i = 0;
            int l = subLen + (extra > 0 ? 1 : 0);
            while (tmp != null && i < l) {
                tmp.next = i != l - 1 ? new ListNode(root.next.val) : null;//不能写成tmp.next = root.next; tmp和root要始终保持独立堆地址
                tmp = tmp.next;
                root = root.next;
                i++;
            }
            extra--;
            if (tmp != null) tmp.next = null;
            res[res.length - k] = fakeHead.next;
            k--;
        }
        return res;
    }
}
