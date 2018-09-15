package array;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * <p>
 * Created by DrunkPiano on 2017/3/6.
 */

public class DivideTwoIntegers {
    public static void main(String args[]) {
        DivideTwoIntegers divideTwoIntegers = new DivideTwoIntegers();
//        System.out.println(divideTwoIntegers.divide(2147483647, 3));
        System.out.println((long)1 << 32);
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        //MIN_VALUE的绝对值比MAX_VALUE大1
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MAX_VALUE && divisor == 1) return dividend;
        int pDividend = Math.abs(dividend);
        int pDivisor = Math.abs(divisor);
        if (pDividend == pDivisor) {
            if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
                return 1;
            } else {
                return -1;
            }
        }


        int result = 0;
        while (pDividend >= pDivisor) {
            int numShift = 0;
//          while (pDividend >= (pDivisor << numShift)) {
            while (pDividend >= (pDivisor << numShift)) {
                numShift++;
            }

            //dividend minus the largest shifted divisor
            result = result + (1 << (numShift - 1));
            pDividend = pDividend - (pDivisor << (numShift - 1));
        }
        if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
            return result;
        } else {
            return 0 - result;
        }
    }
}

