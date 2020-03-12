package linkedlist;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * Created by DrunkPiano on 2017/3/26.
 * 20200207 --review
 */

class LinkedListCycleII {
    /**
     * 题意：寻找有环链表的入口。
     * 解法：这个是经典题，需要一些公式推导，CC150上有。
     * 以下题解摘自：https://blog.csdn.net/weixin_40807247/article/details/91447922
     * ---------------------------------------------
     * 从链表起始处到环入口长度为：a，从环入口到Faster和Slower相遇点长度为：x，整个环长为：c。
     * 明确了以上信息，就可以开始做运算了。
     * 假设从开始到相遇，Slower走过的路程长为s，由于Faster的步速是Slower的2倍，那么Faster在这段时间走的路程长为2s。
     * 而对于Faster来说，他走的路程还等于之前绕整个环跑的n圈的路程nc，加上最后这一次遇见Slower的路程s。
     * 所以我们有：
     *                2s = nc + s
     对于Slower来说，他走的路程长度s还等于他从链表起始处到相遇点的距离，所以有：
     *                 s = a + x
     * 通过以上两个式子代入化简有：
     *                 a + x = nc
     *                 a = nc - x
     *                 a = (n-1)c + c-x
     *                 a = kc + (c-x)
     * 那么可以看出，c-x，就是从相遇点继续走回到环入口的距离。上面整个式子可以看出，如果此时有个pointer1从起始点出发并且同时还有个pointer2从相遇点出发继续往前走（都只迈一步），那么绕过k圈以后， pointer2会和pointer1在环入口相遇。这样，换入口就找到了。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode walker = head;
        ListNode runner = head;
        ListNode meetPoint = null;
        //找到相遇点meetPoint
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                meetPoint = walker;
                break;
            }
        }
        if (meetPoint == null) return null;
        // 此时，从链表开头以及meetPoint出发，一起走，直到相遇，一定在入口处相遇
        while (head != meetPoint) {
            head = head.next;
            meetPoint = meetPoint.next;
        }
        return head;
    }
}
