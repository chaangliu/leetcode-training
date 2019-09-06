package dfs;

import java.util.ArrayList;
import java.util.List;

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
}
