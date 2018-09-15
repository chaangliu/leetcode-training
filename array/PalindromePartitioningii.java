package array;

/**
 * Created by DrunkPiano on 2017/4/27.
 */

public class PalindromePartitioningii {

    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] pal = new boolean[s.length()][s.length()];
        int[] minCut = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            minCut[i] = i;
            for (int j = 0; j <= i; j++) {
                if ((s.charAt(i) == s.charAt(j) && i - j <= 1 ||
                        s.charAt(i) == s.charAt(j) && pal[j+1][i-1])) {
                    pal[j][i] = true;
                    if (j > 0) {
                        minCut[i] = Math.min(minCut[i], minCut[j - 1] + 1);
                    } else {
                        //j == i的时候切0刀
                        minCut[i] = 0;
                    }
                }
            }
        }
        return minCut[s.length() - 1];
    }
//public int minCut(String s) {
//    int n = s.length();
//
//    boolean dp[][] = new boolean[n][n];
//    int cut[] = new int[n];
//
//    for (int j = 0; j < n; j++) {
//        cut[j] = j; //set maximum # of cut
//        for (int i = 0; i <= j; i++) {
//            if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i+1][j-1])) {
//                dp[i][j] = true;
//
//                // if need to cut, add 1 to the previous cut[i-1]
//                if (i > 0){
//                    cut[j] = Math.min(cut[j], cut[i-1] + 1);
//                }else{
//                    // if [0...j] is palindrome, no need to cut
//                    cut[j] = 0;
//                }
//            }
//        }
//    }

//    return cut[n-1];
//}
    public static void main(String args[]) {
        System.out.println(new PalindromePartitioningii().minCut("aab"));
    }
}
