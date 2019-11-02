package twopointers;

/**
 * There are N dominoes in a line, and we place each domino vertically upright.
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 * Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 * Return a string representing the final state.
 * Example 1:
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 * Example 2:
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Note:
 * 0 <= N <= 10^5
 * String dominoes contains only 'L', 'R' and '.'
 * 20191031
 */
public class PushDominoes {
    /**
     * 题意：给你一行多米诺骨牌，L，R，. 三种符号分别代表初始状态向左推，向右推和直立。求最后的状态。
     * 解法：two pointers。从左到右处理，每次i和j分别找到两个字母，处理[i,j]之间的dominoes;处理完就把i指向j（注意不是j+1）。
     * [i,j]中间四种情况：
     * L...L=>LLLLL
     * L...R=>L...R
     * R...L=>RRRLLL OR RRR.LLL
     * R...R=>RRRRR
     */
    public String pushDominoes(String d) {
        d = 'L' + d + 'R';//技巧，两个不影响结果的辅助字符
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 1; j < d.length(); j++) {
            if (d.charAt(j) == '.') continue;
            int dist = j - i - 1;
            char l = d.charAt(i), r = d.charAt(j);
            if (l == 'L' && r == 'L') {
                for (int k = 0; k <= dist; k++) {//j位置下一轮处理。
                    if (i == 0 && k == 0) continue;
                    res.append('L');
                }
            } else if (l == 'L' && r == 'R') {
                for (int k = i; k < j; k++) {
                    if (k > 0) res.append(d.charAt(k));
                }
            } else if (l == 'R' && r == 'L') {
                for (int k = 0; k <= dist / 2; k++) {
                    res.append('R');
                }
                if ((dist & 1) == 1) {
                    res.append('.');
                }
                for (int k = 0; k < dist / 2; k++) {
                    res.append('L');
                }
            } else if (l == 'R' && r == 'R') {
                for (int k = 0; k <= dist; k++) {
                    res.append('R');
                }
            }
            i = j;
        }
        return res.toString();
    }
}
