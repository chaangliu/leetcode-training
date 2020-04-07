package greedy;

import java.util.Arrays;

/**
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
 * <p>
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 * <p>
 * s is happy and longest possible.
 * s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
 * s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 * Example 1:
 * <p>
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 * <p>
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 * Example 3:
 * <p>
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * 20200407
 */
public class LongestHappyString {
    /**
     * 题意：给你a,b,c三种字母的个数，让你组成最长的happy string，也就是不能用三个相同字母相连的string。
     * 这种题看起来没啥套路，只能现场去构造。这里的思路是，优先用2个最多的，和1个第二多的。但是如果最多的-2已经<第二多的了，我们就不放第二多的了，下一轮直接放两个第二多的。
     * 群友说，这种类型题不适合做面试，「因为是贪心题，贪心题很容易变成知道了就知道，不知道就不知道，然后无法考查一个人的综合素质了。」
     */
    public String longestDiverseString(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }

    String generate(int a, int b, int c, String aa, String bb, String cc) {
        if (a < b)
            return generate(b, a, c, bb, aa, cc);
        if (b < c)
            return generate(a, c, b, aa, cc, bb);
        if (b == 0)
            return repeat(aa, Math.min(2, a));
        int a_times = Math.min(a, 2);
        int b_times = a - 2 >= b ? 1 : 0;
        return repeat(aa, a_times) + repeat(bb, b_times) + generate(a - 2, b - b_times, c, aa, bb, cc);
    }

    private String repeat(String s, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(s);
        return sb.toString();
    }

    /**
     * 第二种方法，来自中文discuss
     */
    public String longestDiverseString_(int a, int b, int c) {
        MyChar[] myChars = new MyChar[]{
                new MyChar('a', a),
                new MyChar('b', b),
                new MyChar('c', c),
        };
        StringBuilder sb = new StringBuilder();
        while (true) {
            Arrays.sort(myChars);
            //先放最多的, 如果上个放的2个字符串和剩余个数最多的字符相同，则放置次多的字符

            if (sb.length() >= 2 &&
                    sb.charAt(sb.length() - 1) == myChars[2].ch &&
                    sb.charAt(sb.length() - 2) == myChars[2].ch) {
                if (myChars[1].count-- > 0) {
                    sb.append(myChars[1].ch);
                } else {
                    break;
                }

            } else {
                if (myChars[2].count-- > 0) {
                    sb.append(myChars[2].ch);
                } else {
                    break;
                }
            }

        }
        return sb.toString();
    }

    private class MyChar implements Comparable {
        char ch;
        int count;

        public MyChar(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            MyChar other = (MyChar) o;
            return this.count - other.count;
        }
    }
}
