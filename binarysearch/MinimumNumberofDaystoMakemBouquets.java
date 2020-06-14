package binarysearch;

/**
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * 示例 1：
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 20200614
 */
public class MinimumNumberofDaystoMakemBouquets {
    /**
     * 题意：给你一些花朵成熟的时间，问能集齐m束花，每束k朵的最早时间。
     * 解法：binary search
     */
    public int minDays(int[] A, int m, int k) {
        if (A.length < m * k) return -1;
        int l = 0, r = 0;
        for (int i : A) r = Math.max(r, i);
        while (l < r) {
            int mid = l + (r - l) / 2;
            boolean flag = valid(A, mid, m, k);
            // if (flag) System.out.println("VALID MID: " + mid); else System.out.println("INVALID MID: " + mid);
            if (flag) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private boolean valid(int[] A, int mid, int m, int k) {
        int cur = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > mid) {
                cur = 0;
                continue;
            }
            if (++cur == k) {
                cur = 0;
                if (--m == 0) return true;
            }
        }
        return false;
    }
}
