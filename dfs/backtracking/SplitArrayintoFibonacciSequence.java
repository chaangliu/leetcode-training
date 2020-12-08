package dfs.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 * <p>
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 * <p>
 * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * F.length >= 3;
 * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 * <p>
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 * <p>
 * Example 1:
 * <p>
 * Input: "123456579"
 * Output: [123,456,579]
 * Example 2:
 * <p>
 * Input: "11235813"
 * Output: [1,1,2,3,5,8,13]
 * Example 3:
 * <p>
 * Input: "112358130"
 * Output: []
 * Explanation: The task is impossible.
 * Example 4:
 * <p>
 * Input: "0123"
 * Output: []
 * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
 * Example 5:
 * <p>
 * Input: "1101111"
 * Output: [110, 1, 111]
 * Explanation: The output [11, 0, 11, 11] would also be accepted.
 * Note:
 * <p>
 * 1 <= S.length <= 200
 * S contains only digits.
 * 20190905
 * 20191101
 */
public class SplitArrayintoFibonacciSequence {
    /**
     * 题意：给你一个String，把它分成任意一个Fibonacci序列（长度>=3）。如果不能分，返回空数组。1<=S长度<=200
     * 因为长度只有200所以可以暴力搜。用start记录当前递归开始位置的这种做法好像做过几题。
     * 复杂度分析:
     * Time complexity is O(10^n) => O(branches^depth).
     * At each level, we branch at most 10 times, as the for-loop will run until Integer.MAX_VALUE is reached (which is maximum 10 digits in length).
     * Our recursive calls go to a maximum depth of n.
     * The time complexity of any recursive function is O(branches^depth)
     * Therefore the upper-bound time complexity is O(10^n)
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        helper(S, res, 0);
        return res;
    }

    public boolean helper(String s, List<Integer> res, int start) {
        if (start == s.length() && res.size() >= 3) {
            return true;
        }
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(start) == '0' && i > start) {
                return false;
            }
            long num = Long.parseLong(s.substring(start, i + 1));//从start开始截断一个数
            if (num > Integer.MAX_VALUE) {
                return false;
            }
            int size = res.size();
            // early termination
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) {//本次split已经超过前两个数字之和，本层递归结束，返回上一层。
                return false;
            }
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);
                //只要找到一个答案就可结束
                if (helper(s, res, i + 1)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    /**
     * 重新看了这题，思路没有第一时间想到，但其实直接暴力就行；又写了下，corner case要注意不能直接判断startsWith 0，如"0000"还是有答案的。另外要考虑整数overflow。还有，无需额外全局变量的。
     */
    public List<Integer> splitIntoFibonacci_(String S) {
        helper(S.toCharArray(), 0, new ArrayList<>());
        if (S.startsWith("0") && result.size() > 1 && result.get(0) > 0) return new ArrayList<>();
        return result;
    }

    List<Integer> result = new ArrayList<>();

    private boolean helper(char[] A, int start, List<Integer> res) {
        if (start == A.length) {
            if (res.size() >= 3) {
                result = res;
                return true;
            }
            return false;
        }
        int num = 0;
        for (int i = start; i < A.length; i++) {
            if (num > Integer.MAX_VALUE / 10) return false;
            num = num * 10 + (A[i] - '0');
            int size = res.size();
            if (size < 2) {
                res.add(num);
                if (helper(A, i + 1, res)) return true;
                res.remove(res.size() - 1);
            } else {
                if (res.get(size - 2) + res.get(size - 1) == num) {
                    res.add(num);
                    if (helper(A, i + 1, res)) return true;
                    res.remove(res.size() - 1);
                }
            }
        }
        return false;
    }
//    public:
//    /**
//     * 这题因为长度只有200，所以暴力搜索，做法值得学习。用C++的话，由于substr的第二个参数是步长，所以for循环枚举步长比较方便。用Java的话，可以直接枚举结尾index
//     * 关于long和long long的区别：long is equivalent to long int , just as short is equivalent to short int . A long int is a signed integral type that is at least 32 bits, while a long long or long long int is a signed integral type is at least 64 bits. This doesn't necessarily mean that a long long is wider than a long
//     */
//    bool backtrack(string &S, int start, vector<int> &nums) {
//        int n = S.size();
//        if (start >= n && nums.size() >= 3) {
//            return true;
//        }
//        // Since '0' in beginning is not allowed therefore if the current char is '0'
//        // then we can use it alone only and cannot extend it by adding more chars at the back.
//        // Otherwise we make take upto 10 chars (length of INT_MAX)
//        int maxSplitSize = (S[start] == '0') ? 1 : 10;//2147483647
//        //start代表起始位置，i代表步长
//        for (int i = 1; i <= maxSplitSize && start + i <= S.size(); i++) {
//            long long num = stoll(S.substr(start, i));
//            if (num > INT_MAX)return false;
//            int sz = nums.size();
//            if (sz >= 2 && (long long) nums[sz - 1] + (long long) nums[sz - 2] < num)
//            return false;
//            if (sz <= 1 || (long long) nums[sz - 1] + (long long) nums[sz - 2] == num) {
//                nums.push_back(num);
//                if (backtrack(S, start + i, nums))
//                    return true;
//                nums.pop_back();
//            }
//        }
//        return false;
//    }
//
//    vector<int> splitIntoFibonacci(string S) {
//        vector<int> nums;
//        // Backtrack from 0th char
//        backtrack(S, 0, nums);
//        return nums;
//    }
//};
}
