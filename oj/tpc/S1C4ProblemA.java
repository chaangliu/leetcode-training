package oj.tpc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题意: 一个N * N的矩阵，只能交换两个数字的位置，问行、列、对角线最大和是多少。
 * 真滴不太适应这种比赛..WA看不到case，最后灵光一现才发现是int越界问题。
 * 20200603
 */
public class S1C4ProblemA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long cases = in.nextInt();
        for (int c = 0; c < cases; c++) {
            int n = in.nextInt();
            long[][] A = new long[n][n];
            long sum = 0, diaSum = 0, onMin = (long) (1e9), offMax = 0, totalMax = 0, mid = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = in.nextInt();
                    if (isOnDiagonal(i, j, n)) {
                        diaSum += A[i][j];
                        onMin = Math.min(onMin, A[i][j]);
                    } else {
                        offMax = Math.max(offMax, A[i][j]);
                    }
                    totalMax = Math.max(totalMax, A[i][j]);
                    sum += A[i][j];
                    if (n % 2 == 1 && i == n / 2 && j == n / 2) {
                        mid = A[i][j];
                    }
                }
            }
            ArrayList<long[]> maxes = new ArrayList<>();
            for (int i = 0; i < n; i++)// 统计全局最大的数的位置
                for (int j = 0; j < n; j++) {
                    if (totalMax == A[i][j]) {
                        maxes.add(new long[]{i, j});
                    }
                }
            long res = sum * 2 + diaSum + (n % 2 == 1 ? A[n / 2][n / 2] : 0);
            if (mid == -1) { // n 是偶数
                res += Math.max(0, offMax - onMin);
            } else {
                long m = A[n / 2][n / 2];
                // 至少一个全局最大的数不在对角线上
                boolean flag = false;
                for (int i = 0; i < maxes.size(); i++) {
                    if (!isOnDiagonal(maxes.get(i)[0], maxes.get(i)[1], n)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    // 两种情况，把对角线外的全局最大数 1. 换到中间， 2. 换到对角线上的最小数上
                    long r1 = res + 2 * Math.max(offMax - m, 0);
                    long r2 = res + Math.max(offMax - onMin, 0);
                    res = Math.max(res, Math.max(r1, r2));
                } else {
                    // 三种情况，1. 把全局最大的数换到中间， 2. 把对角线外的最大数换到中间 3. 对角线上最小数和对角线外最大数互换
                    long r1 = res + Math.max(0, totalMax - m);
                    long r2 = res + 2 * Math.max(0, offMax - m);
                    long r3 = res + Math.max(0, offMax - onMin);
                    long r4 = res + Math.max(offMax - onMin, 0);
                    long r5 = res + 2 * Math.max(0, offMax - m);
                    res = Math.max(res, Math.max(r1, Math.max(r2, r3)));
                    res = Math.max(r4, res);
                    res = Math.max(r5, res);
                }
            }
            System.out.println(res);
        }
    }

    private static boolean isOnDiagonal(long i, long j, long n) {
        return i == j || i + j == n - 1;
    }
}
