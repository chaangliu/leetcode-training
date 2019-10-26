package binarysearch;

/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * <p>
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 * <p>
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * <p>
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 * Example 1:
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 * Constraints:
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 * 20191022
 */
public class DivideChocolate {
    /*
     * 双周赛第四题。
     * 题意：一个数组，经过K次切割分割成K+1块，求sum最小的那块的最大值。
     * 解法：二分 + 贪心。
     * 通过二分的Mid来充当最小sum，用这个sum带入到数组中去，从头开始遍历，每找到一段>=mid的值之后就切割下来，如果找到了>=K+1段，那么这个mid就可以更大一些，否则这个mid需要更小一些。
     * 值得注意的是这里二分的边界。参考：https://www.acwing.com/solution/leetcode/content/5444/
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int total = 0;
        for (int num : sweetness) total += num;
        int l = 0, r = total;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid, K, sweetness))
                l = mid + 1;//最后找到l的时候又加了一次才退出，所以最后要减去一次(但是K=0的时候不需要减)。相当于upper_bound，最后退出时会比最大答案大1，例如mid:(1+ 45)/2 = 23, (1+23)/2=12, (1+12)/2=6
            else r = mid;
        }
        return K != 0 ? l - 1 : l;
    }

    //check if array can be split into at least K + 1parts, with each part's sum >= mid
    private boolean check(int mid, int K, int[] sweetness) {
        int sum = 0, cnt = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= mid) {
                sum = 0;
                cnt++;
                if (cnt >= K + 1) return true;
            }
        }
        return false;
    }
}
