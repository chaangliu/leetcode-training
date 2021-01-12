#include <iostream>
#include <string>
#include <vector>
#include <map>
using namespace std;
/**
    Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
    An array A is a zigzag array if either:
    Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
    OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
    Return the minimum number of moves to transform the given array nums into a zigzag array.
    Example 1:
    Input: nums = [1,2,3]
    Output: 2
    Explanation: We can decrease 2 to 0 or 3 to 1.
    Example 2:

    Input: nums = [9,6,1,6,2]
    Output: 4
    Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= 1000
    20190804
**/

/**
 * 这题我的困惑主要是是否需要连续操作两个相邻的数字？
 * 答案是不用。[2,7,10,9,10,9]，变成[2,7,6,9,10,9]之后，后面的9没有任何理由降低了，因为它需要比前后都要大，只有可能想要增加。
 */
    int movesToMakeZigzag(vector<int> &nums) {
        nums.insert(nums.begin(), INT_MAX);
        nums.push_back(INT_MAX);
        int res[] = {0, 0};
        for (int i = 1; i < nums.size() - 1; ++i) {
            int tar = min(nums[i - 1], nums[i + 1]) - 1;
            res[i % 2] += max(0, nums[i] - tar);
        }
        return min(res[0], res[1]);
    }