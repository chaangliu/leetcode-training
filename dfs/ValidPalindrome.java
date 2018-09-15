package dfs;

/**
 * Created by DrunkPiano on 2017/3/16.
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() < 2) return true;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i<=s.length() - 1 && !isNumber(s.charAt(i)) && !isAlpha(s.charAt(i))) {
                i++;
            }

            while (j>=0 && !isNumber(s.charAt(j)) && !isAlpha(s.charAt(j))) {
                j--;
            }

            if (i >= j) return true;
            if (isSame(s.charAt(i), s.charAt(j))) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isNumber(char c) {
        //要用单引号
        if (c >= '0' && c <= '9') return true;
        else return false;
    }

    public boolean isAlpha(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') return true;
        else return false;
    }

    public boolean isSame(char a, char b) {
        if (isNumber(a) && isNumber(b)) {
            return a == b;
        } else if (Character.toLowerCase(a) == Character.toLowerCase(b)) return true;
        else return false;
    }
}
