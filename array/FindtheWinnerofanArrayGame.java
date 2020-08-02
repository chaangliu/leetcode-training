package array;

/**
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 * <p>
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 * <p>
 * 返回赢得比赛的整数。
 * <p>
 * 题目数据 保证 游戏存在赢家。
 * 示例 1：
 * 输入：arr = [2,1,3,5,4,6,7], k = 2
 * 输出：5
 */
public class FindtheWinnerofanArrayGame {
    /**
     * 题意：给你一个array，每次比较arr[0]和arr[1]的大小，大的放队首，小的放队尾。问最早连续赢得k次的数字。
     * 解法：我用的是arraylist remove add那样的模拟；看lee的解法是就遍历记录最大数，看哪个数最早撑过k轮。
     */
    public int getWinner(int[] A, int k) {
        int cur = A[0], win = 0;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > cur) {
                cur = A[i];
                win = 0;
            }
            if (++win == k) break;
        }
        return cur;
    }
}
