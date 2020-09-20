package greedy.sweepline;

/**
 * 有一个整数数组 nums ，和一个查询数组 requests ，其中 requests[i] = [starti, endi] 。第 i 个查询求 nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi] 的结果 ，starti 和 endi 数组索引都是 从 0 开始 的。
 *
 * 你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。
 *
 * 由于答案可能会很大，请你将它对 109 + 7 取余 后返回。
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * 输出：19
 * 解释：一个可行的 nums 排列为 [2,1,3,4,5]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * 总和为：8 + 3 = 11。
 * 一个总和更大的排列为 [3,5,4,2,1]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * 总和为： 11 + 8 = 19，这个方案是所有排列中查询之和最大的结果。
 * 20200920
 */
public class MaximumSumObtainedofAnyPermutation {
    /**
     * 题意：给你一个数组，一些query，每个query包含start和end，问这些start 到 end之间的数字相加的话，最大能组成的和。
     * 解法：暴力O(n^2)会超时; 正确解法是扫描线，先统计次数然后再计算前缀和。类似题目：CorporateFlightBookings
     */
//    class Solution {
//
//        private:
//                const long long MOD = 1e9 + 7;
//
//        public:
//        int maxSumRangeQuery(vector<int>& nums, vector<vector<int>>& requests) {
//
//            int n = nums.size();
//
//            vector<int> freq(n + 1);
//            for(const vector<int>& v: requests)
//            freq[v[0]] ++, freq[v[1] + 1] --;
//
//            for(int i = 1; i <= n; i ++)
//                freq[i] += freq[i - 1];
//
//            sort(freq.begin(), freq.begin() + n);
//            sort(nums.begin(), nums.end());
//
//            long long res = 0;
//            for(int i = n - 1; i >= 0; i --)
//                res = (res + (long long)nums[i] * freq[i]) % MOD;
//            return res;
//        }
//    };
}
