package array;

/**
 * Given an array of positive integers arr,  find a pattern of length m that is repeated k or more times.
 * <p>
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times consecutively without overlapping. A pattern is defined by its length and the number of repetitions.
 * <p>
 * Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
 * Example 1:
 * Input: arr = [1,2,4,4,4,4], m = 1, k = 3
 * Output: true
 * Explanation: The pattern (4) of length 1 is repeated 4 consecutive times. Notice that pattern can be repeated k or more times but not less.
 * Example 2:
 * <p>
 * Input: arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
 * Output: true
 * Explanation: The pattern (1,2) of length 2 is repeated 2 consecutive times. Another valid pattern (2,1) is also repeated 2 times.
 */
public class DetectPatternofLengthMRepeatedKorMoreTimes {
    /**
     * 题意：在一串数字里，返回是否能找m个连续的pattern，重复k次。
     * 解法：作为easy题，数据量只有100，可以brute force；但是这题有One pass解法，非常巧妙的2 pointers。
     */
//    bool containsPattern(vector<int>& arr, int m, int k) {
//
//        int cnt=0;
//        for(int i=0;i+m < arr.size(); i++){
//
//            if(arr[i]!=arr[i+m]){
//                cnt=0;
//            }
//            cnt += (arr[i] == arr[i+m]);
//            if(cnt == (k-1)*m)
//                return true;
//
//        }
//        return false;
//    }
}
