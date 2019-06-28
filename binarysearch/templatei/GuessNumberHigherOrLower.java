package binarysearch.templatei;

/**
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * <p>
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example :
 * Input: n = 10, pick = 6
 * Output: 6
 * 20190628
 */
public class GuessNumberHigherOrLower {
    /**
     * binary search，模板1
     */
    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int g = guess(mid);
            if (g == 0) return mid;
            else if (g == -1) r = mid - 1;
            else l = mid + 1;
        }
        return 999;
    }

    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
       */
    int guess(int num) {
        return 2;
    }
}
