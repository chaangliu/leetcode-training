package array;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 * <p>
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * 20190126
 */
public class FindTheDuplicateNumber {
    /**
     * 题意：一个数组里的数字在[1,n]范围内，但是里面有n+1个数字，也就是里面至少有2个数字是相同的。请用O(n)时间O(1)空间找出这个数。数组read only。
     * //前两个approach不满足给出的限制条件
     * //Approach #1 Sorting [Accepted]
     * //Approach #2 Set [Accepted]
     * //Approach #3 Floyd's Tortoise and Hare (Cycle Detection) [Accepted]
     * //我以为这题给出1~n+1的限定条件是提示可以用位运算，或者除法运算来解决；然而是为了方便指针指向
     * 如果快慢指针指向的两个数字相同，相当于两个node指向了同一个node，也就是出现了环；
     * 于是把这题变成了142题，寻找环的入口节点（剑指offer也有这题）
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise]; // slow = slow.next
            hare = nums[nums[hare]]; // fast = fast.next.next
        } while (tortoise != hare);

        // Find the "entrance" to the cycle. 因为相遇的节点不一定是重复节点

        //链接：https://www.nowcoder.com/questionTerminal/253d2c59ec3e4bc68da16833f79a38e4
        //先说个定理：两个指针一个fast、一个slow同时从一个链表的头部出发
        //fast一次走2步，slow一次走一步，如果该链表有环，两个指针必然在【环内】相遇
        //此时只需要把其中的一个指针重新指向链表头部，另一个不变（还在环内），
        //这次两个指针一次走一步，相遇的地方就是入口节点。
        //这个定理的证明可以去Linked List Cycle II那题去看。
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }
}
