package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * 20190413
 */
public class JumpGameII {

    /**
     * 题意：给你一个数组，第i位的数字代表能跳A[i]距离。问最少跳几步能跳到最后。
     * approach1: bfs
     * 这题听说要用greedy，感觉gg；看了一眼讨论区说用bfs，想了一下茅塞顿开，求的就是bfs要蔓延几次能从头蔓延到last index
     * My AC Code:
     */
    class Pair {
        int num, idx;

        Pair(int n, int index) {
            this.num = n;
            this.idx = index;
        }
    }

    public int jump(int[] nums) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(nums[0], 0));
        int res = 0, lastIndex = 0;
        if (nums.length == 1) return 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                lastIndex = Math.max(p.num + p.idx, lastIndex);
                if (lastIndex >= nums.length - 1) return res;
                if (i != size - 1) continue;
                for (int j = p.idx + 1; j <= lastIndex; j++) {
                    queue.offer(new Pair(nums[j], j));
                }
            }
        }
        return res;
    }

    /**
     * 不使用queue的bfs
     */
    int jump(int A[], int n) {
        if (n < 2) return 0;
        int level = 0, currentMax = 0, i = 0, nextMax = 0;

        while (currentMax - i + 1 > 0) {        //nodes count of current level>0
            level++;
            for (; i <= currentMax; i++) {    //traverse current level , and update the max reach of next level
                nextMax = Math.max(nextMax, A[i] + i);
                if (nextMax >= n - 1) return level;   // if last element is in level+1,  then the min jump=level
            }
            currentMax = nextMax;
        }
        return 0;
    }

    /**
     * approach2: greedy
     * 每次尝试跳最远；触及到边界代表需要再跳一次，curEnd第一次是0，第二次是2，没有第三次，说明需要跳两次。思路跟bfs有点像，都要找当前能跳的最远index。
     * 跟bfs不同的地方在于，bfs不需要完整遍历一遍数组，中途跳出index范围了就会退出
     * [2,3,1,1,4]
     */
    public int jump__GREEDY(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
