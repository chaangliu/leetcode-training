package array;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * <p>
 * Created by DrunkPiano on 2017/1/18.
 * 20200111 --review
 */

public class JumpGame {
    /**
     * 20190412 复习一下solutions里4种循序渐进的解法
     * Approach 1: Backtracking
     */
    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }


    /**
     * Approach 2: Dynamic Programming Top-down；也就是对递归的memorization
     * Time complexity : O(n^2)
     * Space complexity : O(2n) = O(n)O(2n)=O(n). First n originates from recursion.
     * 这题用了Enum来标识三种状态，其实也可以用一个Boolean[]memo
     */
    enum Index {
        GOOD, BAD, UNKNOWN
    }

    Index[] memo;

    public boolean canJumpFromPosition__(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition__(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump__DP(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition__(0, nums);
    }


    /**
     * Approach 3: Dynamic Programming Bottom-up
     * 从倒数第二格开始向前，推测当前index覆盖范围内的格子是否能抵达最后一格
     * 为什么是从后往前，因为这题的最终状态是起点，而不是通常的终点；也就是这次bottom是结尾，而不是开头。
     * O(n^2)
     */
    public boolean canJump__DP2(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }

    /**
     * Approach 4: Greedy
     * 从后往前，只要当前index能cover到最小的一个能调到结尾的index(lastPos)，lastPos就换成当前index
     * 也就是，每次从当前位置尝试跳最远，如果能跳到终点线，就把终点线🏁提前。不断更新最小的valid的那个index（终点线）。
     * 其实这个greedy解法，跟前面几种并不很像，只是跟DP有那么一点像，不是很intuitive。
     * 时间：O(n)
     */
    public boolean canJump_Greedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }


    public boolean canJump__2017(int[] nums) {
        int maxCover = 0;
        for (int i = 0; i <= nums.length - 1 && i <= maxCover; i++) {
            maxCover = Math.max(maxCover, nums[i] + i);
            if (maxCover >= nums.length - 1) return true;
        }
        return false;
    }
}