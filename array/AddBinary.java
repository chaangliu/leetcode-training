package array;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * For example, a = "11", b = "1", the return is "100".
 * <p>
 * Created by DrunkPiano on 2017/3/14.
 */

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aLen = a.length() - 1;
        int bLen = b.length() - 1;
        int carry = 0;
        int cur = 0;
        while (aLen >= 0 || bLen >= 0) {
            int temp;
            if (aLen >= 0) {
                //不能直接加上charAt。。那个是ascii码
                temp = a.charAt(aLen) == '0' ? 0 : 1;
                carry = carry + temp;
            }

            if (bLen >= 0) {
                temp = b.charAt(bLen) == '0' ? 0 : 1;
                carry = carry + temp;
            }

            cur = carry % 2;
            carry = carry >= 2 ? 1 : 0;
            sb.insert(0, cur);

            aLen--;
            bLen--;
        }
        if (carry == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("11", "1"));
    }
}
