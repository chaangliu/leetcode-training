package dfs;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
 * Example:
 * Input:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * Output:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 * 20191122
 */
public class FlattenaMultilevelDoublyLinkedList {

    /**
     * 题意：给你一个多级的双向链表，让你把它flatten成一级，按照题意那样。
     * 解法就是dfs，依次处理每个node。我的代码：
     */
    class Solution {
        public Node flatten(Node head) {
            if (head == null) return null;
            Node dummy = new Node();
            dummy.next = head;
            dfs(head, dummy);
            return dummy.next;
        }

        /**
         * dfs返回当前层级的最后一个node
         * if cur.child != null , tmp = cur.next;
         * cur.next = cur.child;
         * int last = dfs(cur)
         * last.next = tmp;
         **/
        private Node dfs(Node node, Node prev) {
            if (node == null) return prev;
            if (node.child == null) return dfs(node.next, node);
            Node tmp = node.next;
            node.next = node.child;
            node.next.prev = node;
            Node last = dfs(node.child, node);
            last.next = tmp;
            if (tmp != null) tmp.prev = last;
            node.child = null;//忘记了
            return dfs(last.next, last);
        }
    }

    /**
     * 网上的non-recursion代码：
     * 1. Start form the head , move one step each time to the next node
     * 2. When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, by doing this we merged the child chain back to the main thread
     * 3. Return to p and proceed until find next node with child.
     * 4. Repeat until reach null
     */
    public Node flatten__(Node head) {
        if( head == null) return head;
        // Pointer
        Node p = head;
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null )
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}


// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
