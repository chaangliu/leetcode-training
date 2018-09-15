package array;

/**
 * Created by DrunkPiano on 2017/5/1.
 */

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int temp = x;
        if (x < 0 || x != 0 && x % 10 == 0) return false;
        int rev = 0;
        while (temp > 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        return rev == temp;
    }

//    public boolean isPalindrome(int x) {
//        if (x < 0 || x != 0 && x % 10 == 0) return false;
//        int rev = 0;
//        while (x > rev) {
//            rev = rev * 10 + x % 10;
//            x /= 10;
//        }
//        return x == rev || x == rev / 10;
//    }
}
