package linkedlist;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 20200204 --review
 */

class RandomListNode {
    int val;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.val = x;
    }
}

public class CopyListWithRandomPointer {
    /**
     * 题意：一个链表，每个node有个random pointer指向任意node。让你deep copy这个链表。
     * 这题是剑指offer原题，难点是，你如果先直接复制一套，没办法立刻定位random的位置。
     * 所以解法是，第一轮在每个node后面赋值一份，第二轮遍历再复制random的指向，第三轮把他们剥离。
     * A->B
     * A->A'->B->B'
     * 详细可见solutions里的图https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cruiser = head;
        //1. INSERT，A->A'->B->B'
        while (cruiser != null) {
            RandomListNode tmp = cruiser.next;
            cruiser.next = new RandomListNode(cruiser.val);
            cruiser.next.next = tmp;
            cruiser = cruiser.next.next;
        }

        //2. 安排RANDOM的指向
        cruiser = head;
        while (cruiser != null && cruiser.next != null) {
            if (cruiser.random != null) {
                cruiser.next.random = cruiser.random.next;
            }
            cruiser = cruiser.next.next;
        }

        //3. SEPARATE (Next pointer of nodes from the original list MUST NOT be modified.)
        //注意，因为是Deep Copy，不能改变原来list的结构
        cruiser = head;
        RandomListNode res = cruiser.next;
        while (cruiser != null) {
            RandomListNode copyCruiser = cruiser.next;
            cruiser.next = cruiser.next.next;//这行跟下面一行不能颠倒不然null pointer。画个图容易理解，要顺序地拆解，而不应该先改变copyCruiser.next，这样cruiser.next就没办法找到后一个节点了
            copyCruiser.next = copyCruiser.next != null ? copyCruiser.next.next : null;
            cruiser = cruiser.next;
        }
        return res;
    }
}
