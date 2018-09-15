package array;

/**
 * Given an input string, reverse the string word by word.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/20.
 */

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].equals("") ) {
                sb.append(array[i]).append(" ");
            }
            //这样写的话就忽略了" 1"这种情况
            // if (i != 0) {
            //     sb.append(" ");
            // }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }

    public static void main(String args[]) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
//        System.out.println(reverseWordsInAString.reverseWords(" a"));
        System.out.println(reverseWordsInAString.reverseWords("the sky is blue"));
    }
}
