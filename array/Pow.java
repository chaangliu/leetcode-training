package array;

/**
 * Implement pow(x, n).
 * Created by DrunkPiano on 2017/3/7.
 */

public class Pow {
    double res = 1;

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        //half的每个值对应一个return 不能正向理解
        double half = myPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        }
        if (n % 2 == 1) {
            return half * half * x;
        } else {
            return half * half / x;
        }
    }

    public static void main(String args[]) {
        Pow pow = new Pow();
        System.out.println(pow.myPow(2, 5));
    }
}
