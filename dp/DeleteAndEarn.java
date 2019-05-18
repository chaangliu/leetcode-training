package dp;

public class DeleteAndEarn {
    /**
     * 这题跟house rubber几乎一模一样，就是要先用bucket处理一下
     * dp[i] = max(dp[i - 2] + val, dp[i - 1])
     */
    public int deleteAndEarn(int[] nums) {
        int[] buckets = new int[10001];
        for (int n : nums) {
            buckets[n] += n;
        }
        //2(2), 3(3), 4(1)
        //pre2, pre1, cur
        int pre1 = 0, pre2 = 0;
        for (int val : buckets) {
            int tmp = Math.max(pre2 + val, pre1);
            pre2 = pre1;
            pre1 = tmp;
        }
        return pre1;
    }

    /**
     也可以用Map来减少space使用
     *     public int deleteAndEarn(int[] nums) {
     final Map<Integer, Integer> values = new HashMap<>();
     for (final int num : nums) {
     values.put(num, values.getOrDefault(num, 0) + num);
     }
     int pre = 0, cur = 0;
     for (final int num : values.keySet()) {
     if (!values.containsKey(num - 1)) {
     pre = cur;
     cur += values.get(num);
     } else {
     final int temp = Math.max(pre + values.get(num), cur);
     pre = cur;
     cur = temp;
     }
     }
     return cur;
     }
     */
}
