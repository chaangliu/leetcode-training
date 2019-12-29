package dfs;

import java.util.HashSet;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 * Constraints:
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 * 20191229
 */
public class JumpGameIII {
    /**
     * 题意：从arr的start位置开始，每次只能向左或者向右跳arr[i]步，问能否跳到一个val为0的index。
     * 显然是dfs，我还加了个memo，其实是不必要的，一个visited就够了。而且，不需要backtrack其实。
     */
    public boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new HashSet<>(), new HashSet<>());
    }

    private boolean dfs(int[] arr, int start, HashSet<Integer> memo, HashSet<Integer> visited) {
        if (arr[start] == 0) return true;
        if (visited.contains(start)) return false;
        if (memo.contains(start)) return false;
        int l = start - arr[start], r = start + arr[start];
        if ((l < 0 || l >= arr.length) && (r < 0 || r >= arr.length)) {
            memo.add(start);
            return false;
        }
        if (l >= 0 && l < arr.length) {
            visited.add(start);
            if (dfs(arr, l, memo, visited)) return true;
            visited.remove(start);//其实不需要backtrack
        }
        if (r >= 0 && r < arr.length) {
            visited.add(start);
            if (dfs(arr, r, memo, visited)) return true;
            visited.remove(start);
        }
        return false;
    }

    /**
     * 3 lines, 0ms
     * 非常巧妙地用一个+=length的方式判断是否visited过，这样也是O(n)。
     * 'We can also use the input array to track visited cells. Note that I just add arr.length to the value, in case we want to restore the original values later.'
     */
    public boolean canReach_(int[] arr, int st) {
        if (st >= 0 && st < arr.length && arr[st] < arr.length) {
            int jump = arr[st];
            arr[st] += arr.length;
            return jump == 0 || canReach(arr, st + jump) || canReach(arr, st - jump);
        }
        return false;
    }
}
