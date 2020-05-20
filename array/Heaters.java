package array;

import java.util.Arrays;

/**
 * 475. Heaters
 * <p>
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 * <p>
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 * <p>
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 * <p>
 * Note:
 * <p>
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */
public class Heaters {
    //max(每个house离最近的heater的距离)，很多corner case，WA很多次；O(n * n)
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res = 0;
        for (int i = 0; i < houses.length; i++) {
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < heaters.length; j++) {
                if (houses[i] < heaters[j]) {
                    minDist = Math.min(heaters[j] - houses[i], minDist);
                } else if (houses[i] == heaters[j]) {
                    minDist = 0;
                    break;
                } else {
                    if (j < heaters.length - 1) {
                        if (houses[i] < heaters[j + 1]) {
                            minDist = Math.min(houses[i] - heaters[j], heaters[j + 1] - houses[i]);
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        res = Math.max(res, houses[i] - heaters[j]);
                    }
                }
            }
            res = minDist != Integer.MAX_VALUE ? Math.max(minDist, res) : res;
        }
        return res;
    }

    /**
     * 评论区看到的BinarySearch方案
     * 对于每个house，去找离它最近的heater，Java的BinarySearch的Api真绕。。我觉得还是自定义lower_bound比较好
     */
    public int findRadius___(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            result = Math.max(result, Math.min(dist1, dist2));
        }
        return result;
    }
    /**
     * js的lower_bound写法，注意heaters.sort((a, b) => a - b)一定要给comparator，不然会字典序排序。。
     */
    //    var findRadius = function(ho, heaters) {
    //        heaters.sort((a, b) => a - b)
    //        let res = 0
    //        for (let i = 0; i < ho.length; i++) {
    //            let house = ho[i];
    //            let index = lower_bound(heaters, house);
    //            let dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Number.MAX_SAFE_INTEGER
    //            let dist2 = index < heaters.length ? heaters[index] - house : Number.MAX_SAFE_INTEGER
    //            res = Math.max(res, Math.min(dist1, dist2));
    //        }
    //        return res
    //    };
    //
    //    var lower_bound = function(arr, target) {
    //        let lo = 0
    //        let hi = arr.length
    //        while (lo < hi) {
    //            let mid = (lo + hi) >> 1
    //            if (arr[mid] >= target) hi = mid;
    //            else lo = mid + 1;
    //        }
    //        return lo;
    //    };
}
