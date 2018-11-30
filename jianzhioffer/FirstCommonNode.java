package jianzhioffer;

import java.util.HashSet;
import java.util.Set;

import linkedlist.ListNode;

public class FirstCommonNode {
    //方法1，Map，空间O(n)，不太好
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode node1 = pHead1, node2 = pHead2;
        while (node1 != null) {
            set.add(node1);
            node1 = node1.next;
        }
        while (node2 != null) {
            if (set.contains(node2)) return node2;
            node2 = node2.next;
        }
        return null;
    }

    //好的解法：
    //    链接：https://www.nowcoder.com/questionTerminal/6ab1d9a29e88450685099d45c9e31e46
    /*
    找出2个链表的长度，然后让长的先走两个链表的长度差，然后再一起走
    （因为2个链表用公共的尾部）
    */
//    class Solution {
//        public:
//        ListNode* FindFirstCommonNode( ListNode *pHead1, ListNode *pHead2) {
//            int len1 = findListLenth(pHead1);
//            int len2 = findListLenth(pHead2);
//            if(len1 > len2){
//                pHead1 = walkStep(pHead1,len1 - len2);
//            }else{
//                pHead2 = walkStep(pHead2,len2 - len1);
//            }
//            while(pHead1 != NULL){
//                if(pHead1 == pHead2) return pHead1;
//                pHead1 = pHead1->next;
//                pHead2 = pHead2->next;
//            }
//            return NULL;
//        }
//
//        int findListLenth(ListNode *pHead1){
//            if(pHead1 == NULL) return 0;
//            int sum = 1;
//            while(pHead1 = pHead1->next) sum++;
//            return sum;
//        }
//
//        ListNode* walkStep(ListNode *pHead1, int step){
//            while(step--){
//                pHead1 = pHead1->next;
//            }
//            return pHead1;
//        }
//    };
}
