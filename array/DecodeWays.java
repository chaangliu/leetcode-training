package array;

/**
 * Created by DrunkPiano on 2017/4/9.
 */

public class DecodeWays {

    //dp[i]
    // = dp[i-1] , if nums[i] == 0
    // = dp[i-1] + dp[i-2] , if '1' <=nums[i-1]<='2'  && '1'<=nums[i]<='6'

//    public int numDecodings(String s) {
//        if (s.length() == 0) return 0;
//        if (s.length() == 1) return 1;
//        int dp[] = new int[s.length()];
//        dp[0] = 1;
//        dp[1] = s.charAt(0) == '1' || s.charAt(0) == '2' && '0' < s.charAt(1) && s.charAt(1) < '6' ? 2 : 1;
//        for (int i = 2; i < s.length(); i++) {
//            if (s.charAt(i) == '0') {
//                dp[i] = dp[i - 1];
//            } else if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) < '6') {
//                dp[i] = dp[i - 1] + dp[i - 2];
//            }
//        }
//        return dp[s.length() - 1];
//    }

    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0 ; //0开头直接无法解码
        //s[i-1] has r1 decode ways , s[i-2] has r2 decode ways
        int r1 = 1 ;
        int r2 = 1 ;

        for (int i = 1 ; i < s.length() ; i ++){
            if (s.charAt(i)== '0'){
              r1 = 0;
            }
            else if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i)<='6'){
                r1 = r1 + r2 ;
                r2 = r1 - r2 ;
            }
            //新加入的一位>6
            else {
                r2 = r1 ;
            }
        }
        return r1 ;
    }
}