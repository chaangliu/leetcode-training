package dp;

/**
 * We have two integer sequences A and B of the same non-zero length.
 * <p>
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
 * <p>
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 * <p>
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.
 * <p>
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Note:
 * <p>
 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 * 20190927
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    /**
     * 一开始想了个贪心地换当前位置数字的方程，然后我枚举了一些case后发现并不适用某些情况，因为你可以换当前位置，也有可能要换前一个位置，也有可能两者都行。
     * 我也想到了用一个二维数组，记录当前位置换与不换，但是想不出转移方程，感觉需要综合考虑i - 1, i, i + 1三个位置的数字..
     * <p>
     * //1,4,7,10
     * //1,4,9,8
     * //1,4,7,100,11
     * //1,4,9,10,101
     * //1,4,7,8,11
     * //1,4,9,10,9
     * <p>
     * 以下是lee215的解法，使用了两个数组分别记录当前位置是否交换（观察到如果满足交叉条件，那么换i-1或者i组效果一样）。
     * 其实仍然不太懂这个逻辑为什么能成立，这题属实抽象。
     **/
    public int minSwap(int[] A, int[] B) {
        int N = A.length;
        int[] swap = new int[1000];
        int[] not_swap = new int[1000];
        swap[0] = 1;
        for (int i = 1; i < N; ++i) {
            swap[i] = not_swap[i] = N;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                not_swap[i] = not_swap[i - 1];
                swap[i] = swap[i - 1] + 1;//前面一个换过了，那么当前也需要换一次才能保证都递增
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                not_swap[i] = Math.min(swap[i - 1], not_swap[i]);//前面一个换了，当前就无需再换
                swap[i] = Math.min(swap[i], not_swap[i - 1] + 1);//前面一个没换，当前需要换一次
            }
        }
        return Math.min(swap[N - 1], not_swap[N - 1]);
    }
}
