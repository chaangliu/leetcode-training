package design.random;

/**
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 * <p>
 * Do NOT use system's Math.random().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: [7]
 * Example 2:
 * <p>
 * Input: 2
 * Output: [8,4]
 * Example 3:
 * <p>
 * Input: 3
 * Output: [8,1,10]
 * <p>
 * <p>
 * Note:
 * <p>
 * rand7 is predefined.
 * Each testcase has one argument: n, the number of times that rand10 is called.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 * 20190902
 */
public class ImplementRand10UsingRand7 {
    /**
     * 这题是经典老题了，
     * 方法：拒绝取样(Rejection Sampling)
     * Rand_a2 = a * (Rand_a-1) + Rand_a  可生成1-a2的随机数。
     */
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;//区间长度为7
        } while (idx > 10);//如果大于10，就拒绝采样
        return idx;
    }

    /**
     * 改进1，缩小拒绝范围
     * 上面有大概率是超出10的，改进方法就是找10的倍数然后做mod操作。
     * 同样的，如果我们要利用rand5求rand7，那么有rand7 = rand25 % 7 + 1，这样可以找到最接近25的21
     * 类似资料：https://blog.csdn.net/gt362ll/article/details/82895591
     */
    public int rand10_() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return idx % 10 + 1;//注意，产生的是1 ~ 10而不是0 ~ 9
    }

    /**
     * 改进2，利用out of range samples
     */
    public int rand10__() {
        int a, b, idx;
        while (true) {
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;//1 ~ 49
            if (idx <= 40) return 1 + (idx - 1) % 10;//无需拒绝
            a = idx - 40;//41 ~ 49 => 1 ~ 9
            b = rand7();// 1 ~ 7
            idx = b + (a - 1) * 7;// get uniform dist from 1 - 63
            if (idx <= 60) return 1 + (idx - 1) % 10;
            a = idx - 60;// 1 ~ 3
            b = rand7();
            idx = b + (a - 1) * 7;// get uniform dist from 1 - 21
            if (idx <= 20) return 1 + (idx - 1) % 10;
            //这里由于idx - 20 只剩下1了，下一个uniform dist会在1 - 7之间所以我们不继续谢下去了；如果不是1，还可以继续写下去，因为每次只需要rand一次产生b
            //            a = idx - 20;
            //            b = rand7();
            //            idx = b + (a - 1) * 7;// get uniform dist from 1 - 7
        }
    }

    /**
     * 另一道题，头条的题；利用rand3（产生1~3的整数）, rand4, rand5，实现rand100
     */
    public int rand100() {
        int a = rand5();
        int b = rand5();
        int c = a + (b - 1) * 5;//[1, 25]
        return rand4() + (c - 1) * 4;
    }

    private int rand4() {
        return -1;
    }

    private int rand5() {
        return -1;
    }


    private int rand7() {
        double d = Math.random();//[0.0, 1.0)
        return (int) (d * 7) + 1;
    }

    /**
     * 另一个题(from: gui chen)：已知概率函数 f(n) 返回值 p概率返回1， 1-p概率返回0 (p != 0.5)，仅利用 f 实现一个g，返回值 0.5概率返回1，0.5概率返回0
     */
}
