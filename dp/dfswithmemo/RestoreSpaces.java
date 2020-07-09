package dp.dfswithmemo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 * <p>
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 */
public class RestoreSpaces {
    /**
     * 题意：给以一个句子，空格被去掉了，再给你一个字典，问字典组成句子里的单词的话，最少需要修改多少个字母。
     * 我的思路：这题用DFS加缓存的做法很典型，dfs返回从start开始的最少修改的字符数。
     * 另外看到官方题解还用了Trie。
     */
    public int respace(String[] dictionary, String sentence) {
        HashSet<Integer> lens = new HashSet<>();
        HashSet<String> dict = new HashSet<>();
        for (String s : dictionary) {
            lens.add(s.length());
            dict.add(s);
        }
        return dfs(0, sentence, lens, dict, new HashMap<>());
    }

    /**
     * 返回从`start`开始的最少修改的字符数。
     **/
    private int dfs(int start, String sentence, HashSet<Integer> lens, HashSet<String> dict, HashMap<Integer, Integer> memo) {
        if (memo.containsKey(start)) return memo.get(start);
        if (start >= sentence.length()) return 0;
        int res = Integer.MAX_VALUE;
        for (int len : lens) { // 枚举词典中单词的长度
            if (start + len <= sentence.length() && dict.contains(sentence.substring(start, start + len))) {
                res = Math.min(res, dfs(start + len, sentence, lens, dict, memo));
            }
        }
        res = Math.min(res, 1 + dfs(start + 1, sentence, lens, dict, memo)); // 从start位置开始未匹配到单词，或者匹配到了但是不使用当前单词
        memo.put(start, res);
        return res;
    }
}
