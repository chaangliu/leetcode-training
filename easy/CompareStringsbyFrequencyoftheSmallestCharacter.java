package easy;

import java.util.Arrays;

/**
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 * <p>
 * Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 * Example 2:
 * <p>
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] are English lowercase letters.
 * 20190825
 */
public class CompareStringsbyFrequencyoftheSmallestCharacter {
    /**
     * 因为是easy并且数据比较小所以用了brute force。优化可以用二分。
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] q = new int[queries.length];
        int[] w = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            q[i] = calcFreq(query);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            w[i] = calcFreq(word);
        }
        int[] res = new int[queries.length];
        int idx = 0;
        for (int i : q) {
            int cnt = 0;
            for (int j : w) {
                if (i < j) {
                    cnt++;
                }
            }
            res[idx++] = cnt;
        }
        return res;
    }

    private int calcFreq(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        int target = arr[0];
        int res = 0;
        for (char c : arr) {
            if (c == target) res++;
        }
        return res;
    }
}
