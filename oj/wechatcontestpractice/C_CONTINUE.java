package oj.wechatcontestpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * DreamGrid has n classmates numbered from 1 to n. Some of them are boys and the others are girls. Each classmate has some gems, and more specifically, the i-th classmate has i gems.
 * <p>
 * DreamGrid would like to divide the classmates into four groups G
 * ​1
 * ​​ , G
 * ​2
 * ​​ , G
 * ​3
 * ​​  and G
 * ​4
 * ​​  such that:
 * <p>
 * Each classmate belongs to exactly one group.
 * Both G
 * ​1
 * ​​  and G
 * ​2
 * ​​  consist only of girls. Both G
 * ​3
 * ​​  and G
 * ​4
 * ​​  consist only of boys.
 * The total number of gems in G
 * ​1
 * ​​  and G
 * ​3
 * ​​  is equal to the total number of gems in G
 * ​2
 * ​​  and G
 * ​4
 * ​​ .
 * Your task is to help DreamGrid group his classmates so that the above conditions are satisfied. Note that you are allowed to leave some groups empty.
 * <p>
 * Input
 * There are multiple test cases. The first line of input is an integer T indicating the number of test cases. For each test case:
 * <p>
 * The first line contains an integer n (1≤n≤10
 * ​5
 * ​​ ) -- the number of classmates.
 * <p>
 * The second line contains a string s (∣s∣=n) consisting of 0 and 1. Let s
 * ​i
 * ​​  be the i-th character in the string s. If s
 * ​i
 * ​​ =1, the i-th classmate is a boy; If s
 * ​i
 * ​​ =0, the i-th classmate is a girl.
 * <p>
 * It is guaranteed that the sum of all n does not exceed 10
 * ​6
 * ​​ .
 * <p>
 * Output
 * For each test case, output a string consists only of {1, 2, 3, 4}. The i-th character in the string denotes the group which the i-th classmate belongs to. If there are multiple valid answers, you can print any of them; If there is no valid answer, output "-1" (without quotes) instead.
 * <p>
 * Sample Input
 * 5
 * 1
 * 1
 * 2
 * 10
 * 3
 * 101
 * 4
 * 0000
 * 7
 * 1101001
 * Sample Output
 * -1
 * -1
 * 314
 * 1221
 * 3413214
 * --------------------
 * created on 20190720
 */
public class C_CONTINUE {

    /**
     * 这题大意是有一个长度n字符串由0和1组成，代表n个同学，1表示男生0表示女生，第个同学持有i个宝石。
     * 要把他们分成4组，其中女生在1、2组，男生在3，4组，要求组里的宝石数量满足：1 + 3 = 2 + 4
     * 这题我一开始想用dfs，但是数据量很大肯定不行(而且也不好写..)
     * 群里有人说O(n)，是一道数学题；
     * 1 ~ n的和怎么才能均分成两组呢？
     * solve的思路（这个做法跟从后往前的做法分出的两组是一致的）：
     * 群里一个妹妹说的：「贪心点 解个方程 n(n+1) = 2m(m+1), 定位分界点，然后调换下分组就好」
     * solve2的思路：从后往前，
     * n + (n - 1) +..+ (n - k) > sum/2时break，把n - k换成[1,n - k)里的一个数
     * 怎么证明k前面一定有的数能组成sum/2?
     * 很容易，1~n-k我们是都有的，所以1~n-k中一定有个数满足 n + (n - 1) +..+ (n - m)=sum/2
     * -------
     * 但是我写出的这两种做法都没法通过测试点1
     * 数学题真的..不太好做。做了一下午。溜了溜了。
     */
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int cases = in.nextInt();
            for (int i = 0; i < cases; i++) {
                int len = in.nextInt();
                long total = (1 + len) * len / 2;
                String list = in.next();
                if ((total & 1) == 1) {//不能分成两组
                    out.println("-1");
                    continue;
                }
                StringBuilder res = new StringBuilder();
                long sum = 0;
                int j = 0;
                for (; j < len; j++) {
                    sum += j + 1;
                    int val = list.charAt(j) - '0';
                    res.append(val == 1 ? 3 : 1);
                    if (sum >= total / 2) break;
                }
                int pivot = (int) (sum - total / 2);
                if (pivot > 0) {
                    int num = list.charAt(pivot - 1) - '0';
                    res.deleteCharAt(pivot - 1);
                    res.insert(pivot - 1, num == 1 ? 4 : 2);
                }
                j++;
                while (j < len) {
                    int val = list.charAt(j) - '0';
                    res.append(val == 1 ? 4 : 2);
                    j++;
                }
                out.println(res);
            }
        }

        public void solve2(InputReader in, PrintWriter out) {
            int cases = in.nextInt();
            for (int i = 0; i < cases; i++) {
                int len = in.nextInt();
                int total = (1 + len) * len / 2;
                String list = in.next();
                if ((total & 1) == 1) {//不能分成两组
                    out.println("-1");
                    continue;
                }
                StringBuilder res = new StringBuilder();
                int sum = 0, k = len - 1;
                for (; k >= 0; k--) {
                    int val = list.charAt(k) - '0';
                    res.insert(0, val == 1 ? 4 : 2);
                    sum += k + 1;
                    if (sum + k > total / 2) break;
                }
                int remain = total / 2 - sum;
                StringBuilder res2 = new StringBuilder();
                for (int l = 0; l < k; l++) {
                    if (l + 1 == remain) {
                        res2.append(list.charAt(l) - '0' == 1 ? 4 : 2);
                    } else {
                        res2.append(list.charAt(l) - '0' == 1 ? 3 : 1);
                    }
                }
                out.println(res2.toString() + res.toString());
            }
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task b = new Task();
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

        public String next() {//nextLine()也可以用这个
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

/**
 * 附上C++做法, c++的cin和string真好用，比java理解起来难，但是操作起来舒服很多，
 * java的string是immutable的这点比较麻烦
 * 感觉c++跟其他所有面向对象语言都不太一样
 */
//#include <iostream>
//#include <string>
//#include <vector>
//
//    using namespace std;
//
//    int main() {
//        int cases = 0;
//        cin >> cases;
//        for (long long i = 0; i < cases; ++i) {
//            int n;
//            string str;
//            cin >> n >> str;
//            long long total = (n + 1) * n / 2;
//            if ((total % 2) == 1) {
//                cout << "-1" << endl;
//                continue;
//            }
//            string res = str;
//            for (char &ch : res) {
//                if (ch == '1') {
//                    ch = '4';
//                } else {
//                    ch = '2';
//                }
//            }
//            long long current = n;
//            vector<long long> index;
//            total /= 2;
//            while (total >= current) {
//                index.push_back(current);
//                total -= current;
//                --current;
//            }
//            //n + (n - 1) +..+ (n - k)>sum/2之后 怎么证明k前面一定有的数能组成sum/2?
//            // 很容易，1~n-k我们是都有的，所以1~n-k中一定有个数满足 n + (n - 1) +..+ (n - m)=sum/2
//            if (total > 0) {
//                index.push_back(total);
//            }
//            for (long long &ele : index) {
//                if (str[ele - 1] == '1') {
//                    res[ele - 1] = '3';
//                } else {
//                    res[ele - 1] = '1';
//                }
//            }
//            cout << res << endl;
//        }
//    }
}
