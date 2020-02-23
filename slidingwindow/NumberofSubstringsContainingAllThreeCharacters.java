package slidingwindow;

/**
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 * Example 1:
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 * Example 2:
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 * Example 3:
 * Input: s = "abc"
 * Output: 1
 * Constraints:
 * 3 <= s.length <= 5 x 10^4
 * s only consists of a, b or c characters.
 * 20200223
 */
public class NumberofSubstringsContainingAllThreeCharacters {
    /**
     * 题意：求string中完全包含abc的substring的个数。
     * 解法：我比赛的时候直接调了subArrayCountOfNoMoreThanNDistinctNumbers那题代码就A了:
     * return subArrayCountOfNoMoreThanNDistinctNumbers(A, 3) - subArrayCountOfNoMoreThanNDistinctNumbers(A, 2);
     */
    public int numberOfSubstrings(String s) {
        int count[] = {0, 0, 0}, res = 0, i = 0, n = s.length();
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0)
                --count[s.charAt(i++) - 'a'];
            res += i;// 对于aaabc, res只会加一次，就是i = 3时,说明[i,j]的部分和[0,i)能构成abc
        }
        return res;
    }

    public static void main(String[] args) {
        new NumberofSubstringsContainingAllThreeCharacters().numberOfSubstrings("aaabc");
    }
}
