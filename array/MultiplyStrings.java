package array;

/**
 * multiplier
 * Created by DrunkPiano on 2017/3/20.
 */

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
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
