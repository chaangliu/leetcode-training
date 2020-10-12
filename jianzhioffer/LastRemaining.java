package jianzhioffer;

public class LastRemaining {
    /**
     * 题意：约瑟夫环；[0,n-1]n个数字，每次移除第m个，问最后剩下的是哪个。
     * 解法：top down很容易理解；第n次移除的是第n-1次移除的向右移动m个。
     **/
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        int t = lastRemaining(n - 1, m);
        return (t + m) % n;
    }

    /**
     * bottom up: 倒推，最后一个肯定是第0个数字；那么从倒二第二轮开始推。
     */
    public int lastRemaining_(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
