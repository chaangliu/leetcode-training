package array;

import java.util.HashSet;

/**
 * Created by DrunkPiano on 2017/5/7.
 */

public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < candies.length; i++) {
            if (set.size() < candies.length / 2) {
                set.add(candies[i]);
            }
        }
        return set.size();
    }

    public static void main(String args[]){
//        int [] nums = {1,1,2,2,3,3};
        int [] nums = {1,1,2,3};
//        int [] nums = {1,1,1,1,2,2,2,3,3,3};
        System.out.println(new DistributeCandies().distributeCandies(nums));
    }
}
