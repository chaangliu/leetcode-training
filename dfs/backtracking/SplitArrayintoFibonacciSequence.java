package dfs.backtracking;

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
 */
public class SplitArrayintoFibonacciSequence {
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
