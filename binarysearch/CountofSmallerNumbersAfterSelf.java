package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CountofSmallerNumbersAfterSelf {
    /**
     * 题意：找出每个数字右边有几个比它小的数。
     * 解法：binary search，有人说是O(n^2)，因为有插入操作。
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>(), res = new ArrayList<>();
        int n = nums.length;
        for (int i = n - 1; i >=0; i--) {
            int lb = lower_bound(list, nums[i]);
            //System.out.println("for " + nums[i] + ", lb is " + lb);
            list.add(lb, nums[i]);
            res.add(lb);    
        }
        Collections.reverse(res);
        return res;
    }

    private int lower_bound(List<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (list.get(mid)>= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}