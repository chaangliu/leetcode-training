package jianzhioffer;

import linkedlist.ListNode;

///20181225
public class DeleteDuplicatedListNode {
    ///赶飞机，先这样
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = pHead;
        while (pHead != null && pHead.next != null) {
            if (pHead.val != pHead.next.val && pHead.next.next != null && pHead.next.val == pHead.next.next.val) {
                ListNode start = pHead;
                pHead = pHead.next;
                while (pHead.next != null && pHead.val == pHead.next.val) {
                    pHead = pHead.next;
                }
                start.next = pHead.next;//跳过重复的部分
            }
        }
        return fakeHead.next;
    }
}
