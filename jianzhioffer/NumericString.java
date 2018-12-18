package jianzhioffer;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class NumericString {


    //这题没意义，简直像业务里的胶水代码。以下摘自讨论区
//    class Solution {
//        public:
//        bool isNumeric(char* str) {
//            // 标记符号、小数点、e是否出现过
//            bool sign = false, decimal = false, hasE = false;
//            for (int i = 0; i < strlen(str); i++) {
//                if (str[i] == 'e' || str[i] == 'E') {
//                    if (i == strlen(str)-1) return false; // e后面一定要接数字
//                    if (hasE) return false;  // 不能同时存在两个e
//                    hasE = true;
//                } else if (str[i] == '+' || str[i] == '-') {
//                    // 第二次出现+-符号，则必须紧接在e之后
//                    if (sign && str[i-1] != 'e' && str[i-1] != 'E') return false;
//                    // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
//                    if (!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E') return false;
//                    sign = true;
//                } else if (str[i] == '.') {
//                    // e后面不能接小数点，小数点不能出现两次
//                    if (hasE || decimal) return false;
//                    decimal = true;
//                } else if (str[i] < '0' || str[i] > '9') // 不合法字符
//                    return false;
//            }
//            return true;
//        }
//    };


    ////**以下未通过**
//    public boolean isNumeric(char[] str) {
//        int numberOfE = 0;
//        int numberOfPoints = 0;
//        if (str == null || str.length == 0) return false;
//        if (str.length == 1 && (str[0] < '0' || str[0] > '9')) return false;
//        for (int i = 0; i < str.length; i++) {
//            //字符范围
//            if ((str[i] < '0' || str[i] > '9') && str[i] != '+' && str[i] != '-' && str[i] != 'e' && str[i] != '.') return false;
//            //e的限制
//            if (str[i] == 'e') {
//                if (i == str.length - 1 || i == 0 || numberOfE != 0) return false;
//                numberOfE++;
//            }
//            //.的限制
//            if (str[i] == '.') {
//                if (i == str.length - 1 || i == 0 || numberOfPoints != 0) return false;
//                numberOfPoints++;
//            }
//            //符号限制
//            if (str[i] == '+' || str[i] == '-') {
//                if (i != 0) {
//                    if (str[i - 1] != 'e' && str[i - 1] != 'E')
//                        return false;
//                    for (int j = i + i; j < str.length; j++) {
//                        if (str[j] > 'z' || str[i] < 'a') return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }



    public static void main(String args[]){
//        new NumericString().isNumeric("123.45e+6".toCharArray());
    }
}
