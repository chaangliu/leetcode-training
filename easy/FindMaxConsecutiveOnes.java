package easy;

public class FindMaxConsecutiveOnes {
    /**
     * 题意：找出最长的连续1的长度。
     * 解法：一次遍历。
     */
    public int findMaxConsecutiveOnes(int[] A) {
        int cnt = 0, max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                cnt++;
                max = Math.max(max, cnt);
            } else {
                cnt = 0;
            }
        }
        return max;
    }
}
