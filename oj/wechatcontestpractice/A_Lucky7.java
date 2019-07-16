package oj.wechatcontestpractice;

import java.util.Scanner;

/**
 * A Lucky 7
 * BaoBao has just found a positive integer sequence a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,...,a
 * ​n
 * ​​  of length n from his left pocket and another positive integer b from his right pocket. As number 7 is BaoBao's favorite number, he considers a positive integer x lucky if x is divisible by 7. He now wants to select an integer a
 * ​k
 * ​​  from the sequence such that (a
 * ​k
 * ​​ +b) is lucky. Please tell him if it is possible.
 * <p>
 * Input
 * There are multiple test cases. The first line of the input is an integer T (about 100), indicating the number of test cases. For each test case:
 * <p>
 * The first line contains two integers n and b (1≤n,b≤100), indicating the length of the sequence and the positive integer in BaoBao's right pocket.
 * <p>
 * The second line contains n positive integers a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,...,a
 * ​n
 * ​​  (1≤a
 * ​i
 * ​​ ≤100), indicating the sequence.
 * <p>
 * Output
 * For each test case output one line. If there exists an integer a
 * ​k
 * ​​  such that a
 * ​k
 * ​​ ∈{a
 * ​1
 * ​​ ,a
 * ​2
 * ​​ ,...,a
 * ​n
 * ​​ } and (a
 * ​k
 * ​​ +b) is lucky, output "Yes" (without quotes), otherwise output "No" (without quotes).
 * <p>
 * Sample Input
 * 4
 * 3 7
 * 4 5 6
 * 3 7
 * 4 7 6
 * 5 2
 * 2 5 2 5 2
 * 4 26
 * 100 1 2 4
 * Sample Output
 * No
 * Yes
 * Yes
 * Yes
 * Hint
 * For the first sample test case, as 4 + 7 = 11, 5 + 7 = 12 and 6 + 7 = 13 are all not divisible by 7, the answer is "No".
 * <p>
 * For the second sample test case, BaoBao can select a 7 from the sequence to get 7 + 7 = 14. As 14 is divisible by 7, the answer is "Yes".
 * <p>
 * For the third sample test case, BaoBao can select a 5 from the sequence to get 5 + 2 = 7. As 7 is divisible by 7, the answer is "Yes".
 * <p>
 * For the fourth sample test case, BaoBao can select a 100 from the sequence to get 100 + 26 = 126. As 126 is divisible by 7, the answer is "Yes".
 * 20190716
 */
public class A_Lucky7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = -1;
        while (in.hasNext()) {
            if (cases == -1)
                cases = in.nextInt();
            in.nextLine();
            for (int i = 0; i < cases; i++) {
                String line1 = in.nextLine();
                String[] arr1 = line1.split(" ");
                int leftPocketSize = Integer.parseInt(arr1[0]);
                int r = Integer.parseInt(arr1[1]);
                String line2 = in.nextLine();
                String[] arr2 = line2.split(" ");
                boolean found = false;
                for (int j = 0; j < arr2.length; j++) {
                    if ((Integer.parseInt(arr2[j]) + r) % 7 == 0) {
                        found = true;
                        break;
                    }
                }
                System.out.println(found ? "Yes" : "No");
            }
        }
    }
}
