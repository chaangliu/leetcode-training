package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * <p>
 * If possible, output any possible result.  If not possible, return the empty string.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 * <p>
 * Input: S = "aaab"
 * Output: ""
 * Note:
 * <p>
 * S will consist of lowercase letters and have length in range [1, 500].
 * <p>
 * https://leetcode.com/problems/reorganize-string/description/
 * https://www.jianshu.com/p/aff4f3db8572
 */
public class ReorganizeString {

    //#2 using PriorityQueue, tracking the remaining 2 most frequent numbers
    public String reorganizeString(String S) {
        if (S == null || S.length() <= 1) {
            return S;
        }
        int len = S.length();
        //attention: array size is 26! not len
        int[] counts = new int[26];
        for (int i = 0; i < S.length(); i++) {
            counts[S.charAt(i) - 'a']++;
            //attention 2: exit condition
            if (counts[S.charAt(i) - 'a'] > (len + 1) / 2) return "";
        }

        PriorityQueue<RichChar> priorityQueue = new PriorityQueue<>(len, new Comparator<RichChar>() {
            @Override
            public int compare(RichChar o1, RichChar o2) {
                return o2.count - o1.count;
            }
        });
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                priorityQueue.offer(new RichChar((char) (i + 'a'), counts[i]));
            }
        }

        //attention, a stringbuilder is used here
        StringBuilder res = new StringBuilder();
        while (priorityQueue.size() >= 2) {
            RichChar ch1 = priorityQueue.poll();
            RichChar ch2 = priorityQueue.poll();
            res.append(ch1.letter);
            res.append(ch2.letter);
            ch1.count--;
            ch2.count--;
            if (ch1.count > 0) priorityQueue.offer(ch1);
            if (ch2.count > 0) priorityQueue.offer(ch2);
        }
        if (priorityQueue.size() > 0) {
            res.append(priorityQueue.poll().letter);
        }
        return res.toString();
    }

    class RichChar {
        char letter;
        int count;

        RichChar(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }
    }

    public static void main(String args[]) {
        System.out.println(new ReorganizeString().reorganizeString("vvvlo"));
    }


    //#1
    public String reorganizeString0(String S) {
        int N = S.length();
        int[] counts = new int[26];
        //aabbc -> [200, 200, 100, 0, 0..]，保证个数多的在前面
        for (char c : S.toCharArray()) {
            counts[c - 'a'] += 100;
        }
        //aabbc -> [200, 201, 102, 3, 4..] 个数相同的情况下按照alphabet排列
        for (int i = 0; i < 26; ++i) {
            counts[i] += i;
        }
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code : counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N + 1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N)
                    t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }

}
