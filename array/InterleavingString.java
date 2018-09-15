package array;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * <p>
 * Created by DrunkPiano on 2017/2/26.
 */

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null) return s2.equals(s3);
        if (s2 == null) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean matrix[][] = new boolean[s1.length() + 1][s2.length() + 1];
        matrix[0][0] = true;
//        matrix[i][j]表示，s1中前i个元素和s2的前j个元素可以组成s3中的前i+j个元素。
        for (int i = 1; i <= s1.length(); i++) {
            //matrix[i][j] = true的条件. 注意第二个条件是i-1
            matrix[i][0] = matrix[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
        }

        for (int j = 1; j <= s2.length(); j++) {
            //matrix[i][j] = true的条件
            matrix[0][j] = matrix[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1);
        }

        for (int i = 1; i <= s1.length(); i++)
            for (int j = 1; j <= s2.length(); j++) {
                //如果加进来的这个数(matrix[i][j])是s2的第j-1位
//                if (s3.charAt(i + j - 1) == s2.charAt(j - 1))
//                    //加入s2的第j位之前的状态，是matrix[i][j-1](因为，往右走1步就是加s2，往下走一步就是加入s1)
//                    //这样分成两行写，跟下面两个条件一起判断那种看起来是一样的，但是有种情况，就是matrix[i][j]本来是true，却可能会被matrix[i - 1][j]置于fasle
//                    matrix[i][j] = matrix[i][j - 1];
//                //如果加进来的这个数是s1中的
//                if (s3.charAt(i + j - 1) == s1.charAt(i - 1))
//                    matrix[i][j] = matrix[i - 1][j];


                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && matrix[i - 1][j]) {
                    matrix[i][j] = true;
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && matrix[i][j - 1]) {
                    matrix[i][j] = true;
                }
            }

        return matrix[s1.length()][s2.length()];
    }

    public static void main(String args[]) {
        InterleavingString interleavingString = new InterleavingString();
        System.out.println(interleavingString.isInterleave("aabcc",
                "dbbca",
                "aadbbcbcac"));
    }
}
