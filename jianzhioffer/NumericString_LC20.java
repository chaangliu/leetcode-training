package jianzhioffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
public class NumericString_LC20 {
    /**
     * 这题重新看了下，其实是编译原理的内容，有限状态自动机（DFA），确定每种状态能转移成什么，以及合法的结束状态；跟前缀树有点像，用状态转移的思想来做。
     * 以下代码摘自中文力扣。
     */
    static class Solution {
        public boolean isNumber(String s) {
            Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
            Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_SPACE, State.STATE_INITIAL);
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
                put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
            }};
            transfer.put(State.STATE_INITIAL, initialMap);
            Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            }};
            transfer.put(State.STATE_INT_SIGN, intSignMap);
            Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_POINT, State.STATE_POINT);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transfer.put(State.STATE_INTEGER, integerMap);
            Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transfer.put(State.STATE_POINT, pointMap);
            Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            }};
            transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
            Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transfer.put(State.STATE_FRACTION, fractionMap);
            Map<CharType, State> expMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
                put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
            }};
            transfer.put(State.STATE_EXP, expMap);
            Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            }};
            transfer.put(State.STATE_EXP_SIGN, expSignMap);
            Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
            Map<CharType, State> endMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transfer.put(State.STATE_END, endMap);

            int length = s.length();
            State state = State.STATE_INITIAL;

            for (int i = 0; i < length; i++) {
                CharType type = toCharType(s.charAt(i));
                if (!transfer.get(state).containsKey(type)) {
                    return false;
                } else {
                    state = transfer.get(state).get(type);
                }
            }
            return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
        }

        public CharType toCharType(char ch) {
            if (ch >= '0' && ch <= '9') {
                return CharType.CHAR_NUMBER;
            } else if (ch == 'e' || ch == 'E') {
                return CharType.CHAR_EXP;
            } else if (ch == '.') {
                return CharType.CHAR_POINT;
            } else if (ch == '+' || ch == '-') {
                return CharType.CHAR_SIGN;
            } else if (ch == ' ') {
                return CharType.CHAR_SPACE;
            } else {
                return CharType.CHAR_ILLEGAL;
            }
        }

        enum State {
            STATE_INITIAL,
            STATE_INT_SIGN,
            STATE_INTEGER,
            STATE_POINT,
            STATE_POINT_WITHOUT_INT,
            STATE_FRACTION,
            STATE_EXP,
            STATE_EXP_SIGN,
            STATE_EXP_NUMBER,
            STATE_END,
        }

        enum CharType {
            CHAR_NUMBER,
            CHAR_EXP,
            CHAR_POINT,
            CHAR_SIGN,
            CHAR_SPACE,
            CHAR_ILLEGAL,
        }
    }

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


    public static void main(String args[]) {
//        new NumericString().isNumeric("123.45e+6".toCharArray());
    }
}
