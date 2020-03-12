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
}
