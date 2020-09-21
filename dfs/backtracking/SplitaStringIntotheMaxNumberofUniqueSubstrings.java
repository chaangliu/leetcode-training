package dfs.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
 * 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
 * 注意：子字符串 是字符串中的一个连续字符序列。
 * 示例 1：
 * 输入：s = "ababccc"
 * 输出：5
 * 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。像 ['a', 'b', 'a', 'b', 'c', 'cc'] 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：2
 * 解释：一种最大拆分方法为 ['a', 'ba'] 。
 * 示例 3：
 * 输入：s = "aa"
 * 输出：1
 * 解释：无法进一步拆分字符串。
 * 提示：
 * 1 <= s.length <= 16
 * s 仅包含小写英文字母
 */
public class SplitaStringIntotheMaxNumberofUniqueSubstrings {
    /**
     * 题意：把一串字符串分割成若干个短字符串，要求不能有重复的；问最多分成几个短的。
     * 解法：dfs判断当前点是分割还是不分割。维护一个set记录已分割的字符串。
     */
    public int maxUniqueSplit(String s) {
        int n = s.length();
        dfs(s, 0, new HashSet<>());
        return res;
    }

    int res = 0;

    private void dfs(String s, int start, HashSet<String> set) {
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (!set.add(sub)) continue;
            res = Math.max(res, set.size());
            dfs(s, i, set);
            set.remove(sub);
        }
    }


    /**
     * 不用for循环的写法，dfs需要记录begin和end
     * https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings/solution/java-hui-su-by-l0o0uis/
     */
    class Solution {
        int max = 0;
        Set<String> set = new HashSet<>();

        public int maxUniqueSplit(String s) {
            dfs(0,0,1,s);
            return max;
        }

        public void dfs(int start, int end, int res, String s){
            //当end已经是最后一个字符，如果最后一个子字符串不重复，即找到一种切割方法
            if (end==s.length()-1){
                if (set.contains(s.substring(start,end+1))) {
                    return;
                }else {
                    max=Math.max(max, res);
                    return;
                }
            }
            String cur = s.substring(start,end+1);
            //选择在end位置后切
            if (!set.contains(cur)) {
                set.add(cur);
                dfs(end+1,end+1,res+1,s);
                set.remove(cur);
            }
            //end位置后不切
            dfs(start,end+1,res,s);
        }
    }
}
