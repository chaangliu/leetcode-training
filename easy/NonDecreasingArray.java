package easy;

/**
 *
 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。

 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。

 你的点数就是你拿到手中的所有卡牌的点数之和。

 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 示例 1：
 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 输出：12
 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 */
public class NonDecreasingArray {
    /**
     * 题意：给出一个数组，只能修改其中一个数字，问是否能把它变成非递减数列。
     * 解法：容易想到替换成前后一致的数字，但是很容易漏掉case，比如[4,2,3]你需要把A[i - 1]换成A[i]，但[3,3,2]你就需要把A[i]换成A[i - 1]了，所以我采取两种都试一试的方法。
     * one pass解法就是基于A[i]和A[i - 2]的关系来做了。
     */
    public boolean checkPossibility(int[] A) {
        int[] B = (int[]) A.clone();
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                A[i] = A[i + 1];
                break;
            }
        }
        for (int i = 1; i < B.length; i++) {
            if (B[i] < B[i - 1]) {
                B[i] = B[i - 1];
                break;
            }
        }
        int cnt1 = 0, cnt2 = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) cnt1++;
            if (B[i] < B[i - 1]) cnt2++;
        }
        return cnt1 == 0 || cnt2 == 0;
    }

    /**
     * one pass解法：在A[i - 1]大于A[i]的时候，判断A[i - 2]与A[i]的大小关系，然后决定A[i] = A[i - 1]或者A[i - 1] = A[i]
     */
    public boolean checkPossibility_(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                sum++;
                if (sum >= 2) return false;
                if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                    nums[i] = nums[i - 1];
                } else nums[i - 1] = nums[i];
            }
        }
        return true;
    }
}
