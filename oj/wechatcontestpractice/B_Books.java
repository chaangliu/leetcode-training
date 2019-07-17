package oj.wechatcontestpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * DreamGrid went to the bookshop yesterday. There are n books in the bookshop in total. Because DreamGrid is very rich, he bought the books according to the strategy below:
 * <p>
 * Check the n books from the 1st one to the n-th one in order.
 * For each book being checked now, if DreamGrid has enough money (not less than the book price), he'll buy the book and his money will be reduced by the price of the book.
 * In case that his money is less than the price of the book being checked now, he will skip that book.
 * BaoBao is curious about how rich DreamGrid is. You are asked to tell him the maximum possible amount of money DreamGrid took before buying the books, which is a non-negative integer. All he knows are the prices of the n books and the number of books DreamGrid bought in total, indicated by m.
 * <p>
 * Input
 * There are multiple test cases. The first line of the input contains an integer T, indicating the number of test cases. For each test case:
 * <p>
 * The first line contains two integers n and m (1≤n≤10
 * ​5
 * ​​ , 0≤m≤n), indicating the number of books in the bookshop and the number of books DreamGrid bought in total.
 * <p>
 * The second line contains n non-negative integers a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,...,a
 * ​n
 * ​​  (0≤a
 * ​i
 * ​​ ≤10
 * ​9
 * ​​ ), where a
 * ​i
 * ​​  indicates the price of the i-th book checked by DreamGrid.
 * <p>
 * It's guaranteed that the sum of n in all test cases will not exceed 10
 * ​6
 * ​​ .
 * <p>
 * Output
 * For each test case output one line.
 * <p>
 * If it's impossible to buy m books for any initial number of money, output "Impossible" (without quotes).
 * <p>
 * If DreamGrid may take an infinite amount of money, output "Richman" (without quotes).
 * <p>
 * In other cases, output a non-negative integer, indicating the maximum number of money he may take.
 * <p>
 * Sample Input
 * 4
 * 4 2
 * 1 2 4 8
 * 4 0
 * 100 99 98 97
 * 2 2
 * 10000 10000
 * 5 3
 * 0 0 0 0 1
 * Sample Output
 * 6
 * 96
 * Richman
 * Impossible
 * 20190717
 */
public class B_Books {
    /**
     * 据说是去年青岛站icpc的签到题？
     * 在纸上画了画，感觉思路没错，给的case都能过，但是过不了PTA的第二个case，不知为何。
     * 借助这题熟悉一下读入挂吧，比Scanner快很多。(https://codeforces.com/contest/1148/submission/54917408)
     */
    static class TaskBooks {
        public void solve(InputReader in, PrintWriter out) {
            int cases = in.nextInt();
            for (int i = 0; i < cases; i++) {
                long shopSize = in.nextLong();//[1, 1e5]
                long bought = in.nextLong();//[0,shopSize]
                long zeroCnt = 0;
                ArrayList<Long> books = new ArrayList<>();
                for (int j = 0; j < shopSize; j++) {
                    long price = in.nextLong();
                    if (price == 0) zeroCnt++;
                    books.add(price);
                }
                if (zeroCnt > bought) {
                    out.println("Impossible");
                } else if (bought == shopSize) {
                    out.println("Richman");
                } else {
                    long remain = bought - zeroCnt;
                    int k = 0, res = 0;
                    for (; k < remain; k++) {
                        res += books.get(k);
                    }
                    long min = Long.MAX_VALUE;
                    for (; k < shopSize; k++) {
                        if (books.get(k) != 0)
                            min = Math.min(min, books.get(k));
                    }
                    out.println(res + min - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        //        TaskA solver = new TaskA();
        //        solver.solve(1, in, out);
        TaskBooks b = new TaskBooks();
        b.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
    //    static class TaskA {
    //        public void solve(int testNumber, InputReader in, PrintWriter out) {
    //            long a = in.nextLong();
    //            long b = in.nextLong();
    //            long c = in.nextLong();
    //            a += c;
    //            b += c;
    //            if (a > b + 1) a = b + 1;
    //            if (b > a + 1) b = a + 1;
    //            out.println(a + b);
    //        }
    //    }
}
