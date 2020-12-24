package greedy;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * - 每个孩子至少分配到 1 个糖果。
 * - 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * 20201224
 */
public class Candy {
    /**
     * 题意：给你一些打分，代表小朋友的表现，根据表现分糖果，需要满足两个规则：
     * - 每个孩子至少分配到 1 个糖果。
     * - 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 问最少需要多少糖果。
     * 解法：greedy，需要同时满足两个规则：
     * 左规则：当 A[i - 1] < A[i] 时，ii 号学生的糖果数量将比 i - 1 号孩子的糖果数量多。
     * 右规则：当 A[i] > A[i + 1] 时，ii 号学生的糖果数量将比 i + 1 号孩子的糖果数量多。
     * 以下摘自官方题解。
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    /**
     * 我的思路，找最小的数字，然后expand from center，但是这样的思路是不正确的，无法通过[1,2,87,87,87,2,1]
     */
    public int candy__WA(int[] ratings) {
        int min = Integer.MAX_VALUE, minIndex = -1, n = ratings.length;
        for (int i = 0; i < n; i++) {
            if (ratings[i] <= min) {
                min = ratings[i];
                minIndex = i;
            }
        }
        int res = 1, prev = 1;
        for (int i = minIndex - 1; i >= 0; i--) {
            if (ratings[i] == ratings[i + 1]) {
                prev = Math.max(1, prev - 1);
                res += prev;
                System.out.println("adding " + Math.max(1, prev - 1));
            } else if (ratings[i] > ratings[i + 1]){
                prev++;
                res += prev;
                System.out.println("adding " + prev);
            } else {
                prev = 1;
                res += prev;
                System.out.println("adding " + prev);
            }
        }
        prev = 1;
        for (int i = minIndex + 1; i < n; i++) {
            if (ratings[i] == ratings[i - 1]) {
                res += Math.max(1, prev - 1);
            } else if (ratings[i] > ratings[i - 1]){
                prev++;
                res += prev;
            } else {
                prev = 1;
                res += prev;
            }
        }
        return res;
    }
}
