package jianzhioffer;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class CopyRandomListNode {
    //第一步，在旧链表中创建新链表，此时不处理新链表的next和random（因为random指向的节点可能还没初始化出来）
    //第二步，初始化新链表的random
    //第三步，分离两个链表
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode node = pHead;

        //1. A1 -> A1' -> A2 -> A2'
        while (node != null) {
            RandomListNode tmp = node.next;
            node.next = new RandomListNode(node.label);
            node.next.next = tmp;
            node = tmp;
        }

        //2. 初始化random
        node = pHead;
        while (node != null) {
            if (node.random != null) {//易错
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        //3. 分离, 注意是deep copy所以不能改变pHead结构
        node = pHead;
        RandomListNode res = node.next;
        while (node != null) {
            RandomListNode cloneNode = node.next;
            node.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            node = node.next;
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

        new CopyRandomListNode().Clone(node1);
    }
}
