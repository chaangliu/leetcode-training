package dfs;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * <p>
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * <p>
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * <p>
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * <p>
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 * <p>
 * 20190303 at TaoYuan HolidayInn
 */
public class MatchSticksToSquare {
    /**
     * 以下做法无法AC：
     * 173 / 174 test cases passed.
     * [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
     */
    int count = 0;

    public boolean makesquare__MYSOLUTION(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int totalLen = 0;
        for (Integer i : nums) totalLen += i;
        if (totalLen % 4 != 0) return false;
        int edgeLen = totalLen / 4;
        dfs(nums, edgeLen, new int[]{edgeLen}, new boolean[nums.length]);
        return count == 4;
    }

    //这题让我有点想不通的是如何在找到一条边之后继续找而不恢复状态
    private boolean dfs(int[] nums, int edgeLen, int[] remain, boolean[] visited) {
        if (count == 4) return true;
        if (remain[0] < 0) return false;
        if (remain[0] == 0) {
            count++;
            remain[0] = edgeLen;
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (nums[i] > edgeLen) return false;
                remain[0] -= nums[i];
                if (!dfs(nums, edgeLen, remain, visited)) {
                    remain[0] += nums[i];
                    visited[i] = false;
                }
            }
        }
        return false;
    }


    /**
     * -------------------------------------------------------------------------------------------
     * * 以下做法来自solution:
     */

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;

        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[index] > target) continue;
            sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
            sums[i] -= nums[index];
        }

        return false;
    }

}
