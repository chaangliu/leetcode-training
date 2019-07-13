package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 Example 1:
 Input: "leetcodeisacommunityforcoders"
 Output: "ltcdscmmntyfrcdrs"
 Example 2:

 Input: "aeiou"
 Output: ""
 Note:
 S consists of lowercase English letters only.
 1 <= S.length <= 1000
 20190714
 【0分easy题】
 */
public class RemoveVowelsfromaString {
    public String removeVowels(String S) {
        StringBuilder res = new StringBuilder();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for (char c : S.toCharArray()){
            if (!set.contains(c)) res.append(c);
        }
        return res.toString();
    }
}
