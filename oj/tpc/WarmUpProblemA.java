package oj.tpc;

import java.util.ArrayList;
import java.util.List;
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
    //    public static void main(String[] args) {
    //        Scanner in = new Scanner(System.in);
    //        int cases = in.nextInt();
    //        for (int i = 0; i < cases; i++) {
    //            int len = in.nextInt();
    //            ArrayList<Integer> list = new ArrayList<>();
    //            for (int j = 0; j < len; j++) {
    //                list.add(in.nextInt());
    //            }
    //            Collections.sort(list);
    //            while (true) {
    //                int gap = list.get(list.size() - 1) - list.get(0);
    //                if (gap == 0) {
    //                    System.out.println(list.get(0));
    //                    break;
    //                }
    //                list.remove(0);
    //                list.remove(list.size() - 1);
    //                list.add(0, gap);
    //                list.add(gap);
    //                Collections.sort(list);
    //            }
    //        }
    //    }
    //    }

    /**
     * 网际协议第 6 版（IPv6）是网际协议（IP）的最新版本。该协议用以鉴别并定位网络上的计算机，并对网络流量进行路由。该协议使用 128 个二进制位代表一个网络地址，其设计目的是为了替换第 4 版协议。
     * <p>
     * IPv6 的 128 位地址可以分为 8 组，每组各 16 位。我们可以将每组二进制位写成 4 个十六进制数，各组之间用冒号（:）分隔。例如，2001:0db8:0000:0000:0000:ff00:0042:8329 就是一个 IPv6 地址。
     * <p>
     * 方便起见，一个 IPv6 地址可以按以下规则缩写为一个更加简短的表现形式：
     * <p>
     * 每一组十六进制数的前导零会被去除。例如，0042 将变为 42。
     * 连续多组十六进制表示的 0 会被一对双冒号（::）替换。请注意，一个地址中双冒号至多出现一次，否则该缩写对应的完整 IPv6 地址可能无法确定。
     * 以下是这些简写规则的使用范例：
     * <p>
     * 完整 IPv6 地址：2001:0db8:0000:0000:0000:ff00:0042:8329
     * 去除每组十六进制数中的前导零后：2001:db8:0:0:0:ff00:42:8329
     * 省略连续的十六进制零后：2001:db8::ff00:42:8329
     * 根据上述规则，回环地址 0000:0000:0000:0000:0000:0000:0000:0001 可以被简写为 ::1
     * <p>
     * 你的任务就是将一个缩写后的 IPv6 地址改写为一个完整的地址。
     * <p>
     * Input / 输入格式
     * There are multiple test cases. The first line of the input contains an integer T (about 1000), indicating the number of test cases. For each test case:
     * <p>
     * The first and only line contains a string s indicating a valid abbreviated IPv6 address. s only contains digits, lowercase letters and colons.
     * <p>
     * 有多组测试数据。第一行输入一个整数 T（约 1000）代表测试数据组数。对于每组测试数据：
     * <p>
     * 第一行输入一个字符串 s 代表一个合法的缩写后的 IPv6 地址，保证 s 只包含数字、小写字母和冒号。
     * <p>
     * Output / 输出格式
     * For each test case output one line containig one string, indicating the full representation of the IPv6 address.
     * <p>
     * 每组数据输出一行一个字符串，代表完整的 IPv6 地址。
     * <p>
     * Sample Input / 样例输入
     * 4
     * 7abc::00ff:fffc
     * fc:0:0:8976:0:0:0:ff
     * 2c0f:9981::
     * ::
     * ::1
     * <p>
     * Sample Output / 样例输出
     * 7abc:0000:0000:0000:0000:0000:00ff:fffc
     * 00fc:0000:0000:8976:0000:0000:0000:00ff
     * 2c0f:9981:0000:0000:0000:0000:0000:0000
     * 0000:0000:0000:0000:0000:0000:0000:0000
     * 0000:0000:0000:0000:0000:0000:0000:0001
     */
    //    public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int c = 0; c < cases; c++) {
            String v6 = in.next();
            StringBuilder sb = new StringBuilder();
            String[] arr = v6.split(":");
            if (arr.length == 0) {
                System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
            } else {
                int cnt = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        cnt++;
                        if (cnt > 1) {
                            List<String> list = new ArrayList<>();
                            for (String ar : arr) {
                                if (ar.equals("") && list.size() > 0 && list.get(list.size() - 1).equals("")) continue;
                                list.add(ar);
                            }
                            arr = new String[list.size()];
                            for (int t = 0; t < arr.length; t++) arr[t] = list.get(t);
                            break;
                        }
                    }
                }
                int i = 0;
                for (; i < arr.length; i++) {
                    if (arr[i].length() == 0) {
                        for (int k = 0; k < 8 - arr.length + 1; k++) {
                            sb.append("0000:");
                        }
                    } else {
                        String item = arr[i];
                        while (item.length() < 4) {
                            item = '0' + item;
                        }
                        sb.append(item).append(":");
                    }

                }
                while (sb.length() < 40) {
                    sb.append("0000:");
                }
                if (sb.charAt(sb.length() - 1) == ':') sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb.toString());
            }
        }
    }
    //    }
}
