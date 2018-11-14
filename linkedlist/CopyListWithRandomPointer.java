package linkedlist;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}

public class CopyListWithRandomPointer {
    //A -> A' -> B -> B'
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cruiser = head;
        //1. INSERT
        while (cruiser != null) {
            RandomListNode tmp = cruiser.next;
            cruiser.next = new RandomListNode(cruiser.label);
            cruiser.next.next = tmp;
            cruiser = cruiser.next.next;
        }

        //2. INIT RANDOM
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


    public static void main(String args[]) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);

        node1.next = node2;
        node1.random = node3;
        node2.next = node3;
        node2.random = node1;
        node3.random = node1;

        new CopyListWithRandomPointer().copyRandomList(node1);
    }
}
