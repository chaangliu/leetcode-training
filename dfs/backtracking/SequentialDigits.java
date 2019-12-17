package dfs.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * Example 1:
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 * Constraints:
 * 10 <= low <= high <= 10^9
 * 20191217
 */
public class SequentialDigits {
    /**
     * 题意：在[low,high]内找出所有的顺序数字。
     * 我用了类似math的方法？迭代地去做，每次加1111..，反正非常麻烦，而且WA了很多次，用了非常久。
     * 其实这题可以打表，int最大是10^9数量级，2147483647，最后枚举出来一共也没多少符合条件的。
     * 其实这题我一开始觉得应该用BFS，就像是open the lock那题。但是后来我没想清楚该把什么加入到q里面去，把low加进去显然不行；
     * 那么把>=low的第一个满足的数字加进去？那样似乎每次q中都只有1个数，还不如直接iterate，然后我就写了一万行代码。。
     * 其实这题仍然可以BFS，因为你可以把12,23..89放到一层，这样你就不用处理89->123进位这种麻烦的事情了，因为123可以由12生成。
     * 方法1.我的一万行解法，math
     */
    public List<Integer> sequentialDigits(int low, int high) {
        String tmp = low + "";
        int len = tmp.length();//4
        int LEN = len;
        int first = tmp.charAt(0) - '0';//1
        List<Integer> res = new ArrayList<>();
        int num = 0;
        if (first + len > 10) {
            LEN++;
            first = 1;
        }
        for (len = LEN; len > 0; len--, first++) {//1234
            num += first * (int) Math.pow(10, len - 1);
        }
        int add = 0;
        for (int i = 0; i < LEN; i++) {//1111
            add += (int) Math.pow(10, i);
        }
        while (num <= high) {
            if (num >= low)
                res.add(num);//6789 + 1111
            boolean flag = true;
            if (num % 10 == 9) {
                LEN++;
                int NUM = 0;
                for (int i = 1; i <= LEN; i++) {
                    NUM += (LEN - i + 1) * (int) Math.pow(10, i - 1);
                }
                num = NUM;
                add = add * 10 + 1;//11111
                flag = false;
            }
            if (flag) num += add;
        }
        return res;
    }

    /**
     * 方法2. 打表
     */
    public List<Integer> sequentialDigits_(int low, int high) {
        int[] allNums = {12, 23, 34, 45, 56, 67, 78, 89,
                123, 234, 345, 456, 567, 678, 789,
                1234, 2345, 3456, 4567, 5678, 6789,
                12345, 23456, 34567, 45678, 56789,
                123456, 234567, 345678, 456789,
                1234567, 2345678, 3456789,
                12345678, 23456789,
                123456789};
        List<Integer> res = new ArrayList<>();
        int n = allNums.length;
        for (int i = 0; i < n; i++) {
            if (allNums[i] < low) continue;
            if (allNums[i] > high) break;
            res.add(allNums[i]);
        }
        return res;
    }

    /**
     * 方法3. BFS，非常好的解法
     */
    public List<Integer> sequentialDigits__BFS(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        if (low <= 0 && high >= 0) ans.add(0);
        for (int i = 1; i < 10; i++) q.add(i);
        while (q.size() > 0) {
            int curr = q.remove();
            if (curr >= low && curr <= high) ans.add(curr);
            int onesDigit = curr % 10;
            if (onesDigit < 9 && curr * 10 + onesDigit + 1 <= high) q.add(curr * 10 + onesDigit + 1);
        }
        return ans;
    }

    /**
     * 方法4. backtracking
     */
    public List<Integer> sequentialDigits__backtracking(int low, int high) {
        int lowDigitCount = (int) Math.floor(Math.log10(low) + 1);   // Number of digits in low
        int highDigitCount = (int) Math.floor(Math.log10(high) + 1); // Number of digits in high
        List<Integer> result = new ArrayList<>();
        for (int i = lowDigitCount; i <= highDigitCount; i++) {
            util(new StringBuilder(), result, 1, i, 0, low, high);
        }
        return result;
    }

    private void util(StringBuilder sb, List<Integer> list, int start, int count, int index, int low, int high) {
        if (index == count) {
            Integer x = Integer.parseInt(sb.toString());
            if (x >= low && x <= high) {
                list.add(x);
            }
            return;
        }
        if (start > 9) {
            return;
        }
        if (index == 0) {
            for (int i = start; i < 9; i++) {
                sb.append(i);
                util(sb, list, i + 1, count, index + 1, low, high);
                sb.deleteCharAt(index);
            }
        } else {
            sb.append(start);
            util(sb, list, start + 1, count, index + 1, low, high);
            sb.deleteCharAt(index);
        }
    }

    /**
     * 方法5. rock的普通迭代做法；以及WNJXYK的枚举做法，我感觉都需要对数字比较敏感
     */
    public List<Integer> sequentialDigits__ROCK(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int digit = 1; digit < 9; ++digit) {
            int next = digit, n = next;
            while (n <= high && next < 10) {
                if (n >= low) {
                    res.add(n);
                }
                int n1 = n * 10 + ++next;
                if (n1 > n) {
                    n = n1;
                } else { // Integer overflow.
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
