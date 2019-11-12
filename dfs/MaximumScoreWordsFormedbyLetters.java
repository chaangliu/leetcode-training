package dfs;

/**
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 * Example 1:
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 * Example 2:
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * Word "xxxz" only get a score of 25.
 * Example 3:
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 14
 * 1 <= words[i].length <= 15
 * 1 <= letters.length <= 100
 * letters[i].length == 1
 * score.length == 26
 * 0 <= score[i] <= 10
 * words[i], letters[i] contains only lower case English letters.
 * 20191111
 */
public class MaximumScoreWordsFormedbyLetters {
    /**
     * 题意：给你一个words数组，一个letters数组，以及每个letter的分值；问letters数组能拼出的words最多能得多少分。
     * 解法：words的长度只有14，所以可以枚举出所有的subsets，如果满足letters的条件，就计算得分。
     * 我的count的恢复现场写得比较复杂，没想到能AC。其实可以简化，如果不valid就不进入下一层dfs。
     */
    int res = 0;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] p = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            for (char c : w.toCharArray()) {
                p[i] += score[c - 'a'];//计算每个单词的字母的总分
            }
        }
        int[] count = new int[26];
        for (char c : letters) count[c - 'a']++;
        dfs(0, words, count, 0, p);
        return res;
    }

    public void dfs(int start, String[] words, int[] count, int sum, int[] p) {
        for (int i = start; i < words.length; i++) {
            boolean valid = true;
            for (char c : words[i].toCharArray()) {
                if (--count[c - 'a'] < 0) {
                    valid = false;
                }
            }
            if (!valid) {//恢复现场
                for (char c : words[i].toCharArray()) {
                    count[c - 'a']++;
                }
                continue;
            }
            res = Math.max(sum + p[i], res);
            System.out.println("p[i] is " + p[i] + " res is  " + res);
            dfs(i + 1, words, count, sum + p[i], p);
            for (char c : words[i].toCharArray()) {//恢复现场
                count[c - 'a']++;
            }
        }
    }

    /**
     * 讨论区解法，局部变量版subsets
     */
    public int maxScoreWords__(String[] words, char[] letters, int[] score) {
        if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null || score.length == 0)
            return 0;
        int[] count = new int[score.length];
        for (char ch : letters) {
            count[ch - 'a']++;
        }
        return backtrack(words, count, score, 0);
    }

    int backtrack(String[] words, int[] count, int[] score, int index) {
        int max = 0;
        for (int i = index; i < words.length; i++) {
            int res = 0;
            boolean isValid = true;
            for (char ch : words[i].toCharArray()) {
                count[ch - 'a']--;
                res += score[ch - 'a'];
                if (count[ch - 'a'] < 0) isValid = false;
            }
            if (isValid) {
                res += backtrack(words, count, score, i + 1);
                max = Math.max(res, max);
            }
            for (char ch : words[i].toCharArray()) {
                count[ch - 'a']++;
                res = 0;
            }
        }
        return max;
    }
}
