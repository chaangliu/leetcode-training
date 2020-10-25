package easy;

/**
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 20201021
 */
public class IsLongPressedName {
    /**
     * 题意：有个人按键盘同一个字母可能多按，问他打出的名字是否和目标匹配。
     * 解法：two pointers。我还用了两个变量保存重复的字母的个数；官方题解是直接判断j是否超额输入。
     */
    public boolean isLongPressedName(String name, String typed) {
        int p = 0, q = 0, m = name.length(), n = typed.length();
        while (p < m && q < n) {
            // System.out.println("p, q : " + p + ", " + q);
            char c = name.charAt(p);
            if (typed.charAt(q) != c) return false;
            int min = 0;
            while (p < m && name.charAt(p) == c) {
                p++;
                min++;
            }
            int cnt = 0;
            while (q < n && typed.charAt(q) == c) {
                q++;
                cnt++;
            }
            if (min > cnt) return false;
        }
        return p == m && q == n;
    }

    public boolean isLongPressedName_official(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
