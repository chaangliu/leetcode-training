package dfs.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an equation, represented by words on left side and the result on right side.
 * You need to check if the equation is solvable under the following rules:
 * Each character is decoded as one digit (0 - 9).
 * Every pair of different characters they must map to different digits.
 * Each words[i] and result are decoded as one number without leading zeros.
 * Sum of numbers on left side (words) will equal to the number on right side (result).
 * Return True if the equation is solvable otherwise return False.
 * Example 1:
 * Input: words = ["SEND","MORE"], result = "MONEY"
 * Output: true
 * Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
 * Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
 * Example 2:
 * <p>
 * Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
 * Output: true
 * Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
 * Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
 * Example 3:
 * <p>
 * Input: words = ["THIS","IS","TOO"], result = "FUNNY"
 * Output: true
 * Example 4:
 * <p>
 * Input: words = ["LEET","CODE"], result = "POINT"
 * Output: false
 * Constraints:
 * 2 <= words.length <= 5
 * 1 <= words[i].length, result.length <= 7
 * words[i], result contains only upper case English letters.
 * Number of different characters used on the expression is at most 10.
 * 20200114
 */
public class VerbalArithmeticPuzzle {
    /**
     * 题意：给你一些word和一个result，让你给每个字母分配一个数字，问两边加起来是否有可能相等。
     * 解法：暴力递归。dfs部分蛮值得学习的。对于当前index的char，枚举0-9
     * 另外，计算两边的sum的部分也很值得学习，把每一位赋予不同的power
     */
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

    public boolean isSolvable(String[] words, String result) {
        Set<Character> allCharsSet = new HashSet<>();
        Set<Character> noLeadingZeros = new HashSet<>();
        HashMap<Character, Integer> price = new HashMap<>(); // Char -> Price, all prices should add to 0
        for (String word : words) {
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (i == 0) noLeadingZeros.add(c);
                price.put(c, price.getOrDefault(c, 0) + POW_10[n - 1 - i]);
                allCharsSet.add(c);
            }
        }
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            if (i == 0) noLeadingZeros.add(c);
            price.put(c, price.getOrDefault(c, 0) - POW_10[result.length() - 1 - i]);
            allCharsSet.add(c);
        }
        List<Character> charList = new ArrayList<>(allCharsSet);
        return dfs(charList, noLeadingZeros, price, new boolean[10], 0, 0);
    }

    private boolean dfs(List<Character> allChars, Set<Character> nonLeadingZeros, HashMap<Character, Integer> price, boolean[] used, int sum, int index) {
        if (index == allChars.size()) return sum == 0;
        char c = allChars.get(index);
        for (int d = 0; d <= 9; d++) {
            if (d == 0 && nonLeadingZeros.contains(c)) continue;
            if (!used[d]) {
                used[d] = true;
                if (dfs(allChars, nonLeadingZeros, price, used, sum + price.get(c) * d, index + 1)) return true;
                used[d] = false;
            }
        }
        return false;
    }
}
