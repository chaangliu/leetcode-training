package array;

/**
 * 1, 11, 21, 1211, 111221, ...
 * Given an integer n, generate the nth sequence.
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/18.
 */

public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder res = new StringBuilder();
        res.append("1");
        int count = 1;
        int i = 1;
        while (i < n) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(res.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count);
            //count 每次都要置1
            count = 1;
            sb.append(res.charAt(res.length() - 1));
            res = sb;
            i++;
        }
        return res.toString();
    }

    public static void main(String args[]) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(5));
    }
}
