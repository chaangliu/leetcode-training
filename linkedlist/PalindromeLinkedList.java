package linkedlist;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 * 20190122
 */
public class PalindromeLinkedList {
    //此题要求O(1) space，所以用List保存node的方法不好。

    //一个方案是，reverse 整个list然后用新list从头开始跟旧list对比，two pass

    //优化的方案是，只reverse半个list；这样的优势是，只要对比一半就可以；1.5 pass
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        //退出循环的时候，如果list长度是偶数，slow是中间的那个node；如果list长度是偶数，slow是中点右边的node

        //slow成了新的head
        slow = reverseList(slow);
        fast = head;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        //slow的长度只有head的一半，slow != nul会先不满足
        return true;
    }

    //这个要多写几遍。。。。。。。
    private ListNode reverseList(ListNode node) {
        if (node == null) return null;
        //reverse list的迭代写法要借助一个dummy node(头插法)，否则最后要把原来的head指向null
        ListNode pre = null;
        ListNode cur = node;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            //next.next = cur;//已犯错误，这一句不能有，不然下一次循环cur.next就指向prev了。。。。
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        new PalindromeLinkedList().isPalindrome(node);
    }


//    public boolean isPalindrome(ListNode head) {
//        ListNode fast = head, slow = head;
//        while (fast != null && fast.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        if (fast != null) { // odd nodes: let right half smaller
//            slow = slow.next;
//        }
//        slow = reverse(slow);
//        fast = head;
//
//        while (slow != null) {
//            if (fast.val != slow.val) {
//                return false;
//            }
//            fast = fast.next;
//            slow = slow.next;
//        }
//        return true;
//    }
//
//    public ListNode reverse(ListNode head) {
//        ListNode prev = null;
//        while (head != null) {
//            ListNode next = head.next;
//            head.next = prev;
//            prev = head;
//            head = next;
//        }
//        return prev;
//    }
}
