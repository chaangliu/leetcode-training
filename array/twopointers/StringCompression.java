package array.twopointers;

/**
 * Given an array of characters, compress it in-place.
 * <p>
 * The length after compression must always be smaller than or equal to the original array.
 * <p>
 * Every element of the array should be a character (not int) of length 1.
 * <p>
 * After you are done modifying the input array in-place, return the new length of the array.
 * <p>
 * <p>
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["a","a","b","b","c","c","c"]
 * <p>
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * <p>
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * Example 2:
 * <p>
 * Input:
 * ["a"]
 * <p>
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * <p>
 * Explanation:
 * Nothing is replaced.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * <p>
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * <p>
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * <p>
 * <p>
 * Note:
 * <p>
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 * <p>
 * 20190523
 */
public class StringCompression {
    /**
     * 题目要求in place，in place是指不借助辅助数据结构。
     * 方法是two pointers, 一个铁头娃i在前面走，后面用一个pointer指向字母和数量
     */
    public int compress(char[] chars) {
        int pointer = 0, i = 0;
        while (i < chars.length) {
            char cur = chars[i], count = 0;
            while (i < chars.length && chars[i] == cur) {
                i++;//已犯错误1：这个不能放在while语句判断条件里
                count++;
            }
            chars[pointer++] = cur;
            if (count != 1) {
                for (char c : Integer.toString(count).toCharArray()) {//关键
                    chars[pointer++] = c;
                }
            }
        }
        return pointer;
    }
}
