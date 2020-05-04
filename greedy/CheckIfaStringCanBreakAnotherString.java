package greedy;

import java.util.Arrays;

/**
 * Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa (in other words s2 can break s1).
 * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
 * Example 1:
 * Input: s1 = "abc", s2 = "xya"
 * Output: true
 * Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".
 * Example 2:
 * Input: s1 = "abe", s2 = "acd"
 * Output: false
 * Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
 * Example 3:
 * Input: s1 = "leetcodee", s2 = "interview"
 * Output: true
 * Constraints:
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 10^5
 * All strings consist of lowercase English letters.
 * 20200504
 */
public class CheckIfaStringCanBreakAnotherString {
    /**
     * 题意：给你两个长度相同的字符串，问其中一个是否可以打破另外一个；打破的定义是，字母任意组合，但是对应index的位置，其中一个永远大于另外一个。
     * 解法：直观想法就是Sort，然后比较相应位置的字母大小，事实证明这种想法就是对的；
     * 更好的办法就是借助map，这样可以O(n)，比较的是已经占据的字符的数量，画图好理解
     * O(n)
     */
    public boolean checkIfCanBreak_(String s1, String s2) {
        int n = s1.length();
        int[] arr = new int[26], brr = new int[26];
        for (int i = 0; i < n; i++) arr[s1.charAt(i) - 97]++;
        for (int i = 0; i < n; i++) brr[s2.charAt(i) - 97]++;
        int count1 = 0, count2 = 0;
        boolean f1 = false, f2 = false;
        for (int i = 0; i < 26; i++) {
            count1 += arr[i];
            count2 += brr[i];
            if (count1 > count2) {
                if (f2) return false;
                f1 = true;
            } else if (count2 > count1) {
                if (f1) return false;
                f2 = true;
            }
        }
        return true;
    }

    /**
     * O(nlogn)
     */
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        // boolean flag1 = true, flag2 = true;
        int flag1 = 0, flag2 = 0;
        if (arr1[0] <= arr2[0]) {
            flag1 = 1;
            for (int i = 1; i < arr1.length; i++) {
                if (arr1[i] > arr2[i]) {
                    flag1 = 2;
                    break;
                }
            }
        }
        if (arr1[0] >= arr2[0]) {
            flag2 = 1;
            for (int i = 1; i < arr1.length; i++) {
                if (arr1[i] < arr2[i]) {
                    flag2 = 2;
                    break;
                }
            }
        }
        return flag1 == 1 || flag2 == 1;
    }
}
