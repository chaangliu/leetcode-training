package sort.bucketsort;

public class MaximumGap {
    /**
     * 题意：给你一些非负整数，请你用O(n)时间计算这些数中间最大的gap。
     * 解法：O(n)时间排序可以用桶排序。
     * 对于这题，原理是，保证每个桶内的最大值减去最小值gap > 桶长度，这样就可以比较前一个桶的最大值和后一个桶的最小值来更新gap的最大值了。
     * 桶排序的两个核心问题：
     * 1. 每个桶的长度是多少？换句话说，每个桶放置元素的范围是什么？
     * 2. 一共要准备多少个桶？
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1)); // 桶长度 = (max - min) / (nums.length - 1)
        Bucket[] buckets = new Bucket[(max - min) / bucketSize + 1]; // 桶个数 = (max - min) / bucketSize + 1
        for (int i = 0; i < nums.length; ++i) {
            int loc = (nums[i] - min) / bucketSize; // 每个数应该对应哪个桶 = (nums[i] - min) / bucketSize
            if (buckets[loc] == null) buckets[loc] = new Bucket();
            buckets[loc].min = Math.min(buckets[loc].min, nums[i]);
            buckets[loc].max = Math.max(buckets[loc].max, nums[i]);
        }
        int previousMax = Integer.MAX_VALUE;
        int maxGap = Integer.MIN_VALUE;
        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i] != null && previousMax != Integer.MAX_VALUE) {
                maxGap = Math.max(maxGap, buckets[i].min - previousMax);
            }
            if (buckets[i] != null) {
                previousMax = buckets[i].max;
                maxGap = Math.max(maxGap, buckets[i].max - buckets[i].min);
            }
        }
        return maxGap;
    }

    private class Bucket {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }
}
