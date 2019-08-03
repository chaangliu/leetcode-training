package dp;

/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * <p>
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 * <p>
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 * <p>
 * Example 1:
 * Input: "a"
 * Output: 1
 * <p>
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 * 20190803
 */
public class UniqueSubstringsinWraparoundString {

    /**
     * 通过观察发现，唯一的子字符串的数量就等于：分别以a，b， c， d。。。z结尾的最长子字符串的长度之和(不能重复)
     * 记录以每个字母结尾的substring的长度
     * * c++写法
     */
    //    class Solution {
    //        public:
    //        int findSubstringInWraproundString(string p) {
    //            vector<int> letters(26, 0);//记录以每个字母结尾的substring的长度
    //            int cur = 0;
    //            for (int i = 0; i < p.size(); ++i) {
    //                if (i > 0 && (p[i] - p[i - 1] == 1 || p[i - 1] - p[i] == 25))
    //                    cur++;
    //                else
    //                    cur = 1;
    //                int index = p[i] - 'a';
    //                letters[index] = max(letters[index], cur);    }
    //            int res = 0;
    //            for (int i = 0; i < 26; ++i) {
    //                res += letters[i];
    //            }
    //            return res;
    //        }
    //    };
}
