package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class LargestNumber {
    //my version
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String) o1 + (String) o2;
                String s2 = (String) o2 + (String) o1;
                return s2.compareTo(s1);//倒序排列, 数字大的String的hashcode就越大
            }
        };

        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs, comparator);
        StringBuffer sb = new StringBuffer(nums.length);
        for (int i = 0; i < nums.length; i++) {
            sb.append(strs[i]);
        }

        String res = sb.toString();
        if (res .startsWith("0")){//00的corner case
            return "0";
        }
        return res;
    }

    //leetcode version
//    private class LargerNumberComparator implements Comparator<String> {
//        @Override
//        public int compare(String a, String b) {
//            String order1 = a + b;
//            String order2 = b + a;
//            return order2.compareTo(order1);
//        }
//    }
//
//    public String largestNumber(int[] nums) {
//        // Get input integers as strings.
//        String[] asStrs = new String[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            asStrs[i] = String.valueOf(nums[i]);
//        }
//
//        // Sort strings according to custom comparator.
//        Arrays.sort(asStrs, new LargerNumberComparator());
//
//        // If, after being sorted, the largest number is `0`, the entire number
//        // is zero.
//        if (asStrs[0].equals("0")) {
//            return "0";
//        }
//
//        // Build largest number from sorted array.
//        String largestNumberStr = new String();
//        for (String numAsStr : asStrs) {
//            largestNumberStr += numAsStr;
//        }
//
//        return largestNumberStr;
//    }
}


//附：String的hashCode方法
//public int hashCode() {
//        int h = hash;
//        if (h == 0 && value.length > 0) {
//        char val[] = value;
//
//        for (int i = 0; i < value.length; i++) {
//        h = 31 * h + val[i];
//        }
//        hash = h;
//        }
//        return h;
//        }
//        ---------------------
//        https://blog.csdn.net/claram/article/details/53770830?utm_source=copy
