package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
 * Example 2:
 * <p>
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
 * Example 3:
 * <p>
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 * <p>
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 * <p>
 * Input: text = "abcdef"
 * Output: 1
 * Constraints:
 * 1 <= text.length <= 20000
 * text consist of lowercase English characters only.
 * 20190814
 */
public class SwapForLongestRepeatedCharacterSubstring {
    /**
     * 这题意思是，找一个最长字母相同的substring。允许里面有个字符可以跟substring之外的字母替换。
     * Approach1.
     * 我的做法，linear scan，第一发现不同可以继续scan，第二次发现不同，就要断开，重新扫描。
     * 关键是从哪开始扫描？不能从断开的地方扫描，那样会漏掉一些case。要从第一次不同的pivot那里继续扫描。(这样时间复杂度挺高，目测最高O(n^2)？。
     */
    public int maxRepOpt1(String text) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 1;
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int len = 0, block = 0, pivot = -1;
        char role = text.charAt(0);
        int i = 0;
        while (i < text.length()) {
            boolean flag = false;
            char cur = text.charAt(i);
            if (cur == role) {
                len++;
                if (block == 0) res = Math.max(len, res);
                if (block == 1 && i == text.length() - 1) {
                    if (map.get(role) > len - 1) {
                        res = Math.max(len, res);
                    } else if (map.get(role) == len - 1) {
                        res = Math.max(len - 1, res);
                    }
                }
            } else if (block == 0) {
                len++;
                block++;
                pivot = i;
            } else if (block > 0) {
                if (map.get(role) > len - 1) {
                    res = Math.max(len, res);
                } else if (map.get(role) == len - 1) {
                    res = Math.max(len - 1, res);
                }
                i = pivot;
                len = 0;
                block = 0;
                role = text.charAt(pivot);
                flag = true;
            }
            if (!flag) i++;
        }
        System.out.println(res);
        return res;
    }

    /**
     * Approach2. Group and count
     * 先把每种字母的index记录下来，然后对每种字母的index进行扫描，允许中间跳过一个。
     * （其实我一开始也想到可以对每种字母进行扫描，但是没有想到可以先对index进行group）
     *
     * 看b站视频也是针对每个字母进行枚举的，复杂度O(26n)，比这个要高
     */
    //    int maxRepOpt1(string text, int res = 1) {
    //        vector<vector<int>> idx(26);
    //        for (auto i = 0; i < text.size(); ++i) idx[text[i] - 'a'].push_back(i);
    //        for (auto n = 0; n < 26; ++n) {
    //            auto cnt = 1, cnt1 = 0, mx = 0;
    //            for (auto i = 1; i < idx[n].size(); ++i) {
    //                if (idx[n][i] == idx[n][i - 1] + 1) ++cnt;
    //                else {
    //                    cnt1 = idx[n][i] == idx[n][i - 1] + 2 ? cnt : 0;
    //                    cnt = 1;
    //                }
    //                mx = max(mx, cnt1 + cnt);
    //            }
    //            res = max(res, mx + (idx[n].size() > mx ? 1 : 0));
    //        }
    //        return res;
    //    }
}
