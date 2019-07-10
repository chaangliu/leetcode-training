package binarysearch.templateii;

/**
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
 * <p>
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 * <p>
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 * 20190710
 */
public class FindSmallestLetterGreaterThanTarget {
    /**
     * my code
     * 做这题我发现折半的时候是mid还是mid+1或mid-1是没有规律的（应该是），要根据具体题目情况代入看看。
     * 比如这题，letters = ["c", "f", "j"]，target = "c"；
     * 第一轮肯定不能mid - 1，因为我们要求的是右边一位。
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        if (letters[0] > target) return letters[0];
        if (letters[letters.length - 1] <= target) return letters[0];//wrap around
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (letters[mid] > target) {
                r = mid;
            } else {//letters[mid] = target时 mid不是我们想要的
                l = mid + 1;
            }
        }
        return letters[l];
    }

    /**
     * Solution里@awice的解法，初始化high为letters.length了，跟模板ii一样；
     * 但是这里他这么做似乎是因为他想把最后一位的后面一位（wrap around的第0位）囊括进去。
     */
    public char nextGreatestLetter_(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target) lo = mi + 1;
            else hi = mi;
        }
        return letters[lo % letters.length];
    }
}
