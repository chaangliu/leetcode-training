package binarysearch;

import java.util.Arrays;

/**
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * <p>
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * <p>
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * 示例 1：
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
 */
public class MagneticForceBetweenTwoBalls {
    /**
     * 题意：给你框子的position，和m个球，问这些球放在筐子里的最小距离的最大值（也就是球放得越分散越好）。
     * 解法：BinarySearch（n <= 10^5，所以nlogn不会超时。）。
     * 枚举最小距离，尝试着往筐里放，夹逼即可。类似题目：分巧克力，koko吃香蕉。
     * 这题关键是枚举的方法有点不容易想到。
     * 参考：https://leetcode-cn.com/problems/magnetic-force-between-two-balls/solution/er-fen-sou-su-45ms-by-geguanting/
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length, lo = 1, hi = position[n - 1], ans = 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (checkValid(position, mid, m)) {
                ans = mid; // 记录最后满足的值，然后继续夹逼
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    boolean checkValid(int[] position, int distance, int m) {
        int count = 1;
        int i = 0;
        for (int j = 1; j < position.length; j++) {
            if (position[j] - position[i] >= distance) { // 所有的间距都要>=distance，因为这是我们预设的最小距离。
                i = j;
                count++;
                if (count >= m) return true; // 如果能放到m个，就valid
            }
        }
        return false;
    }

    // 如果用lo < hi的条件，最后需要return lo - 1，因为lo退出的时候已经不满足了
    public int maxDistance_(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length, lo = 1, hi = position[n - 1];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!checkValid(position, mid, m)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1;
    }
}
