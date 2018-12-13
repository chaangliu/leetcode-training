package jianzhioffer;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class SumFromOneToN {

    ///常规递归解法，用了条件判断
    public int Sum_Solution_MINE(int n) {
        return n == 1 ? 1 : n + Sum_Solution(n - 1);
    }

    ///网上的解法。一开始看了一头雾水不知道为什么赋值给一个ans又不用，后来知道了它为了利用 && 左边的条件来替代if来终止递归。没啥意思。这题看了下书上，本意是用一些c++特性来求解。
    public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n > 0) && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }

    public static void main(String args[]) {
        System.out.println(new SumFromOneToN().Sum_Solution(4));
    }
}
