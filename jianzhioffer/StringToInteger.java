package jianzhioffer;

public class StringToInteger {

    //这题跟leetcode 8一样。那题差评如潮
    public int StrToInt(String str) {
        int total = 0, sign = 1, index = 0;
        if (str == null || str.length() == 0) return 0;
        //jump through spaces
        while (index < str.length() && str.charAt(index) == ' ') index++;
        //judge the sign
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        //计算方法是进位
        while (index < str.length()) {
            //易错
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) return 0;
            //在超出int范围之前判断
            if (total > Integer.MAX_VALUE / 10 || total == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
                total = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                break;
            }
            total = total * 10 + digit;
            index++;
        }
        return total * sign;
    }
}
