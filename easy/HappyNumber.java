package easy;

/**
 * 编写一个算法来判断一个数是不是“快乐数”。
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * 示例: 
 * 输入: 19
 * 输出: true
 * 解释:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 20200219
 */
public class HappyNumber {
    /**
     * 题意：如果一个数各位加起来最终等于1，就是happy number。判断一个数是否是happy number。
     * 这题tag是hash和math，基于一个预设：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。
     * 也就是说，这个number一定是一个环（不知道怎么证明），只是不一定收敛于1。所以可以用快慢指针或者hashset。
     */
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        do { //一定要先loop起来再while，比如case 19
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        }
        while (slow != fast);
        return slow == 1;
    }

    int squareSum(int num) {
        int res = 0;
        while (num != 0) {
            res += (num % 10) * (num % 10);
            num /= 10;
        }
        return res;
    }
}
