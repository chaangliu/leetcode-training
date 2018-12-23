package jianzhioffer;

import linkedlist.ListNode;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
///20181221
public class DeleteDuplicatedListNode {
    ///赶飞机，先这样
//    public ListNode deleteDuplication(ListNode pHead) {
//        ListNode fakeHead = new ListNode(-1);
//        fakeHead.next = pHead;
//        while (pHead != null && pHead.next != null) {
//            if (pHead.val != pHead.next.val && pHead.next.next != null && pHead.next.val == pHead.next.next.val) {
//                ListNode start = pHead;
//                pHead = pHead.next;
//                while (pHead.next != null && pHead.val == pHead.next.val) {
//                    pHead = pHead.next;
//                }
//                start.next = pHead.next;//跳过重复的部分
//            }
//        }
//        return fakeHead.next;
//    }

    //20181223
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = pHead;
        ListNode walker = fakeHead;
        ListNode runner = pHead;
        while (runner != null && runner.next != null) {
            //如果runner和下一个节点的val相同，那么找到不重复的那个为止
            if (runner.val == runner.next.val) {
                int value = runner.val;
                while (runner != null && runner.val == value) {
                    runner = runner.next;
                }//跳出循环的时候runner已经是重复节点的下一个节点了
                walker.next = runner;
            } else {
                //runner和下一个节点的val不相同
                walker = walker.next;
                runner = runner.next;
            }
        }
        return fakeHead.next;
    }
}
