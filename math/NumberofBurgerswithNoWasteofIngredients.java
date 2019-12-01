package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 * <p>
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
 * Example 1:
 * <p>
 * Input: tomatoSlices = 16, cheeseSlices = 7
 * Output: [1,6]
 * Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese. There will be no remaining ingredients.
 * Example 2:
 * <p>
 * Input: tomatoSlices = 17, cheeseSlices = 4
 * Output: []
 * Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
 * Example 3:
 * <p>
 * Input: tomatoSlices = 4, cheeseSlices = 17
 * Output: []
 * Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
 * Example 4:
 * <p>
 * Input: tomatoSlices = 0, cheeseSlices = 0
 * Output: [0,0]
 * Example 5:
 * <p>
 * Input: tomatoSlices = 2, cheeseSlices = 1
 * Output: [0,1]
 * Constraints:
 * <p>
 * 0 <= tomatoSlices <= 10^7
 * 0 <= cheeseSlices <= 10^7
 * 20191201
 */
public class NumberofBurgerswithNoWasteofIngredients {
    /**
     * 题意：问你能不能用完给定的番茄和芝士做出两种汉堡。
     * 这题那道之后很快发现是要解二元一次方程，O(N^2)枚举行吗？数据范围10^7,试了一下果然TLE。
     * 于是开始百度，找了个看着像的，结果WA。
     * 然后我才察觉到这里我们的常数a1b1,a2b2是固定的。。那么这样就很容易了，总之还是花了很长时间，没想清楚
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        //int a1 = 4, b1 = 2,
        //int a2 = 2, b2 = 2,
        int c1 = tomatoSlices;
        int c2 = cheeseSlices * 2;
        List<Integer> res = new ArrayList<>();
        if ((c1 - c2) % 2 == 1) return res;
        int x = (c1 - c2) / 2;
        int y = cheeseSlices - x;
        if (x < 0 || y < 0) return res;//不能有负数解！
        res.add(x);
        res.add(y);
        return res;
    }

    /**
     * lee的代码
     */
    public List<Integer> numOfBurgers_(int t, int c) {
        return t % 2 == 0 && c * 2 <= t && t <= c * 4 ? Arrays.asList(t / 2 - c, c * 2 - t / 2) : new ArrayList();
    }
}
