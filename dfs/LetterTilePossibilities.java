package dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 * Example 2:
 * <p>
 * Input: "AAABBC"
 * Output: 188
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 * <p>
 * 20190609
 */
public class LetterTilePossibilities {
    /**
     * 模仿combinationsII的，没想到没有tle
     */
    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        char arr[] = tiles.toCharArray();
        Arrays.sort(arr);
        dfs(arr, new boolean[arr.length], new StringBuilder(), set);
        return set.size() - 1;
    }

    private void dfs(char arr[], boolean[] used, StringBuilder sb, Set<String> set) {
        set.add(sb.toString());
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) continue;
            if (!used[i]) {
                used[i] = true;
                sb.append(arr[i]);
                dfs(arr, used, sb, set);
                sb.deleteCharAt(sb.length() - 1);
                used[i] = false;
            }
        }
    }

}
