package oj.tpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Given n integers, each time one can perform the following operation: select the maximum interger a and the minimum integer b among them, and then replace both a and b with (a−b).
 * <p>
 * Is it possible to make all integers equal after a finite number of operations? If yes, what's the final result?
 * <p>
 * 给定 n 个整数，每次可以进行如下操作：选择其中最大的整数 a 与最小的整数 b，并将它们都替换为 (a−b)。
 * <p>
 * 是否可以在有限次操作内使得所有整数都相等？如果是，最后的结果是什么？
 * <p>
 * Input / 输入格式
 * There are multiple test cases. The first line of the input contains an integer T (about 20) indicating the number of test cases. For each test case:
 * <p>
 * The first line contains an integer n (2≤n≤10) indicating the number of integers.
 * <p>
 * The second line contains n integers a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,⋯,a
 * ​n
 * ​​  (−10
 * ​5
 * ​​ ≤a
 * ​i
 * ​​ ≤10
 * ​5
 * ​​ ) indicating the given integers.
 * <p>
 * 有多组测试数据。第一行输入一个整数 T（约 20）代表测试数据组数。对于每组测试数据：
 * <p>
 * 第一行输入一个整数 n（2≤n≤10）代表给定整数的数量。
 * <p>
 * 第二行输入 n 个整数 a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,⋯,a
 * ​n
 * ​​ （−10
 * ​5
 * ​​ ≤a
 * ​i
 * ​​ ≤10
 * ​5
 * ​​ ）代表给定的整数。
 * <p>
 * Output / 输出格式
 * For each test case output one line. If it's possible to make all integers equal, output the integer they'll become; Otherwise output "Impossible" (without quotes).
 * <p>
 * 每组数据输出一行。若可以将所有整数变为相等，输出最终产生的那个整数；否则输出 "Impossible"（不输出引号）。
 * <p>
 * Sample Input / 样例输入
 * 2
 * 3
 * 1 2 3
 * 2
 * 5 5
 * <p>
 * Sample Output / 样例输出
 * 2
 * 5
 * 20200421
 */
public class WarmUpProblemA {
    /**
     * 题意：给你一串数字，让你每次找出最大和最小的两数的差，然后替换掉原来的数。问最后能否得到只剩下一个数。如果不能，输出Impossible
     * 解法: 其实不存在Impossible的情况。就模拟一下就好了，不需要用set替换掉所有最大最小数，一次次来就好。
     * 注意TPC的Java提交需要类名是Main，并且需要Imports。
     * 另外，签到题也就不要用读入挂了。。Scanner就挺好。
     * PTA上提交Java程序的一些套路：https://www.cnblogs.com/zhrb/p/6347738.html?utm_source=itdadao&utm_medium=referral
     */
    //    public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int len = in.nextInt();
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(in.nextInt());
            }
            Collections.sort(list);
            while (true) {
                int gap = list.get(list.size() - 1) - list.get(0);
                if (gap == 0) {
                    System.out.println(list.get(0));
                    break;
                }
                list.remove(0);
                list.remove(list.size() - 1);
                list.add(0, gap);
                list.add(gap);
                Collections.sort(list);
            }
        }
    }
    //    }
}
