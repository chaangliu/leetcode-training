package array;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 * <p>
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * Created by DrunkPiano on 2017/3/20.
 */

public class MultiplyStrings {
    /**
     * 题意:给两个字符串代表的数字，求大数相乘，最后也用字符串输出。不能用大数相乘。
     * 下面是排名第一的solution，画图拆解比较好理解。
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;//p1 p2代表当前i和j位相乘，得到的两位数结果落在的位数index
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : pos) if (!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }


    public String multiply_OLD(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) return null;
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') return "0";
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        //the max length
        int totalLen = num1.length() + num2.length();
        int[] digit = new int[totalLen];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                //已犯错误1 这里写成了= 而不是+=
                digit[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }

        //这里再一次更新digit数组的每一位
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < totalLen; i++) {
            int carry = digit[i] / 10;
            int mod = digit[i] % 10;
            if (i + 1 < totalLen) {
                //更新下一位digit
                digit[i + 1] = digit[i + 1] + carry;
            }
            res.insert(0, mod);
        }
//        for (int i = 0; i < totalLen; i++) {
//            int mod = digit[i] % 10;
//            int carry = digit[i] / 10;
//            if (i + 1 < totalLen) {
//                digit[i + 1] += carry;
//            }
//            res.insert(0, mod);
//        }
        //最后一位没进位的情况
        if (res.charAt(0) == '0' && res.length() > 1) {
            res.deleteCharAt(0);
        }
        return res.toString();
    }

    public static void main(String args[]) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("9", "99"));
    }
}
