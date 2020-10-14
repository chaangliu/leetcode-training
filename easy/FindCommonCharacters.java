package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 20201014
 */
public class FindCommonCharacters {
    /**
     * 题意：找出字符串数组里面公共的字符，出现几次就要打印几次。
     * 解法：跟题目名字一样，用一个common数组来统计出现的最小次数。
     */
    public List<String> commonChars(String[] A) {
        int[] common = new int[26];
        for (int i = 0; i < 26; i++) common[i] = 100;
        for (String s : A) {
            int[] map = new int[26];
            for (char c : s.toCharArray()) {
                map[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                common[i] = Math.min(common[i], map[i]);
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < common[i]; k++) {
                res.add(((char) ('a' + i)) + "");// 已犯错误，i写成了common[i]
            }
        }
        return res;
    }
}
