package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given n boxes, each box is given in the format [status, candies, keys, containedBoxes] where:
 * status[i]: an integer which is 1 if box[i] is open and 0 if box[i] is closed.
 * candies[i]: an integer representing the number of candies in box[i].
 * keys[i]: an array contains the indices of the boxes you can open with the key in box[i].
 * containedBoxes[i]: an array contains the indices of the boxes found in box[i].
 * You will start with some boxes given in initialBoxes array. You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.
 * <p>
 * Return the maximum number of candies you can get following the rules above.
 * Example 1:
 * <p>
 * Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
 * Output: 16
 * Explanation: You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2. Box 1 is closed and you don't have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
 * In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
 * Total number of candies collected = 7 + 4 + 5 = 16 candy.
 * Example 2:
 * <p>
 * Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
 * Output: 6
 * Explanation: You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys. The total number of candies will be 6.
 * Example 3:
 * <p>
 * Input: status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]
 * Output: 1
 * Example 4:
 * <p>
 * Input: status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
 * Output: 0
 * Example 5:
 * <p>
 * Input: status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]
 * Output: 7
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= status.length <= 1000
 * status.length == candies.length == keys.length == containedBoxes.length == n
 * status[i] is 0 or 1.
 * 1 <= candies[i] <= 1000
 * 0 <= keys[i].length <= status.length
 * 0 <= keys[i][j] < status.length
 * All values in keys[i] are unique.
 * 0 <= containedBoxes[i].length <= status.length
 * 0 <= containedBoxes[i][j] < status.length
 * All values in containedBoxes[i] are unique.
 * Each box is contained in one box at most.
 * 0 <= initialBoxes.length <= status.length
 * 0 <= initialBoxes[i] < status.length
 * 20191223
 */
public class MaximumCandiesYouCanGetfromBoxes {
    /**
     * 题意：给你一些盒子，盒子里面又包含盒子，盒子可能有🔑也可能没有，打开每个盒子能获得一些糖果，问最多能获得多少糖果。
     * 这题我一开始以为是必须选某个path去获得这个path上的box中的糖果，所以我想用dfs + memo；实际上这里可以拿完每一条path上的糖果，所以maximum就是把所有能打开的盒子都打开。
     * BFS。巧妙的是，用一个reachedButClosed来记录遇到了但是没🔑的情况。
     */
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> reachedButClosed = new HashSet<>();
        for (int i : initialBoxes) {
            if (status[i] == 1)
                q.offer(i);
            else
                reachedButClosed.add(i);
        }
        int res = 0;
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                int box = q.poll();
                res += candies[box];
                for (int i : keys[box]) {
                    if (status[i] == 0 && reachedButClosed.contains(i)) {
                        q.offer(i);
                    }
                    status[i] = 1;//有钥匙了，标记为下一轮打开
                }
                for (int i : containedBoxes[box]) {
                    if (status[i] == 1) {
                        q.offer(i);
                    } else reachedButClosed.add(i);
                }
            }
        }
        return res;
    }
}
