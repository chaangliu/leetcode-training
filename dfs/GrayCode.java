package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * <p>
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * Example 2:
 * <p>
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 * A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 * Therefore, for n = 0 the gray code sequence is [0].
 * 20190907
 */
public class GrayCode {
    /**
     * 题意是按顺序打印长度位n的二进制转换成十进制的值，要求相邻的二进制数bit不同的数量为1
     * tag是backtracking，于是我就用了dfs做的。
     * approach1. 答案区的迭代做法
     * iteratively + bit manipulation
     * 对于res里的每个数字，从后往前地执行『按位或』操作。
     * 000
     * 001
     * 011
     * 111
     * ..
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int size = res.size();
            for (int k = size - 1; k >= 0; k--)
                res.add(res.get(k) | 1 << i);
        }
        return res;
    }

    /**
     * approach2.
     * 我的c++ code，借助了hashSet
     */
    //    class Solution {
    //        public:
    //        unordered_set<string> visited;
    //
    //        void helper(vector<int> &res, string &s, int &n) {
    //            if (visited.size() == pow(2, n)) {
    //                return;
    //            }
    //            for (int i = 0; i < n; ++i) {
    //                s[i] ^= 1;
    //                if ((visited.find(s)) != visited.end()) {
    //                    s[i] ^= 1;
    //                    continue;
    //                }
    //                visited.insert(s);
    //                int decimal = 0;
    //                for (int j = 0; j < n; ++j) {
    //                    decimal += (s[n - 1 - j] - '0') * pow(2, n - 1 - j);
    //                }
    //                res.push_back(decimal);
    //                cout << s << endl;
    //                helper(res, s, n);
    //            }
    //        }
    //
    //        vector<int> grayCode(int n) {
    //            string s = "";
    //            vector<int> res;
    //            for (int i = 0; i < n; ++i) {
    //                s.push_back('0');
    //            }
    //            res.push_back(0);
    //            if (n == 0) return res;
    //
    //            visited.insert(s);
    //            helper(res, s, n);
    //            return res;
    //        }
    //    };

    /**
     * approach 3. 有点像二叉树中序遍历..
     * 00,01,11,10
     * 非典型backtracking，需要看一下CS106B
     * https://leetcode.com/problems/gray-code/discuss/29880/Backtracking-C++-solution
     * https://www.youtube.com/watch?v=78t_yHuGg-0
     */
    int nums = 0;

    public List<Integer> grayCode_(int n) {
        List<Integer> res = new ArrayList<>();
        backTrack(n, res);
        return res;
    }

    private void backTrack(int n, List<Integer> res) {
        if (n == 0) {
            res.add(nums);
            return;
        }
        backTrack(n - 1, res);
        nums ^= (1 << n - 1);
        backTrack(n - 1, res);//从后往前
    }

    public static void main(String args[]) {
        new GrayCode().grayCode_(2);
    }
}
