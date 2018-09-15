package array;

/**
 * Created by DrunkPiano on 2017/3/7.
 */

public class Sqrtx {
    public int mySqrt(int x) {
        long low = 0;
        long high = x;// or high = x/2 +1 ;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid <= x && x < (mid + 1) * (mid + 1)) {
                return (int) mid;
            } else if (mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String args[]) {
        System.out.println(Math.sqrt(10));
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
