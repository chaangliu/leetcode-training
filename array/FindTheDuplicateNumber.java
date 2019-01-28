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

    //前两个approach不满足给出的限制条件
    //Approach #1 Sorting [Accepted]
    //Approach #2 Set [Accepted]

    //Approach #3 Floyd's Tortoise and Hare (Cycle Detection) [Accepted]
    //我以为这题给出1~n+1的限定条件是提示可以用位运算，或者除法运算来解决；然而是为了方便指针指向

    //而是模拟快慢指针，于是把这题变成了142题，寻找环的入口节点（剑指offer也有这题）
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle. 因为相遇的节点不一定是重复节点

        //链接：https://www.nowcoder.com/questionTerminal/253d2c59ec3e4bc68da16833f79a38e4
        //先说个定理：两个指针一个fast、一个slow同时从一个链表的头部出发
        //fast一次走2步，slow一次走一步，如果该链表有环，两个指针必然在【环内】相遇
        //此时只需要把其中的一个指针重新指向链表头部，另一个不变（还在环内），
        //这次两个指针一次走一步，相遇的地方就是入口节点。
        //这个定理可以自己去网上看看证明。
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }

    public static void main(String args[]) {
//        int [] num = {3,1,3,4,2};
        int[] num = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        new FindTheDuplicateNumber().findDuplicate(num);
    }
}
