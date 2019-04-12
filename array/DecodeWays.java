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
        if (s.length() == 0 || s.charAt(0) == '0') return 0; //0开头直接无法解码
        //s[i-1] has r1 decode ways , s[i-2] has r2 decode ways
        int r1 = 1;
        int r2 = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                r1 = 0;
            } else if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                r1 = r1 + r2;
                r2 = r1 - r2;
            }
            //新加入的一位>6
            else {
                r2 = r1;
            }
        }
        return r1;
    }

    /**
     * 20190412 review
     * 两年后这题的思路跟两年前一样，做了20分钟发现corner case不停出现于是放弃
     * <p>
     * 看了下高票答案，从后往前，用了类似counting stairs的思想
     * For the case "226" (dp[i]), you can split it into "22 6"(dp[i-1]) and "2 26"(dp[i-2]).
     */
    public int numDecodings__(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];//like counting stairs；226可以从i + 1和i + 2的位置跳过来(2 26和22 6)，227就只能从i+1的位置跳过来
        }
        return memo[0];
    }
}