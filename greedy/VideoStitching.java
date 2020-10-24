package greedy;

import java.util.Arrays;

/**
 * You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.
 * <p>
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * <p>
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.
 * Example 1:
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 * Example 2:
 * <p>
 * Input: clips = [[0,1],[1,2]], T = 5
 * Output: -1
 * Explanation:
 * We can't cover [0,5] with only [0,1] and [0,2].
 * Example 3:
 * <p>
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * Output: 3
 * Explanation:
 * We can take clips [0,4], [4,7], and [6,9].
 * Example 4:
 * <p>
 * Input: clips = [[0,4],[2,8]], T = 5
 * Output: 2
 * Explanation:
 * Notice you can have extra video after the event ends.
 * Note:
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0], clips[i][1] <= 100
 * 0 <= T <= 100
 * 20191107
 */
public class VideoStitching {
    /**
     * 题意：给你一连串视频片段，求最小需要几个片段能剪接成一个[0,T]的视频。
     * 做法1，DP
     * dp[i]代表覆盖区间[0,i)需要的最少clips。
     * dp[i] = min{dp[aj]} + 1
     * Time O(NT), Space O(T)
     */
    public int videoStitching__DP(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;
        for (int i = 1; i <= T && dp[i - 1] < T; i++) {
            for (int[] c : clips) {
                if (c[0] <= i && i <= c[1])
                    dp[i] = Math.min(dp[i], dp[c[0]] + 1);
            }
        }
        return dp[T] == T + 1 ? -1 : dp[T];
    }

    /**
     * 做法2：贪心，类似jump game ii,按照开始时间从小到大排序，然后确定第res块clip能覆盖到的最远距离curFarest。
     * 比如，排序后第一个区间是[0,0]，那么就在这个范围内(对应while循环)找curFarest。下一轮会在[0,curFarest]范围内再找一个能跳最远的。
     * Time O(NlogN), Space O(1)
     **/
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (a, b) -> a[0] - b[0]);
        int res = 0;
        for (int i = 0, curEnd = 0, curFarest = 0; curEnd < T; curEnd = curFarest, res++) {
            while (i < clips.length && clips[i][0] <= curEnd) {
                curFarest = Math.max(curFarest, clips[i][1]);
                i++;
            }
            if (curFarest == curEnd) return -1;
        }
        return res;
    }


}
