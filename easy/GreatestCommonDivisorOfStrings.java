package easy;

/**
 * For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)
 * <p>
 * Return the largest string X such that X divides str1 and X divides str2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 * <p>
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 * <p>
 * Note:
 * <p>
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] and str2[i] are English uppercase letters.
 * <p>
 * 20190603
 */
public class GreatestCommonDivisorOfStrings {
    /**
     * 这题我的思路是把短的串逐渐变短，然后拼接，如果能满足给出的两个string就返回。复杂度O(n2)
     */
    public String gcdOfStrings(String str1, String str2) {
        String shortS, longS;
        if (str1.length() < str2.length()) {
            shortS = str1;
            longS = str2;
        } else {
            shortS = str2;
            longS = str1;
        }

        for (int i = shortS.length(); i > 0; i--) {
            String cell = shortS.substring(0, i);
            boolean validForShrot = false;
            int n = 1;
            while (i * n++ <= longS.length()) {//i代表cell的长度，n代表需要几个重复的cell
                String dup = "";
                for (int j = 0; j < n; j++) {
                    dup += cell;
                    if (dup.equals(shortS)) {//一开始没考虑到还要满足shortS，只想到了longS，WA了
                        validForShrot = true;
                    }
                    if (validForShrot && dup.equals(longS)) {
                        return cell;
                    }
                }
            }
        }
        return "";
    }

    /**
     * 摘抄一种递归做法，很独特
     * 隐含了一个规律：长的str一定startWith短的str
     */
    public String gcdOfStrings__RECURSIVE(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return gcdOfStrings__RECURSIVE(str2, str1);// make sure str1 is not shorter than str2.
        } else if (!str1.startsWith(str2)) {
            return "";// shorter string is not common prefix.
        } else if (str2.isEmpty()) {
            return str1;// gcd string found.
        } else {
            return gcdOfStrings__RECURSIVE(str1.substring(str2.length()), str2);// cut off the common prefix part of str1.
        }
    }
}
