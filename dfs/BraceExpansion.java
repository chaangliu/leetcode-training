package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A string S represents a list of words.
 * Each letter in the word has 1 or more options. If there is one option, the
 * letter is represented as is. If there is more than one option, then curly
 * braces delimit the options. For example, "{a,b,c}" represents options ["a",
 * "b", "c"].
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf",
 * "cde", "cdf"].
 * Return all words that can be formed in this manner, in lexicographical order.
 * Example 1:
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 * Input: "abcd"
 * Output: ["abcd"]
 * Note:
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets
 * are different.
 * 20190615
 */
public class BraceExpansion {
  /**
   * 昨晚的biweekly contest的第三题，当时名字叫1087. Permutation of Letters
   * to Contest，现在改名了。
   */
  public String[] permute(String S) {
    List<String> res = new ArrayList<>();
    boolean closure = true;
    List<String> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (char c : S.toCharArray()) {
      if (c == ',')
        continue;
      if (c == '{') {
        closure = false;
        continue;
      }
      if (c == '}') {
        closure = true;
        list.add(sb.toString());
        sb = new StringBuilder();
        continue;
      }
      if (!closure) {
        sb.append(c);
        continue;
      }
      list.add(c + "");
    }
    dfs(res, list, "", 0);
    String[] result = new String[res.size()];
    for (int i = 0; i < res.size(); i++) {
      result[i] = res.get(i);
    }
    return result;
  }

  private void dfs(List<String> res, List<String> layer, String tmp, int depth) {
    if (depth >= layer.size()) {
      res.add(tmp);
      return;
    }
    String s = layer.get(depth);
    for (char c : s.toCharArray()) {
      dfs(res, layer, tmp + c, depth + 1);
    }
  }

  public static void main(String args[]) {
    new BraceExpansion().expand("{a,b}c{d,e}f");
  }

  // this is clearer
  public String[] expand(String S) {
    if (S == null || S.length() == 0) {
      return new String[0];
    }

    List<String> res = new ArrayList<String>();
    dfs(S, 0, new StringBuilder(), res);
    Collections.sort(res);
    return res.toArray(new String[0]);
  }

  private void dfs(String s, int i, StringBuilder sb, List<String> res) {
    if (i >= s.length()) {
      res.add(sb.toString());
      return;
    }
    if (s.charAt(i) == '{') {
      int j = i + 1;
      while (j < s.length() && s.charAt(j) != '}') {
        j++;
      }

      String[] candidates = s.substring(i + 1, j).split(",");
      for (String candidate : candidates) {
        sb.append(candidate);
        dfs(s, j + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
      }
    } else {
      sb.append(s.charAt(i));
      dfs(s, i + 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
