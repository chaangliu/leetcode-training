package math;

import java.util.PriorityQueue;

/**
 * Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1, and any square sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.
 * <p>
 * Return the maximum possible number of ones that the matrix M can have.
 * Example 1:
 * <p>
 * Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
 * Output: 4
 * Explanation:
 * In a 3*3 matrix, no 2*2 sub-matrix can have more than 1 one.
 * The best solution that has 4 ones is:
 * [1,0,1]
 * [0,0,0]
 * [1,0,1]
 * Example 2:
 * <p>
 * Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
 * Output: 6
 * Explanation:
 * [1,0,1]
 * [1,0,1]
 * [1,0,1]
 * Constraints:
 * <p>
 * 1 <= width, height <= 100
 * 1 <= sideLength <= width, height
 * 0 <= maxOnes <= sideLength * sideLength
 * 20190909
 */
public class MaximumNumberofOnes {
    /**
     * Biweekly contest 08的第四题，是一个数学题或者说智力题，只有61人做出来。要找规律。
     * 这个做法是看到WNJXYK的视频里的思路，计算左上角第一个正方形里的每个位置的等价位置数量，取最多的maxOnes个。
     * 感觉这样的没套路的题也不用指望一定做出来，重心应该放在强TAG的题上面~比如DP，而且通常第四题DP也比较多。
     */
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < sideLength; i++)//(i,j)是坐标
            for (int j = 0; j < sideLength; j++) {
                //计算左上角的squre中每个位置在整个矩形内的等价位置的个数，等价位置可以理解为，平移sideLength高度和宽度之后的位置
                int w = width / sideLength + (i < width % sideLength ? 1 : 0);//计算一条横线上等价位置个数，如果在i在mod范围之外，等价位置多1个
                int h = height / sideLength + (j < height % sideLength ? 1 : 0);
                queue.offer(w * h);
                System.out.println(w * h);
            }
        int res = 0;
        for (int i = 0; i < maxOnes; i++) {
            res += queue.poll();
        }
        return res;
    }
}
