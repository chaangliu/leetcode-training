package jianzhioffer;

/**
 * 替换空格。
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */

//这题考的是c语言操作数组的技巧，c语言的话要先traverse一遍看有多少空格，
//然后定义新数组长度为oldnumber+replacenumber*2;

//java的话不用。可以利用String.replaceAll，注意StringBuffer没有这个api; 也可以新建一个StringBuffer，对照原str构造新的

public class ReplaceSpace {

    //Approach 1
    public String replaceSpace(StringBuffer str) {
        if (str == null) return null;
        String s = str.toString();
        return s.replaceAll(" ", "%20");
    }

    //Approach 2
    //链接：https://www.nowcoder.com/questionTerminal/4060ac7e3e404ad1a894ef3e17650423
    public String replaceSpace2(StringBuffer str) {
        String sti = str.toString();
        char[] strChar = sti.toCharArray();
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == ' ')
                out.append("%20");
            else
                out.append(strChar[i]);
        }
        return out.toString();
    }
}
