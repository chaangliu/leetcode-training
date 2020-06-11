package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 20200610review
 */

public class WordLadder {
    /**
     * 题意：给你一个beginWord和endWord，以及一个wordlist, 每次只能变一个字母，转换过程中的中间单词必须是字典中的单词。问最少需要多少次可以从beginWord变到endWord。
     * 这题跟open the lock那题很像，但是我一开始没想到用bfs，而是想用两个循环把word对其他word的距离都求出来。
     * TAG是BFS，我一开始想，普通BFS目测是不行的，为什么，因为BFS需要每一层标记visited，那么如果当前层的某个词被标记了，那下一层就没法再变回来了，难道要backtrack?
     * 这个想法是错误的！因为A和B/C相邻的话，B,C的dist一定是大于1的。比如hit him wit。
     * 那么这题用普通BFS的问题就是会TLE。所以有两种策略，一种就是2-end BFS，也就是最快的方法；
     * 第二种是枚举len * 26种变换，这种看似慢，其实在数据量大的时候比在wordList中找要快；当然也比不上2-end bfs
     * <p>
     * open the lock那题可以用2-end bfs，这题也一样。2-end bfs，用两个set来替代queue（其实普通BFS也可以用SET，这里用SET是方便检测两端是否互相包含）。
     * 我看题解里有人在beginSet.size>endSet.size()才时候才switch，也算一种优化。
     * 双向BFS:
     */
    public int ladderLength_(String beginWord, String endWord, List<String> wordList) {
        boolean found = false;
        for (String s : wordList) {
            if (s.equals(endWord)) {
                found = true;
                break;
            }
        }
        if (!found) return 0;
        HashSet<String> visited = new HashSet<>();
        HashSet<String> begin = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        int res = 1;
        while (!begin.isEmpty() && !end.isEmpty()) {
            HashSet<String> temp = new HashSet<>();
            for (String word : begin) {//遍历set的简单方式
                visited.add(word);
                if (end.contains(word)) return res;
                getNextStepWords(visited, temp, word, wordList);
            }
            begin = end;
            end = temp;
            res++;
        }
        return 0;
    }

    private void getNextStepWords(HashSet<String> visited, HashSet<String> temp, String s, List<String> wordList) {
        for (String word : wordList) {
            int diff = 0;
            if (visited.contains(word)) continue;
            for (int i = 0; i < word.length(); i++) {
                if (s.charAt(i) != word.charAt(i)) diff++;
            }
            if (diff == 1) {
                temp.add((word));
            }
        }
    }

    /**
     * 单向BFS:
     * 思路是把wordList中的每个单词中的每个字母都试着替换成*，记录成一种模式，然后放到map里记录这种模式里所有的单词，然后一层层去匹配
     * 单向BFS的缺点是，数据量大的时候会延伸出一棵很大的树。所以双向BFS比较好。
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length(); // Since all words are of same length.
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        int level = 1; // 第一个单词也算链子的长度
        while (!Q.isEmpty()) {
            for (int sz = Q.size(); sz > 0; sz--) { // 标准答案没有用这种写法，而是用了Pair记录每个node的level；我试了一下我常用的写法（for循环一次把同一层的node都处理完），也能过
                String word = Q.remove();
                for (int i = 0; i < L; i++) {
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                        if (adjacentWord.equals(endWord)) {
                            return level + 1;
                        }
                        if (!visited.containsKey(adjacentWord)) {
                            visited.put(adjacentWord, true);
                            Q.add(adjacentWord);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String args[]) {
        new WordLadder().ladderLength_("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }
}
