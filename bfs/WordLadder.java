package bfs;

import java.util.HashSet;
import java.util.List;

public class WordLadder {
    /**
     * 这题跟open the lock那题很像，但是我一开始没想到用bfs，而是想用两个循环把word对其他word的距离都求出来。
     * 那题可以用2-end bfs，这题也一样。2-end bfs就是用两个set来替代queue。
     * 不过我看题解里有人在beginSet.size>endSet.size()才时候才switch，也算一种优化。
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
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
        int diff = 0;
        for (String word : wordList) {
            if (visited.contains(word)) continue;
            for (int i = 0; i < word.length(); i++) {
                if (s.charAt(i) != word.charAt(i)) diff++;
            }
            if (diff == 1) {
                temp.add((word));
            }
            diff = 0;
        }
    }
}
