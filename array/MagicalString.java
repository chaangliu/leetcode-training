package array;

/**
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * <p>
 * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
 * <p>
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * <p>
 * If we group the consecutive '1's and '2's in S, it will be:
 * <p>
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * <p>
 * and the occurrences of '1's or '2's in each group are:
 * <p>
 * 1 2	2 1 1 2 1 2 2 1 2 2 ......
 * <p>
 * You can see that the occurrence sequence above is the S itself.
 * <p>
 * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 * <p>
 * Note: N will not exceed 100,000.
 * <p>
 * Example 1:
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
 * <p>
 * 20190328
 */
public class MagicalString {


    /**
     * 用一个pointer指针指向应该loop的数量
     * 这种题没啥规律，就是要专注思考；不能断
     */
    public int magicalString(int n) {
        int res = 0;
        StringBuilder sb = new StringBuilder();
        int pointer = 0;
        boolean init = true;
        String cell = "1";
        while (sb.length() < n) {
            int count = sb.length() == 0 || sb.charAt(pointer) == '1' ? 1 : 2;
            if (init) {
                count += 1;
                init = false;
            }
            for (int i = 0; i < count - 1; i++) {
                sb.append(cell);
                if (cell.equals("1")) res++;
                if (sb.length() == n) break;
            }
            if (sb.length() == n) break;
            sb.append(sb.charAt(sb.length() - 1) == '2' ? "1" : "2");
            if (sb.charAt(sb.length() - 1) == '1') res++;
            pointer++;
            cell = cell.equals("1") ? "2" : "1";
        }
        return res;
    }
}
