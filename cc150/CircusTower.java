package cc150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 * 提示：
 * height.length == weight.length <= 10000
 * 通过次数3,964提交次数15,684
 * 20200825
 */
public class CircusTower {
    /**
     * 题意：给你两个数组代表一些人的身高和体重，又高又重的人可以摆在人塔的下面。问叠罗汉的最大高度。
     * 解法：先按照其中一列升序排序，然后对另一列求LIS。其实就转换成了lc300那题LIS，用O(n log n)的复杂度解。
     * 值得注意的是在h相同的时候，w要降序排序，这样再搜索的时候，当前人的体重就更有可能替换到靠前的index。这也是贪心的思想。
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        if (n == 0) return 0;
        int[] dp = new int[n]; // dp[i]表示长度为i的LIS的结尾最小数字
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) list.add(new Pair(height[i], weight[i]));
        Collections.sort(list, (a, b) -> a.h == b.h ? b.w - a.w : a.h - b.h);
        int len = 0;
        for (Pair p : list) {
            int w = p.w;
            int lo = 0, hi = len;
            while (lo < hi) {
                int mid = (lo + hi) >> 1;
                if (dp[mid] >= w) hi = mid;
                else lo = mid + 1;
            }
            dp[lo] = w; // 找到第一个比当前元素大的数字的位置，替换它
            if (lo == len) len++;
        }
        return len;
    }
    static class Pair { int h, w;Pair (int h, int w) { this.h = h; this.w = w; }}
}
