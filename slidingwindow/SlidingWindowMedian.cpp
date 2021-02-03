#include <iostream>
#include <string>
#include <vector>
#include <map>
using namespace std;

class Solution {
/**
 * 题意：求滑动窗口中的中位数。
 * 解法：利用C++的multiset。用Java的话要构建大根堆小根堆，类似Find Median from Data Stream
 * 以下代码来自https://leetcode-cn.com/problems/sliding-window-median/solution/xuan-ze-he-gua-de-shu-ju-jie-gou-zhe-ti-muyt4/
**/
public:
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        vector<double> res;
        multiset<double> st;
        for (int i = 0; i < nums.size(); ++i) {
            if (st.size() >= k) st.erase(st.find(nums[i - k]));
            st.insert(nums[i]);
            if (i >= k - 1) {
                auto mid = st.begin();
                std::advance(mid, k / 2);
                res.push_back((*mid + *prev(mid, (1 - k % 2))) / 2);
            }
        }
        return res;
    }
};