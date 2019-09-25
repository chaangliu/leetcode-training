package other;

/**
 * 有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？
 * <p>
 * [此处有图， 是一个a + 1/(b + 1(c + ..))]的形式
 * <p>
 * 连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。
 * 输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
 * 示例 1：
 * <p>
 * 输入：cont = [3, 2, 0, 2]
 * 输出：[13, 4]
 * 解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。
 * 示例 2：
 * <p>
 * 输入：cont = [0, 0, 3]
 * 输出：[3, 1]
 * 解释：如果答案是整数，令分母为1即可。
 * 限制：
 * <p>
 * cont[i] >= 0
 * 1 <= cont的长度 <= 10
 * cont最后一个元素不等于0
 * 答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
 * 2019094，力扣秋季赛
 */
public class Fraction {
    /**
     * 这题我直接模拟了一下，脑子清楚就模拟出来了
     */
    public int[] fraction(int[] cont) {
        int len = cont.length;
        if (len == 1) return new int[]{cont[0], 1};
        int[] base = new int[]{1, cont[len - 1]};
        for (int i = len - 2; i >= 0; i--) {
            base = calc(cont[i], base);//[3,7]
        }

        int[] res = new int[2];
        res[0] = base[1];
        res[1] = base[0];
        return res;
    }

    private int[] calc(int a, int[] b) {
        int[] res = new int[2];
        res[0] = a * b[1];
        res[1] = b[1];
        res[0] += b[0];

        int tmp = res[0];
        res[0] = res[1];
        res[1] = tmp;
        return res;
    }
}
