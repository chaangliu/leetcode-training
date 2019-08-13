package array;

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 * <p>
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * <p>
 * Return true if and only if you can transform str1 into str2.
 * Example 1:
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 * <p>
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 * Note:
 * 1 <= str1.length == str2.length <= 10^4
 * Both str1 and str2 contain only lowercase English letters.
 */
public class StringTransformsIntoAnotherString {
    /**
     * 这题我一开始没注意到第一个例子里"the order of conversions matter"这句话，所以没考虑到顺序地变换可能造成后面重复的问题。
     * 后来我仍然执着于采取某种策略改变str1，比如先跳过aa，处理后面的。
     * 但是这题实际上要想清楚，可以使用any letter的，不一定一次到位，所以可以用一个临时字母。所以，总有办法把str1转换成str2，而这个步骤我们不需要模拟。
     * 那这样就有一个case就是两个字符串都使用了所有的字母，这样无法使用临时字母了。还有一些其他corner case要考虑。
     *
     * 我写的c++代码:
     */
    //    class Solution {
    //        public:
    //        bool canConvert(string str1, string str2) {
    //            if (str1.compare(str2)== 0) return true;
    //            if (str1.size() >= 26) {
    //                int cnt[26];
    //                int cnt2[26];
    //                memset(cnt, 0, sizeof(cnt));
    //                memset(cnt2, 0, sizeof(cnt));
    //                int c = 0, c2 = 0;
    //                for (int i = 0; i < str1.size(); ++i) {
    //                    cnt[str1[i] - 'a']++;
    //                    cnt2[str2[i] - 'a']++;
    //                    if (cnt[str1[i] - 'a'] == 1)
    //                        c++;
    //                    if (cnt2[str2[i] - 'a'] == 1)
    //                        c2++;
    //                }
    //                if (c == 26 && c2 == 26)
    //                    return false;
    //            }
    //
    //            unordered_map<char, char> map;
    //            for (int i = 0; i < str1.size(); ++i) {
    //                if (str1[i] == str2[i]) continue;
    //                if (map.find(str1[i]) == map.end()) map[str1[i]] = str2[i];
    //                else if (map[str1[i]] != str2[i]) return false;
    //            }
    //            return true;
    //        }
    //    };


    // 可以参考:https://wnjxyk.keji.moe/index.php/archives/31/
}
