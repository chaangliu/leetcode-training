package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
 * <p>
 * For each such occurrence, add "third" to the answer, and return the answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 * Example 2:
 * <p>
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= text.length <= 1000
 * text consists of space separated words, where each word consists of lowercase English letters.
 * 1 <= first.length, second.length <= 10
 * first and second consist of lowercase English letters.
 * <p>
 * 20190609
 */
public class OccurrencesAfterBigram {
    /**
     * took: 24min 出现了忘记写i++之类的错误。感觉easy题唯一的作用就是思维严谨性和手速
     */
    public String[] findOcurrences(String text, String first, String second) {
        String pattern = first + " " + second;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = first.charAt(0);
            if (text.charAt(i) == c) {
                boolean match = true;
                int j = 1;
                while (j < pattern.length() && i + j < text.length()) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                    j = j + 1;
                }
                if (match) {
                    if (i + j + 1 < text.length()) {
                        int k = i + j + 1;
                        StringBuilder sb = new StringBuilder();
                        while (k < text.length() && text.charAt(k) != ' ') {
                            sb.append(text.charAt(k));
                            k = k + 1;
                        }
                        res.add(sb.toString());
                    }
                }
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
