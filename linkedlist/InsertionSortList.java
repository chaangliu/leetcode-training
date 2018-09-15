package linkedlist;

/**
 * /**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * https://en.wikipedia.org/wiki/Insertion_sort
 * Created by DrunkPiano on 2017/3/22.
 */

class InsertionSortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

//    Pseudocode:
//    for i = 1 to length(A)
//    j ← i
//    while j > 0 and A[j-1] > A[j]
//    swap A[j] and A[j-1]
//    j ← j - 1
//    end while
//    end for
//    问题是怎么访问j-1?
//    public ListNode insertionSortList(ListNode head) {
//        ListNode fakeHead = new ListNode(-1);
//        fakeHead.next = head;
//        ListNode left , right ;
//        left = head;
//        right = head.next ;
//        while(right.val > left.val){
//            left = left.next;
//        }
//
//    }


//    public static ListNode insertionSortList(ListNode head) {
//        if( head == null ){
//            return head;
//        }
//
//        ListNode helper = new ListNode(0); //new starter of the sorted list
//        ListNode cur = head; //the node will be inserted
//        ListNode pre = helper; //insert node between pre and pre.next
//        ListNode next = null; //the next node will be inserted
//        //not the end of input list
//        while( cur != null ){
//            next = cur.next;
//            //find the right place to insert
//            while( pre.next != null && pre.next.val < cur.val ){
//                pre = pre.next;
//            }
//            //insert between pre and pre.next
//            cur.next = pre.next;
//            pre.next = cur;
//            pre = helper;
//            cur = next;
//        }
//
//        return helper.next;
//    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        ListNode pre = fakeHead;
        ListNode cur = head;


        while (cur != null) {
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }

            //在把cur.next重定位（插入到pre和pre.next之间）之前，必须要把cur.next保存下来，这样才能cur = next开始下一次循环
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
            pre = fakeHead;
        }
        return fakeHead.next;
    }


    /**
     * insertion sort an array
     *
     * @param arr
     */
    public static void InsertSort(int[] arr) {
        int i, j;
        int n = arr.length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = 1; i < n; i++)
        {
            j = i;
            target = arr[i];

            while (j > 0 && target < arr[j - 1])
            {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = target;
        }
    }


    //    2 3 4 3 4 5
    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);

        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;


        n1 = insertionSortList(n1);

        printList(n1);

    }

    public static void printList(ListNode x) {
        if (x != null) {
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }
//        int[] array = {2, 3, 4, 3, 4, 5};
//         InsertSort(array);
//        System.out.println(arr);

    }
}
